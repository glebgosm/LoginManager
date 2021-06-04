package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User dispatcher which stores registered users in an <code>ArrayList</code>
 */
public class ArrayListUserDAO implements UserDAO {

    private List<User> users = new ArrayList<>();

    private long userCount = 0;

    @Override
    public User createUser(String userName, String password) {
        userCount++;
        User user = new User(userCount, userName,password);
        users.add(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserByName(String userName) {
        if (userName == null || userName.isBlank()) return null;
        List<User> userList =
                users.stream()
                     .filter(user -> userName.equals(user.getUserName()))
                     .collect(Collectors.toList());
        if (userList.size() == 0) return null;
        if (userList.size()>1) {
            // TODO log error "multiple users with the same name"
        }
        return userList.get(0);
    }

    @Override
    public User getUserById(long id) {
        if (id < 0) return null;
        List<User> userList =
                users.stream()
                     .filter(user -> id == user.getId())
                     .collect(Collectors.toList());
        if (userList.size() == 0) return null;
        if (userList.size()>1) {
            // TODO log error "multiple users with the same id"
        }
        return userList.get(0);
    }

}
