package message.consumer.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import message.consumer.model.HeaderModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class DataListener {

    private final Logger logger = LogManager.getLogger("DataListenerLogger");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HeaderModel mapToHeader(Object value) {

        if (value instanceof String) {
            try {
                return objectMapper.readValue((String) value, HeaderModel.class);
            } catch (JsonProcessingException e) {
                logger.error("Failed to deserialize JSON string to Form object", e);
            }
        } else if (value instanceof Map) {
            return objectMapper.convertValue(value, HeaderModel.class);
        } else {
            logger.error("Unexpected record value type: {}", value.getClass().getName());
        }

        return null;

    }

    @KafkaListener(groupId = "ProducerId", topics = {"json-topic", "another-topic"}, containerFactory = "kafkaContainerFactory")
    public void listener(ConsumerRecord<String, Object> consumerRecord) {

        Object value = consumerRecord.value();
        HeaderModel header = mapToHeader(value);

        if (header != null) {
            logger.info("Message treated is: {}", header.toMap().toString());
        } else{
            logger.info("Header is null");
        }

        logger.info("Running integration cases");



    }
}
