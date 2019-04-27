package pjatk.sri.formula;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
class FormulaInfoProducer {

    private static final String QUEUE_NAME = "formula.info";

    private final JmsTemplate jmsTemplate;

    FormulaInfoProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    void sendInfo(FormulaInfoDTO dto) {
        jmsTemplate.convertAndSend(QUEUE_NAME, dto.asMap());
    }


}
