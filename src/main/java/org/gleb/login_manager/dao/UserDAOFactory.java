package org.gleb.login_manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UserDAOFactory {

    @Value("${persistence.mode}")
    private String persistenceMode;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public UserDAO getUserDAO() {
        if (persistenceMode == null) return null;
        switch (persistenceMode) {
            case("inmem_db"):
                return new H2UserDAO(jdbcTemplate);
            case("inmem_array"):
                return new ArrayListUserDAO();
            case("PostgreSQL"):
                return new PostgreSQLUserDAO();
        }
        return null;
    }


}
