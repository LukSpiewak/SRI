package pl.pja.sri.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import pl.pja.sri.infrastructure.rest.UserController;

@Component
public class EndpointRegister extends ResourceConfig {

    public EndpointRegister() {
        register(UserController.class);
    }

}
