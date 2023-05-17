package com.example.store.consumer;

import com.example.store.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StoreConsumer {
    @RabbitListener(queues = AppConfig.QUEUE)
    public void listener(@Payload String message) {
        log.info("Store Consumer message - {}", message );
    }

}
