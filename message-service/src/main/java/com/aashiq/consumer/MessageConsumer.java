package com.aashiq.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageConsumer {

    public static final String QUEUE = "MyQueue";


    @RabbitListener(queues = QUEUE)
    public void   consumeMessage(com.aashiq.entity.User user) {
        System.out.println("Message received from queue::::: " + user.toString());
    }


}
