package pjatk.sri.audit.audit;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.TopicConnection;

@Slf4j
@Component
public class AuditListener {

    @JmsListener(destination = "formula.info")
    public void onMessage(Message message) {
        log.info(message.getPayload().toString());
    }
}
