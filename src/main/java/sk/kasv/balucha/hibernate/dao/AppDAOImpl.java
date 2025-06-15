package sk.kasv.balucha.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.kasv.balucha.hibernate.entities.*;

import java.util.List;


@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveRoom(MeetingRoom room) {
        entityManager.persist(room);
    }

    @Override
    @Transactional
    public void updateRoom(MeetingRoom room) {
        entityManager.merge(room);
    }

    @Override
    public MeetingRoom findRoomById(int id) {
        return entityManager.find(MeetingRoom.class, id);
    }

    @Override
    @Transactional
    public void deleteRoom(MeetingRoom room) {
        entityManager.remove(room);
    }

    @Override
    public List<MeetingRoom> findAllRooms() {
        TypedQuery<MeetingRoom> query = entityManager.createQuery("FROM MeetingRoom", MeetingRoom.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveReservation(Reservation reservation) {
        this.entityManager.merge(reservation);
    }

    @Override
    @Transactional
    public void updateReservation(Reservation reservation) {
        entityManager.merge(reservation);
    }

    @Override
    public Reservation findReservationById(int id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    @Transactional
    public void deleteReservation(Reservation reservation) {
        entityManager.remove(reservation);
    }

    @Override
    public List<Reservation> findAllReservations() {
        TypedQuery<Reservation> query = entityManager.createQuery("FROM Reservation", Reservation.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveEquipment(Equipment equipment) {
        entityManager.persist(equipment);
    }

    @Override
    @Transactional
    public void updateEquipment(Equipment equipment) {
        entityManager.merge(equipment);
    }

    @Override
    public Equipment findEquipmentById(int id) {
        return entityManager.find(Equipment.class, id);
    }

    @Override
    @Transactional
    public void deleteEquipment(Equipment equipment) {
        entityManager.remove(equipment);
    }

    @Override
    public List<Equipment> findAllEquipment() {
        TypedQuery<Equipment> query = entityManager.createQuery("FROM Equipment", Equipment.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public MeetingRoom saveRoomWithEquipment(MeetingRoom room, Equipment equipment) {
        equipment.setMeetingRoom(room);
        entityManager.persist(equipment);
        return room;
    }

    @Override
    @Transactional
    public Reservation saveReservationWithParticipants(Reservation reservation, List<User> participants) {
        reservation.getParticipants().addAll(participants);
        entityManager.persist(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findReservationsByRoom(int roomId) {
        TypedQuery<Reservation> query = entityManager.createQuery(
            "FROM Reservation r WHERE r.meetingRoom.id = :roomId", Reservation.class);
        query.setParameter("roomId", roomId);
        return query.getResultList();
    }

    @Override
    public List<Reservation> findReservationsByUser(int userId) {
        TypedQuery<Reservation> query = entityManager.createQuery(
            "FROM Reservation r WHERE r.organizer.id = :userId", Reservation.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
} 