package springSecurity.login;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import springSecurity.login.Entity.Role;
import springSecurity.login.Entity.User;
import springSecurity.login.Repository.RoleRepository;
import springSecurity.login.Repository.UserRepository;
import springSecurity.login.dto.UserRequest;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args ->{
			Role role = new Role(0, "USER");
			roleRepository.save(role);
			Role roleAdm = new Role(0,"ADMIN");
			roleRepository.save(roleAdm);

			User user = new User("user", passwordEncoder.encode("user"), "user@gmail.com");
			user.setRole(role);
			userRepository.save(user);
			User userAdm = new User("admin", passwordEncoder.encode("admin"), "admin@gmail.com");
			userAdm.setRole(roleAdm);
			userRepository.save(userAdm);
		};
	}

}
