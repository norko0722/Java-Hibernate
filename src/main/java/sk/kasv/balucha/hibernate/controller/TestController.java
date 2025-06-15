package sk.kasv.balucha.hibernate.controller;

import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.entities.Reservation;
import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.service.MeetingRoomService;
import sk.kasv.balucha.hibernate.service.ReservationService;
import sk.kasv.balucha.hibernate.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final MeetingRoomService meetingRoomService;
    private final ReservationService reservationService;
    private final UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("\n=== Automatická inicializácia testovacích dát ===");
        try {
            // Kontrola či už existujú dáta
            if (meetingRoomService.getAllRooms().isEmpty()) {
                // Vytvorenie testovacej miestnosti
                MeetingRoom room = new MeetingRoom();
                room.setName("Konferenčná miestnosť 1");
                room.setCapacity(10);
                room.setLocation("1. poschodie");
                room.setIsActive(true);
                MeetingRoom savedRoom = meetingRoomService.createRoom(room);
                System.out.println("✓ Vytvorená miestnosť: " + savedRoom.getName());

                // Vytvorenie testového používateľa
                User user = new User();
                user.setFirstName("Admin");
                user.setLastName("Používateľ");
                user.setEmail("admin@example.com");
                user.setPassword("admin123");
                user.setRole("ADMIN");
                User savedUser = userService.createUser(user);
                System.out.println("✓ Vytvorený používateľ: " + savedUser.getFirstName() + " " + savedUser.getLastName());

                // Vytvorenie testovej rezervácie
                Reservation reservation = new Reservation();
                reservation.setMeetingRoom(savedRoom);
                reservation.setOrganizer(savedUser);
                reservation.setStartTime(LocalDateTime.now().plusHours(1));
                reservation.setEndTime(LocalDateTime.now().plusHours(2));
                reservation.setPurpose("Tímové stretnutie");
                reservation.setNumberOfAttendees(5);
                Reservation savedReservation = reservationService.createReservation(reservation);
                System.out.println("✓ Vytvorená rezervácia: " + savedReservation.getPurpose());
            } else {
                System.out.println("✓ Testovacie dáta už existujú");
            }
        } catch (Exception e) {
            System.out.println("✗ Chyba pri vytváraní testovacích dát: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        System.out.println("\n=== Kontrola stavu aplikácie ===");
        System.out.println("1. Kontrola databázového pripojenia...");
        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            System.out.println("   ✓ Databáza je pripojená");
        } catch (Exception e) {
            System.out.println("   ✗ Chyba pripojenia k databáze: " + e.getMessage());
        }

        System.out.println("\n2. Kontrola dostupných miestností...");
        try {
            List<MeetingRoom> rooms = meetingRoomService.getAllRooms();
            System.out.println("   ✓ Nájdených " + rooms.size() + " miestností");
            rooms.forEach(r -> System.out.println("     - " + r.getName() + " (kapacita: " + r.getCapacity() + ")"));
        } catch (Exception e) {
            System.out.println("   ✗ Chyba pri načítaní miestností: " + e.getMessage());
        }

        System.out.println("\n3. Kontrola používateľov...");
        try {
            List<User> users = userService.getAllUsers();
            System.out.println("   ✓ Nájdených " + users.size() + " používateľov");
            users.forEach(u -> System.out.println("     - " + u.getFirstName() + " " + u.getLastName() + " (" + u.getEmail() + ")"));
        } catch (Exception e) {
            System.out.println("   ✗ Chyba pri načítaní používateľov: " + e.getMessage());
        }

        System.out.println("\n4. Kontrola rezervácií...");
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            System.out.println("   ✓ Nájdených " + reservations.size() + " rezervácií");
            reservations.forEach(r -> System.out.println("     - " + r.getPurpose() + " (" + r.getStartTime() + ")"));
        } catch (Exception e) {
            System.out.println("   ✗ Chyba pri načítaní rezervácií: " + e.getMessage());
        }

        return ResponseEntity.ok("Kontrola stavu dokončená. Pozrite si konzolu pre detaily.");
    }
} 