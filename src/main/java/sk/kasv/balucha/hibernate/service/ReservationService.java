package sk.kasv.balucha.hibernate.service;

import sk.kasv.balucha.hibernate.entities.Reservation;
import sk.kasv.balucha.hibernate.dao.ReservationDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;

    @Autowired
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + id));
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationDAO.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = getReservationById(id);
        reservation.setStartTime(reservationDetails.getStartTime());
        reservation.setEndTime(reservationDetails.getEndTime());
        reservation.setPurpose(reservationDetails.getPurpose());
        reservation.setNumberOfAttendees(reservationDetails.getNumberOfAttendees());
        reservation.setIsCancelled(reservationDetails.getIsCancelled());
        return reservationDAO.save(reservation);
    }

    public void deleteReservation(Long id) {
        if (!reservationDAO.existsById(id)) {
            throw new EntityNotFoundException("Reservation not found with id: " + id);
        }
        reservationDAO.deleteById(id);
    }

    public List<Reservation> getReservationsByRoom(Long roomId) {
        return reservationDAO.findByMeetingRoomId(roomId);
    }

    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationDAO.findByOrganizerId(userId);
    }
} 