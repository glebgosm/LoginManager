package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("#{'${persistence.mode}'.equalsIgnoreCase('PostgreSQL')}")
public class PostgreSQLUserDAO implements UserDAO {
    @Override
    public User createUser(String userName, String password) {
        return null;
    }

    @Override
    public Iterable<User> getUsers() {
        return null;
    }

    @Override
    public Iterable<User> getUserByName(String userName) {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }
}
