package com.hermes.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumer {

    @RabbitListener(queues = "codingWorldQueue")
    public void receiveCodingWorldNewsMessage(final String message){
        log.info(message);
    }

    @RabbitListener(queues = "yozmQueue")
    public void receiveyozmNewsMessage(final String message){
        log.info(message);
    }

}
