package br.com.child.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ChildManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildManagerApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
