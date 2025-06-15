package sk.kasv.balucha.hibernate.dao;

import sk.kasv.balucha.hibernate.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMeetingRoomId(Long roomId);
    List<Reservation> findByOrganizerId(Long userId);
} 