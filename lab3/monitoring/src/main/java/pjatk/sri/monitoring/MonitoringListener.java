package pjatk.sri.monitoring;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MonitoringListener {

    private final ChannelSelector channelSelector;

    MonitoringListener(ChannelSelector channelSelector) {
        this.channelSelector = channelSelector;
    }

    @JmsListener(containerFactory = "topicListenerFactory", destination = "formula.info")
    public void onMessage(Message<Map<String,Object>> message) {
        channelSelector.selectChannel(FormulaInfoDTO.creafeFromMap(message.getPayload()));
    }
}
