package com.fatec_api3.fatec_api3;

// import java.time.LocalDateTime;

// import com.fatec_api3.fatec_api3.entities.User;
// import com.fatec_api3.fatec_api3.enums.Gender;
// import com.fatec_api3.fatec_api3.repositories.UserRepository;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FatecApi3Application {

	public static void main(String[] args) {
		SpringApplication.run(FatecApi3Application.class, args);
	}

	// @Bean
	// CommandLineRunner runner(UserRepository repository) {
	// 	return args -> {
	// 		User user = new User(
	// 			"Gabriel",
	// 			"Camargo",
	// 			"gabriel.cleite@outlook.com",
	// 			"1234",
	// 			Gender.MALE,
	// 			"Brazil",
	// 			"12211-000",
	// 			"São José dos Campos",
	// 			LocalDateTime.now(),
	// 			LocalDateTime.now()
	// 		);

			// repository.insert(user);
	// 	};
	// }
}
