package tn.esprit.msproductquery.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.dto.Event;
import tn.esprit.dto.ProductDto;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProductEventHandler productEventHandler;
    private final String topic = "product-event-topic";

    @KafkaListener(topics = topic, groupId = "my-group")
    public void consume(ConsumerRecord<String,Event> consumerRecord) {

        Event event = consumerRecord.value();

        log.info("\n Consumed Event of type : {} \n published on topic at : {} \n Data value is : {}", event.type(), event.eventCreatedAt(), event.productDto() );

        switch (consumerRecord.key()) {
            case "CREATED_PRODUCT_EVENT":
                productEventHandler.handleProductCreatedEvent(event.productDto());
                break;
            case "UPDATED_PRODUCT_EVENT":
                productEventHandler.handleProductUpdatedEvent(event.productDto());
                break;
            case "DELETED_PRODUCT_EVENT":
                productEventHandler.handleProductDeletedEvent(event.productDto().id());
                break;
            default:
                log.info("Event ignored");
                break;
        }

    }
}
