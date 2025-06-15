package sk.kasv.balucha.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.balucha.hibernate.dao.AppDAO;
import sk.kasv.balucha.hibernate.entities.Reservation;
import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.dto.ReservationCreateDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.stream.Collectors;
import sk.kasv.balucha.hibernate.dto.ReservationUpdateDTO;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;


@Service
public class ReservationService {
    private AppDAO appDAO;
    private MeetingRoomService meetingRoomService;
    private final UserService userService;

    @Autowired
    public ReservationService(AppDAO appDAO, MeetingRoomService meetingRoomService, UserService userService) {
        this.appDAO = appDAO;
        this.meetingRoomService = meetingRoomService;
        this.userService = userService;
    }


    public Reservation updateReservation(Reservation reservation) {
        appDAO.updateReservation(reservation);
        return reservation;
    }

    public Reservation findReservationById(int id) {
        return appDAO.findReservationById(id);
    }

    public List<Reservation> findAllReservations() {
        return appDAO.findAllReservations();
    }

    public boolean deleteReservation(int id) {
        Reservation reservation = findReservationById(id);
        if (reservation != null) {
            appDAO.deleteReservation(reservation);
            return true;
        }
        return false;
    }


    public List<Reservation> findReservationsByRoom(int roomId) {
        return appDAO.findReservationsByRoom(roomId);
    }

    public List<Reservation> findReservationsByUser(int userId) {
        return appDAO.findReservationsByUser(userId);
    }

    public Reservation createReservation(ReservationCreateDTO dto) {
        MeetingRoom room = meetingRoomService.findRoomById(dto.getMeetingRoomId());
        if (room == null) {
            throw new RuntimeException("Meeting room not found with id: " + dto.getMeetingRoomId());
        }

        User organizer = userService.findUserById(dto.getOrganizerId());
        if (organizer == null) {
            throw new RuntimeException("Organizer not found with id: " + dto.getOrganizerId());
        }

        Reservation reservation = new Reservation();

        reservation.setTitle(dto.getTitle());
        reservation.setPurpose(dto.getPurpose());
        reservation.setDescription(dto.getDescription());

        LocalDateTime startDateTime = LocalDateTime.of(dto.getReservationDate(), dto.getStartTime());
        LocalDateTime endDateTime = LocalDateTime.of(dto.getReservationDate(), dto.getEndTime());
        reservation.setStartTime(startDateTime);
        reservation.setEndTime(endDateTime);

        reservation.setMeetingRoom(room);
        reservation.setOrganizer(organizer);

        Set<User> participants = new HashSet<>();
        if (dto.getParticipantIds() != null && !dto.getParticipantIds().isEmpty()) {

            List<Integer> participantIdsWithoutOrganizer = dto.getParticipantIds().stream()
                    .filter(id -> !id.equals(dto.getOrganizerId()))
                    .toList();

            if (!participantIdsWithoutOrganizer.isEmpty()) {
                participants = participantIdsWithoutOrganizer.stream()
                        .map(userService::findUserById)
                        .collect(Collectors.toSet());
            }
        }
        reservation.setParticipants(participants);

        int totalAttendees = 1 + participants.size();
        reservation.setNumberOfAttendees(totalAttendees);

        appDAO.saveReservation(reservation);

        return reservation;
    }

    public Reservation addParticipantsToReservation(int reservationId, List<Integer> participantIds) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation == null) throw new RuntimeException("Reservation not found");

        Set<User> participantsToAdd = participantIds.stream()
                .map(userService::findUserById)
                .collect(Collectors.toSet());

        reservation.getParticipants().addAll(participantsToAdd);

        int totalAttendees = 1 + reservation.getParticipants().size();
        reservation.setNumberOfAttendees(totalAttendees);

        return updateReservation(reservation);
    }

    public Reservation updateReservation(int reservationId, ReservationUpdateDTO dto) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found with id: " + reservationId);
        }

        if (dto.getTitle() != null) reservation.setTitle(dto.getTitle());
        if (dto.getPurpose() != null) reservation.setPurpose(dto.getPurpose());
        if (dto.getDescription() != null) reservation.setDescription(dto.getDescription());

        LocalDate date = (dto.getReservationDate() != null) ? dto.getReservationDate() : reservation.getStartTime().toLocalDate();
        LocalTime startTime = (dto.getStartTime() != null) ? dto.getStartTime() : reservation.getStartTime().toLocalTime();
        LocalTime endTime = (dto.getEndTime() != null) ? dto.getEndTime() : reservation.getEndTime().toLocalTime();

        reservation.setStartTime(LocalDateTime.of(date, startTime));
        reservation.setEndTime(LocalDateTime.of(date, endTime));

        return updateReservation(reservation);
    }
}