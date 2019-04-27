package pjatk.sri.mechanic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
class MechanicListener {

    @JmsListener(destination = "monitoring.serious.damage", containerFactory = "topicListenerFactory")
    void onMessage(Message<Map<String, Object>> message) {
        log.info("Formula have a problem, mechanic are ready to work");
    }
}
