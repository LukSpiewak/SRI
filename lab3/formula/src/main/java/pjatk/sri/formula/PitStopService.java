package pjatk.sri.formula;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Service
class PitStopService {

    private final ActiveMQConnectionFactory connectionFactory;

    PitStopService(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public boolean requestPitStop(Integer number) throws JMSException {
        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = new ActiveMQQueue("pit.stop.request");
        MessageProducer producer = session.createProducer(destination);

        Destination replyDestination = new ActiveMQQueue("pit.stop.reply");

        Message message = session.createMessage();
        message.setIntProperty("request", number);
        message.setJMSReplyTo(replyDestination);
        message.setJMSCorrelationID(Long.toHexString(new Random(System.currentTimeMillis()).nextLong()));

        MessageConsumer replyConsumer = session.createConsumer(replyDestination);
        producer.send(message);

        Message replyMessage = replyConsumer.receive();
        Map properties = null;
        try {
            properties = ((ActiveMQMessage) replyMessage).getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.stop();
        replyConsumer.close();
        return (Boolean) properties.get("decision");
//        return false;
    }
}
