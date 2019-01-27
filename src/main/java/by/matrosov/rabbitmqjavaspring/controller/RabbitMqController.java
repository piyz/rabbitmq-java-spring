package by.matrosov.rabbitmqjavaspring.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.logging.Logger;

@Controller
public class RabbitMqController {
    private Logger logger = Logger.getLogger(RabbitMqController.class.getName());

    @Autowired
    private RabbitTemplate template;

    @RequestMapping("/")
    @ResponseBody
    public String sender() {
        String message;
        String routingKey;
        Random random = new Random();
        for (int i = 1; i < 25; i++) {
            int n = random.nextInt(i) + 1;
            message = String.valueOf(n);
            if (isPrime(n)){
                routingKey = "prime";
            }else {
                routingKey = "non-prime";
            }
            template.convertAndSend(routingKey, message);
            logger.info("[x] Sent '" + routingKey + ": " + message + "'");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {}
        }

        return "look at the console";
    }

    private boolean isPrime(int n){
        if (n % 2 == 0) return false;
        for (int i = 3; i*i <= n; i+= 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

}