package sk.kasv.balucha.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.entities.Reservation;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
            "FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public List<User> findByRole(String role) {
        TypedQuery<User> query = entityManager.createQuery(
            "FROM User WHERE role = :role", User.class);
        query.setParameter("role", role);
        return query.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public boolean existsById(int id) {
        return findById(id) != null;
    }

    @Override
    public List<Reservation> getUserReservations(int userId) {
        TypedQuery<Reservation> query = entityManager.createQuery(
            "FROM Reservation r WHERE r.organizer.id = :userId OR :userId MEMBER OF r.participants", 
            Reservation.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Reservation> getUserOrganizedReservations(int userId) {
        TypedQuery<Reservation> query = entityManager.createQuery(
            "FROM Reservation WHERE organizer.id = :userId", Reservation.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Reservation> getUserParticipatingReservations(int userId) {
        TypedQuery<Reservation> query = entityManager.createQuery(
            "FROM Reservation r WHERE :userId MEMBER OF r.participants", 
            Reservation.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
} 