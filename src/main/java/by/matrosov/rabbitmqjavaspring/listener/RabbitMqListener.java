package by.matrosov.rabbitmqjavaspring.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMqListener {
    private Logger logger = Logger.getLogger(RabbitMqListener.class.getName());

    @RabbitListener(queues = "queue")
    public void onMessage(String message) {
        logger.info(" [x] Received from queue: " + message);
    }
}
