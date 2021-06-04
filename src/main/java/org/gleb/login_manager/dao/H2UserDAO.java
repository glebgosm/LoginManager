package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class H2UserDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_ADD_USER = "INSERT INTO users (user_name,password) VALUES (?,?)";
    private final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private final String SQL_GET_USER_BY_NAME = "SELECT * FROM users WHERE user_name = ?";
    private final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

    private RowMapper<User> rowMapper =
            (rs,rowNum) -> {
                User user = new User(rs.getInt("id"),
                                     rs.getString("user_name"),
                                     rs.getString("password"));
                return user;
            };

    public H2UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public User createUser(String userName, String password) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, userName);
                    ps.setString(2, password);
                    return ps;
                }, keyHolder);
        if (keyHolder.getKeys() != null) {
            Long primaryKey = keyHolder.getKeyAs(Long.class);
            User user = new User(primaryKey, userName, password);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, rowMapper);
    }

    @Transactional
    @Override
    public User getUserByName(String userName) {
        List<User> users =
            jdbcTemplate.query((connection) -> {
                PreparedStatement ps = connection.prepareStatement(SQL_GET_USER_BY_NAME);
                ps.setString(1,userName);
                return ps;
            }, rowMapper);
        if (!users.isEmpty())
            return users.get(0);
        return null;
    }

    @Override
    public User getUserById(long id) {
        List<User> users =
                jdbcTemplate.query((connection) -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_GET_USER_BY_ID);
                    ps.setLong(1,id);
                    return ps;
                }, rowMapper);
        if (!users.isEmpty())
            return users.get(0);
        return null;
    }
}
