package pjatk.sri.director;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
class DirectorProducer {

    private final JmsTemplate jmsTemplate;

    DirectorProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void informDriver() {
        jmsTemplate.convertAndSend("pit.stop.required");
    }

    public void sendReply(ActiveMQMessage reply) {
       jmsTemplate.convertAndSend(reply.getJMSReplyTo(), reply);
    }
}
