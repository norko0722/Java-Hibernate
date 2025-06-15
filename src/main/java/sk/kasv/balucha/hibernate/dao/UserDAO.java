package sk.kasv.balucha.hibernate.dao;

import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.entities.Reservation;
import java.util.List;

public interface UserDAO {
    void save(User user);
    void update(User user);
    User findById(int id);
    User findByEmail(String email);
    List<User> findByRole(String role);
    boolean deleteById(int id);
    List<User> findAll();
    boolean existsById(int id);
    
    List<Reservation> getUserReservations(int userId);
    List<Reservation> getUserOrganizedReservations(int userId);
    List<Reservation> getUserParticipatingReservations(int userId);
} 