package org.gleb.login_manager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Simple username+password container + user id
 */
@Table("users")
public class User {
    @Id
    private Long id;
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.id = null;
        this.userName = userName;
        this.password = password;
    }

    @PersistenceConstructor
    public User(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
