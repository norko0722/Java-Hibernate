package sk.kasv.balucha.hibernate.dao;

import sk.kasv.balucha.hibernate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
} 