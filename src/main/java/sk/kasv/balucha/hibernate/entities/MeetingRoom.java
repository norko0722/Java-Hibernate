package sk.kasv.balucha.hibernate.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "meeting_rooms")
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "meetingRoom")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Equipment> equipment = new HashSet<>();
}