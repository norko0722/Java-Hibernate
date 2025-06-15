package sk.kasv.balucha.hibernate.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {
    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "description")
    private String description;

    @Column(name = "number_of_attendees")
    private int numberOfAttendees;

    @Column(name = "is_cancelled")
    private boolean isCancelled;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "meeting_room_id", nullable = false)
    private MeetingRoom meetingRoom;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // PRIDAJTE TOTO
    @JoinTable(
            name = "reservation_participants",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (title == null || title.trim().isEmpty()) {
            title = "Untitled Meeting";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}