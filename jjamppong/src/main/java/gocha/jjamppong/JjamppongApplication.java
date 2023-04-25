package gocha.jjamppong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JjamppongApplication {

	public static void main(String[] args) {
		SpringApplication.run(JjamppongApplication.class, args);
	}

}
