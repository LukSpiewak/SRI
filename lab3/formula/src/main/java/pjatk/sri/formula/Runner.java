package pjatk.sri.formula;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TopicConnection;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Runner {

    private final FormulaInfoProducer producer;
    private final AtomicLong longGenerator;

    public Runner(FormulaInfoProducer producer) {
        this.producer = producer;
        longGenerator = new AtomicLong();
        run();
    }

    private void run() {
        Runnable runner = () -> {
            while (true) {
                FormulaInfoDTO dto = new FormulaInfoDTO(longGenerator.incrementAndGet(), longGenerator.incrementAndGet(), LocalDateTime.now());
                producer.sendInfo(dto);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runner);
        thread.start();
    }
}
