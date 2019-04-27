package pjatk.sri.formula;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FormulaConsumer {

    @JmsListener(destination = "pit.stop.required", containerFactory = "queueListenerFactory")
    void onMessage() {
        log.warn("PIT STOP REQUIRED");
    }

    @JmsListener(destination = "monitoring.serious.damage", containerFactory = "topicListenerFactory")
    void seriousDamage() {
        log.error("Formula have serious problem");
    }

    @JmsListener(destination = "monitoring.warning.damage", containerFactory = "queueListenerFactory")
    void warningDamage() {
        log.warn("Formula have small problem");
    }
}

