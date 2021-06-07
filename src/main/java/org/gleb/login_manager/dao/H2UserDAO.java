package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Component
@ConditionalOnExpression("#{'${persistence.mode}'.equalsIgnoreCase('inmem_db')}")
public class H2UserDAO implements UserDAO {

    private UserRepository userRepository;

    public H2UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(String userName, String password) {
        User user = new User(userName, password);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Iterable<User> getUserByName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
