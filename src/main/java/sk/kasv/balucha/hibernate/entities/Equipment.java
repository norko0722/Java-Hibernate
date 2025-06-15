package sk.kasv.balucha.hibernate.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @ManyToMany(mappedBy = "equipment")
    private Set<MeetingRoom> rooms = new HashSet<>();
} 