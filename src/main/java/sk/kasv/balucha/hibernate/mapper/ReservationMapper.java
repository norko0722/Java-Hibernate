package sk.kasv.balucha.hibernate.mapper;

import org.springframework.stereotype.Component;
import sk.kasv.balucha.hibernate.dto.ReservationResponseDTO;
import sk.kasv.balucha.hibernate.dto.RoomSummaryDTO;
import sk.kasv.balucha.hibernate.dto.UserSummaryDTO;
import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.entities.Reservation;
import sk.kasv.balucha.hibernate.entities.User;

import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public ReservationResponseDTO toReservationResponseDTO(Reservation reservation) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(reservation.getId());
        dto.setTitle(reservation.getTitle());
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setPurpose(reservation.getPurpose());
        dto.setDescription(reservation.getDescription());
        dto.setNumberOfAttendees(reservation.getNumberOfAttendees());
        dto.setStatus(reservation.getStatus());
        dto.setCancelled(reservation.isCancelled());
        dto.setMeetingRoom(toRoomSummaryDTO(reservation.getMeetingRoom()));
        dto.setOrganizer(toUserSummaryDTO(reservation.getOrganizer()));
        dto.setParticipants(reservation.getParticipants().stream()
                .map(this::toUserSummaryDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private UserSummaryDTO toUserSummaryDTO(User user) {
        if (user == null) return null;
        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private RoomSummaryDTO toRoomSummaryDTO(MeetingRoom room) {
        if (room == null) return null;
        RoomSummaryDTO dto = new RoomSummaryDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setLocation(room.getLocation());
        return dto;
    }
}