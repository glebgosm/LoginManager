package org.gleb.login_manager.dao;

import org.gleb.login_manager.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT * FROM \"users\" WHERE user_name = :userName")
    Iterable<User> findByName(@Param("userName") String userName);

    @Query("SELECT * FROM \"users\" WHERE id = :userId")
    User findById(@Param("userId") String id);

}
