package sk.kasv.balucha.hibernate.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingRoomDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    @NotBlank(message = "Location is required")
    private String location;
}