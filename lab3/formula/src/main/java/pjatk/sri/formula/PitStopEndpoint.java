package pjatk.sri.formula;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
public class PitStopEndpoint {

    private final PitStopService service;

    public PitStopEndpoint(PitStopService service) {
        this.service = service;
    }

    @RequestMapping("/pit-stop/{number}")
    public ResponseEntity wantPitStop(@PathVariable("number") Integer number) {
        boolean approve = false;
        try {
            approve = service.requestPitStop(number);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        if (approve) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
