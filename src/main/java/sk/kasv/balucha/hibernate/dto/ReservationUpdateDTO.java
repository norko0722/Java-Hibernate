package sk.kasv.balucha.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ReservationUpdateDTO {
    private String title;
    @Schema(type = "string", pattern = "yyyy-MM-dd", example = "2025-12-31")
    private LocalDate reservationDate;
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", pattern = "HH:mm", example = "10:00")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", pattern = "HH:mm", example = "11:30")
    private LocalTime endTime;
    private String purpose;
    private String description;
}