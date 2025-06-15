package sk.kasv.balucha.hibernate.dto;

import lombok.Getter;
import lombok.Setter;
import sk.kasv.balucha.hibernate.entities.Reservation;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationResponseDTO {
    private int id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;
    private String description;
    private int numberOfAttendees;
    private Reservation.Status status;
    private boolean isCancelled;
    private RoomSummaryDTO meetingRoom;
    private UserSummaryDTO organizer;
    private List<UserSummaryDTO> participants;
}