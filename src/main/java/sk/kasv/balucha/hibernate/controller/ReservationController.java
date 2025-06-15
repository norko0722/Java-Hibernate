package sk.kasv.balucha.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.balucha.hibernate.entities.Reservation;

import sk.kasv.balucha.hibernate.mapper.ReservationMapper;
import sk.kasv.balucha.hibernate.service.ReservationService;
import sk.kasv.balucha.hibernate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import sk.kasv.balucha.hibernate.dto.ReservationCreateDTO;
import sk.kasv.balucha.hibernate.dto.ParticipantAddDTO;
import sk.kasv.balucha.hibernate.dto.ReservationUpdateDTO;
import sk.kasv.balucha.hibernate.dto.ReservationResponseDTO;
import java.util.stream.Collectors;
import java.util.List;


@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservation Management", description = "APIs for managing reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    @Operation(summary = "Get all reservations")
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationService.findAllReservations().stream()
                .map(reservationMapper::toReservationResponseDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get reservation by ID")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable int id) {
        Reservation reservation = this.reservationService.findReservationById(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservationMapper.toReservationResponseDTO(reservation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create new reservation")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationCreateDTO reservationDTO) {
        try {
            if (reservationDTO.getStartTime().isAfter(reservationDTO.getEndTime())) {
                return ResponseEntity.badRequest().build();
            }
            Reservation newReservation = reservationService.createReservation(reservationDTO);
            return ResponseEntity.ok(newReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update reservation")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @Valid @RequestBody ReservationUpdateDTO dto) {
        try {
            Reservation updatedReservation = reservationService.updateReservation(id, dto);
            return ResponseEntity.ok(updatedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete reservation")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        if (reservationService.deleteReservation(id)) {
        return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/room/{roomId}")
    @Operation(summary = "Get reservations by room ID")
    public List<Reservation> getReservationsByRoom(@PathVariable int roomId) {
        return reservationService.findReservationsByRoom(roomId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get reservations by user ID")
    public List<Reservation> getReservationsByUser(@PathVariable int userId) {
        return reservationService.findReservationsByUser(userId);
    }

    @PostMapping("/{id}/participants")
    @Operation(summary = "Add participants to reservation")
    public ResponseEntity<Reservation> addParticipantsToReservation(@PathVariable int id, @Valid @RequestBody ParticipantAddDTO dto) {
        try {
            Reservation updatedReservation = reservationService.addParticipantsToReservation(id, dto.getUserIds());
            return ResponseEntity.ok(updatedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 