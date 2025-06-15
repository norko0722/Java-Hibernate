package sk.kasv.balucha.hibernate.service;

import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.dao.MeetingRoomDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {
    private final MeetingRoomDAO meetingRoomDAO;

    @Autowired
    public MeetingRoomService(MeetingRoomDAO meetingRoomDAO) {
        this.meetingRoomDAO = meetingRoomDAO;
    }

    public List<MeetingRoom> getAllRooms() {
        return meetingRoomDAO.findAll();
    }

    public MeetingRoom getRoomById(Long id) {
        return meetingRoomDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting room not found with id: " + id));
    }

    public MeetingRoom createRoom(MeetingRoom room) {
        return meetingRoomDAO.save(room);
    }

    public MeetingRoom updateRoom(Long id, MeetingRoom roomDetails) {
        MeetingRoom room = getRoomById(id);
        room.setName(roomDetails.getName());
        room.setCapacity(roomDetails.getCapacity());
        room.setLocation(roomDetails.getLocation());
        return meetingRoomDAO.save(room);
    }

    public void deleteRoom(Long id) {
        if (!meetingRoomDAO.existsById(id)) {
            throw new EntityNotFoundException("Meeting room not found with id: " + id);
        }
        meetingRoomDAO.deleteById(id);
    }
} 