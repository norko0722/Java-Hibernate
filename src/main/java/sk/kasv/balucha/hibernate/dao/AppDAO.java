package sk.kasv.balucha.hibernate.dao;

import sk.kasv.balucha.hibernate.entities.MeetingRoom;
import sk.kasv.balucha.hibernate.entities.Reservation;
import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.entities.Equipment;

import java.util.List;

public interface AppDAO {
    void saveUser(User user);
    void updateUser(User user);
    User findUserById(int id);
    void deleteUser(User user);
    List<User> findAllUsers();

    void saveRoom(MeetingRoom room);
    void updateRoom(MeetingRoom room);
    MeetingRoom findRoomById(int id);
    void deleteRoom(MeetingRoom room);
    List<MeetingRoom> findAllRooms();

    void saveReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    Reservation findReservationById(int id);
    void deleteReservation(Reservation reservation);
    List<Reservation> findAllReservations();

    void saveEquipment(Equipment equipment);
    void updateEquipment(Equipment equipment);
    Equipment findEquipmentById(int id);
    void deleteEquipment(Equipment equipment);
    List<Equipment> findAllEquipment();

    MeetingRoom saveRoomWithEquipment(MeetingRoom room, Equipment equipment);
    Reservation saveReservationWithParticipants(Reservation reservation, List<User> participants);
    List<Reservation> findReservationsByRoom(int roomId);
    List<Reservation> findReservationsByUser(int userId);
} 