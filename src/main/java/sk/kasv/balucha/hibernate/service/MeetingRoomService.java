package sk.kasv.balucha.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.balucha.hibernate.dao.AppDAO;
import sk.kasv.balucha.hibernate.entities.Equipment;
import sk.kasv.balucha.hibernate.entities.MeetingRoom;

import java.util.List;



@Service
public class MeetingRoomService {
    private AppDAO appDAO;

    private final EquipmentService equipmentService;

    @Autowired
    public MeetingRoomService(AppDAO appDAO, EquipmentService equipmentService) {
        this.appDAO = appDAO;
        this.equipmentService = equipmentService;
    }

    public MeetingRoom saveRoom(MeetingRoom room) {
        appDAO.saveRoom(room);
        return room;
    }

    public MeetingRoom updateRoom(MeetingRoom room) {
        appDAO.updateRoom(room);
        return room;
    }

    public MeetingRoom findRoomById(int id) {
        return appDAO.findRoomById(id);
    }

    public List<MeetingRoom> findAllRooms() {
        return appDAO.findAllRooms();
    }

    public boolean deleteRoom(int id) {
        MeetingRoom room = findRoomById(id);
        if (room != null) {
            appDAO.deleteRoom(room);
            return true;
        }
        return false;
    }

    public MeetingRoom saveRoomWithEquipment(MeetingRoom room, Equipment equipment) {
        return appDAO.saveRoomWithEquipment(room, equipment);
    }

    public MeetingRoom assignEquipmentToRoom(int roomId, int equipmentId) {
        MeetingRoom room = findRoomById(roomId);
        Equipment equipment = equipmentService.findEquipmentById(equipmentId);

        if (room == null || equipment == null) {
            throw new RuntimeException("Room or Equipment not found");
        }

        room.getEquipment().add(equipment);
        equipment.setMeetingRoom(room);

        equipmentService.saveEquipment(equipment);

        return room;
    }

    public void unassignEquipmentFromRoom(int roomId, int equipmentId) {
        MeetingRoom room = findRoomById(roomId);
        Equipment equipment = equipmentService.findEquipmentById(equipmentId);

        if (room == null || equipment == null) {
            throw new RuntimeException("Room or Equipment not found");
        }

        if(equipment.getMeetingRoom() != null && equipment.getMeetingRoom().getId() == roomId) {
            equipment.setMeetingRoom(null);
            room.getEquipment().remove(equipment);

            equipmentService.saveEquipment(equipment);
        } else {
            throw new RuntimeException("Equipment is not assigned to this room");
        }
    }
} 