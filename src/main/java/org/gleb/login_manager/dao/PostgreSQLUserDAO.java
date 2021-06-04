package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;

import java.util.List;

public class PostgreSQLUserDAO implements UserDAO {
    @Override
    public User createUser(String userName, String password) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }
}
