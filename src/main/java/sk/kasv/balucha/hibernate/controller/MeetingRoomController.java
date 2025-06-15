package sk.kasv.balucha.hibernate.controller;

import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.service.MeetingRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Meeting Room Management", description = "APIs for managing meeting rooms")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @GetMapping
    @Operation(summary = "Get all meeting rooms")
    public ResponseEntity<List<MeetingRoom>> getAllRooms() {
        return ResponseEntity.ok(meetingRoomService.getAllRooms());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get meeting room by ID")
    public ResponseEntity<MeetingRoom> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(meetingRoomService.getRoomById(id));
    }

    @PostMapping
    @Operation(summary = "Create new meeting room")
    public ResponseEntity<MeetingRoom> createRoom(@Valid @RequestBody MeetingRoom room) {
        return ResponseEntity.ok(meetingRoomService.createRoom(room));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update meeting room")
    public ResponseEntity<MeetingRoom> updateRoom(@PathVariable Long id, @Valid @RequestBody MeetingRoom room) {
        return ResponseEntity.ok(meetingRoomService.updateRoom(id, room));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete meeting room")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        meetingRoomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }
} 