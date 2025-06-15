package sk.kasv.balucha.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.service.MeetingRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import sk.kasv.balucha.hibernate.dto.MeetingRoomDTO;


import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Meeting Room Management", description = "APIs for managing meeting rooms")
public class MeetingRoomController {

    private MeetingRoomService meetingRoomService;

    @Autowired
    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping
    @Operation(summary = "Get all meeting rooms")
    public List<MeetingRoom> getAllRooms() {
        return meetingRoomService.findAllRooms();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get meeting room by ID")
    public ResponseEntity<MeetingRoom> getRoomById(@PathVariable int id) {
        MeetingRoom room = meetingRoomService.findRoomById(id);
        if (room != null) {
            return ResponseEntity.ok(room);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create new meeting room")
    public MeetingRoom createRoom(@Valid @RequestBody MeetingRoomDTO roomDTO) {
        MeetingRoom newRoom = new MeetingRoom();
        newRoom.setName(roomDTO.getName());
        newRoom.setCapacity(roomDTO.getCapacity());
        newRoom.setLocation(roomDTO.getLocation());
        return this.meetingRoomService.saveRoom(newRoom);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update meeting room")
    public ResponseEntity<MeetingRoom> updateRoom(@PathVariable int id, @Valid @RequestBody MeetingRoomDTO roomDTO) {
        MeetingRoom room = this.meetingRoomService.findRoomById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        if (roomDTO.getName() != null && !roomDTO.getName().isBlank()) {
            room.setName(roomDTO.getName());
        }
        if (roomDTO.getCapacity() > 0) {
            room.setCapacity(roomDTO.getCapacity());
        }
        if (roomDTO.getLocation() != null && !roomDTO.getLocation().isBlank()) {
            room.setLocation(roomDTO.getLocation());
        }

        MeetingRoom updatedRoom = this.meetingRoomService.updateRoom(room);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete meeting room")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        if (meetingRoomService.deleteRoom(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{roomId}/equipment/{equipmentId}")
    @Operation(summary = "Assign an existing piece of equipment to a meeting room")
    public ResponseEntity<MeetingRoom> assignEquipmentToRoom(@PathVariable int roomId, @PathVariable int equipmentId) {
        try {
            MeetingRoom updatedRoom = meetingRoomService.assignEquipmentToRoom(roomId, equipmentId);
            return ResponseEntity.ok(updatedRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{roomId}/equipment/{equipmentId}")
    @Operation(summary = "Unassign a piece of equipment from a meeting room")
    public ResponseEntity<Void> unassignEquipmentFromRoom(@PathVariable int roomId, @PathVariable int equipmentId) {
        try {
            meetingRoomService.unassignEquipmentFromRoom(roomId, equipmentId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 