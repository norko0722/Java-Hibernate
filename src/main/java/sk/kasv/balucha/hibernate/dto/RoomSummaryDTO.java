package sk.kasv.balucha.hibernate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomSummaryDTO {
    private int id;
    private String name;
    private String location;
}