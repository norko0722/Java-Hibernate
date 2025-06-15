package sk.kasv.balucha.hibernate.dao;

import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomDAO extends JpaRepository<MeetingRoom, Long> {
} 