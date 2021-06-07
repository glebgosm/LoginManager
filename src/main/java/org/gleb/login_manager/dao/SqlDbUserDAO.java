package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("#{'${persistence.mode}'.equalsIgnoreCase('sql_db')}")
public class SqlDbUserDAO implements UserDAO {

    private UserRepository userRepository;

    public SqlDbUserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String userName, String password) {
        User user = new User(userName, password);
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> getUserByName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
