package pjatk.sri.director;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DirectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectorApplication.class, args);
	}

}
