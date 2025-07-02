package com.cybage.kafka_consumer_example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "kafka-example") //name should be same
    public void consume(String message){
        logger.info("consume consume the message {} ",message);
    }


}
