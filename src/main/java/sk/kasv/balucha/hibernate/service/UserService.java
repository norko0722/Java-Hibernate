package sk.kasv.balucha.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.balucha.hibernate.dao.AppDAO;
import sk.kasv.balucha.hibernate.entities.User;

import java.util.List;

@Service
public class UserService {
    private AppDAO appDAO;

    @Autowired
    public UserService(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    public User saveUser(User user) {
        appDAO.saveUser(user);
        return user;
    }

    public User updateUser(User user) {
        appDAO.updateUser(user);
        return user;
    }

    public User findUserById(int id) {
        return appDAO.findUserById(id);
    }

    public List<User> findAllUsers() {
        return appDAO.findAllUsers();
    }

    public boolean deleteUser(int id) {
        User user = findUserById(id);
        if (user != null) {
            appDAO.deleteUser(user);
            return true;
        }
        return false;
    }
} 