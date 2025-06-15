package sk.kasv.balucha.hibernate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummaryDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}