package org.gleb.login_manager;

import org.gleb.login_manager.dao.UserDAOFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(UserDAOFactory.class)
public class LoginManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginManagerApplication.class, args);
	}


}
