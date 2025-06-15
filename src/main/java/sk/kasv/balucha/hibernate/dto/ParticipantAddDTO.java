package sk.kasv.balucha.hibernate.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ParticipantAddDTO {
    @NotEmpty(message = "List of user IDs cannot be empty.")
    private List<Integer> userIds;
}