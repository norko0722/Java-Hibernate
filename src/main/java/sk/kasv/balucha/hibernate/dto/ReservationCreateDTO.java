package sk.kasv.balucha.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ReservationCreateDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Reservation date is required")
    @FutureOrPresent(message = "Reservation date cannot be in the past")
    @Schema(type = "string", pattern = "yyyy-MM-dd", example = "2025-12-31")
    private LocalDate reservationDate;

    @NotNull(message = "Start time is required")
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", pattern = "HH:mm", example = "10:00")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", pattern = "HH:mm", example = "11:30")
    private LocalTime endTime;

    @NotBlank(message = "Purpose is required")
    private String purpose;
    private String description;

    @NotNull(message = "Meeting room ID is required")
    private Integer meetingRoomId;
    @NotNull(message = "Organizer ID is required")
    private Integer organizerId;
    private List<Integer> participantIds;

    @AssertTrue(message = "End time must be after start time")
    private boolean isEndTimeAfterStartTime() {
        if (startTime == null || endTime == null) {
            return true;
        }
        return endTime.isAfter(startTime);
    }
}