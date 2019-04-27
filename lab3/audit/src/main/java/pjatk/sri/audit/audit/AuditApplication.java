package pjatk.sri.audit.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pjatk.sri.audit.audit")
public class AuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditApplication.class, args);
	}

}
