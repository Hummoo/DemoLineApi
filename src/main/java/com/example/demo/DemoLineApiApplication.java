package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class DemoLineApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(DemoLineApiApplication.class, args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
	return new TextMessage("Resp: " + event.getMessage().getText() + " UserId : " + event.getSource().getUserId()
		+ " SenderId : " + event.getSource().getSenderId() + " Time : " + event.getTimestamp().toString());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
	System.out.println("event: " + event);
    }
}
