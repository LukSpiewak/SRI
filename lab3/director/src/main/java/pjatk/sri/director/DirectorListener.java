package pjatk.sri.director;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
class DirectorListener {

    private final DirectorProducer producer;

    DirectorListener(DirectorProducer producer) {
        this.producer = producer;
    }

    @JmsListener(destination = "monitoring.serious.damage", containerFactory = "topicListenerFactory")
    void onSeriousDamage(Message<Map<String, Object>> message) {
        log.info("Formula have problem, inform driver that pit-stop is required");
        producer.informDriver();
    }

    @JmsListener(destination = "pit.stop.request", containerFactory = "queueListenerFactory")
    void onRequestPitStop(Message message) throws JMSException {
        ActiveMQMessage m = (ActiveMQMessage) message.getPayload();
        Destination replyChannel = m.getJMSReplyTo();

        ActiveMQMessage reply = new ActiveMQMessage();
        reply.setJMSReplyTo(replyChannel);
        reply.setJMSCorrelationID(m.getJMSCorrelationID());
        Integer param = 1;

        try {
            param = (Integer) m.getProperties().get("request");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (param % 2 == 0) {
            reply.setObjectProperty("decision", true);
        } else {
            reply.setObjectProperty("decision", false);
        }
        producer.sendReply(reply);
    }
}
