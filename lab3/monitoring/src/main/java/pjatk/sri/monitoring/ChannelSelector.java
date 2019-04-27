package pjatk.sri.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ChannelSelector {

    private final JmsTemplate queueTemplate;
    private final JmsTemplate topicTemplate;

    public ChannelSelector(@Qualifier("queueTemplate") JmsTemplate queueTemplate, @Qualifier("topicTemplate") JmsTemplate topicTemplate) {
        this.queueTemplate = queueTemplate;
        this.topicTemplate = topicTemplate;
    }

    public void selectChannel(FormulaInfoDTO info) {
        Map<String, Object> dto = info.asMap();
        if (info.getEngineTemperature() > 30) {
            dto.put("message", "You have serious damage, go to pit stop");
            topicTemplate.convertAndSend("monitoring.serious.damage", dto);
        } else if (info.getEngineTemperature() > 20) {
            dto.put("message", "You have small problem with formula");
            queueTemplate.convertAndSend("monitoring.warning.damage", dto);
        }
    }
}
