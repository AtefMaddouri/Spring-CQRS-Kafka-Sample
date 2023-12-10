package tn.esprit.msproductcommand.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.dto.Event;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, Event> kafkaTemplate;
    private String topic = "product-event-topic";

    public void produceEvent(Event productEvent) {
        kafkaTemplate.send(this.topic, productEvent.type().toString() , productEvent);
    }

}
