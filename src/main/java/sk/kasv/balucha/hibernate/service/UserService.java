package sk.kasv.balucha.hibernate.service;

import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.dao.UserDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userDAO.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userDAO.save(user);
    }

    public void deleteUser(Long id) {
        if (!userDAO.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userDAO.deleteById(id);
    }
} 