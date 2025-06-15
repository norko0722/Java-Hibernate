package sk.kasv.balucha.hibernate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDTO {

    @NotBlank(message = "Equipment name is required")
    private String name;

    private String description;
}