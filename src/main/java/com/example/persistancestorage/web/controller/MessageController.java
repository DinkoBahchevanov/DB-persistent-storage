package com.example.persistancestorage.web.controller;

import com.example.persistancestorage.web.dtos.ProductDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ProductDto getMessage(final ProductDto message) throws InterruptedException {
        Thread.sleep(1000);
        return message;
    }
}
