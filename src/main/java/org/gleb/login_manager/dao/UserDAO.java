package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;

import java.util.List;

/**
 * User objects dispatcher
 */
public interface UserDAO {
    /**
     * Add a nuw user.
     * @param userName user name
     * @param password user password
     * @return a new <code>User</code> instance registered in the service
     */
    User createUser(String userName, String password);

    /**
     * Get all registered users
     * @return list of users
     */
    List<User> getUsers();

    /**
     * Find user by user name
     * @param userName user name
     * @return user with the specified user name
     */
    User getUserByName(String userName);

    /**
     * Find user by id
     * @param id user id
     * @return user with the specified id
     */
    User getUserById(long id);
}
