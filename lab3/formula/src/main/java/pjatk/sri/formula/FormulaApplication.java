package pjatk.sri.formula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pjatk.sri.formula")
public class FormulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormulaApplication.class, args);
	}

}
