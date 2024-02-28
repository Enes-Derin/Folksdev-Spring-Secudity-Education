package com.folksdevsecurity.basicauth;

import com.folksdevsecurity.basicauth.dto.CreateUserRequest;
import com.folksdevsecurity.basicauth.model.Role;
import com.folksdevsecurity.basicauth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;


@SpringBootApplication
public class BasicAuthApplication implements CommandLineRunner {

	private final UserService userService;

	public BasicAuthApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
	}

	private void createDummyData(){
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.name("Mod")
				.username("mod")
				.password("123")
				.authorities(Set.of(Role.ROLE_MOD))
				.build();
		userService.createUser(createUserRequest);

		CreateUserRequest createUserRequest1= CreateUserRequest.builder()
				.name("User")
				.username("user")
				.password("123")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.createUser(createUserRequest1);

		CreateUserRequest createUserRequest2= CreateUserRequest.builder()
				.name("Admin")
				.username("admin")
				.password("123")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.createUser(createUserRequest2);

		CreateUserRequest createUserRequest3= CreateUserRequest.builder()
				.name("Fsk")
				.username("fsk")
				.password("123")
				.authorities(Set.of(Role.ROLE_FSK))
				.build();
		userService.createUser(createUserRequest3);
	}
}
