package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class BaseEventController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LineMessagingClient lineMessagingClient;

    protected void reply(@NotNull String replyToken, @NotNull Message message) {
	reply(replyToken, Collections.singletonList(message));
    }

    protected void reply(@NotNull String replyToken, @NotNull List<Message> messages) {
	try {
	    BotApiResponse apiResponse = lineMessagingClient.replyMessage(new ReplyMessage(replyToken, messages)).get();
	    log.info("Sent messages: {}", apiResponse);
	} catch (InterruptedException | ExecutionException e) {
	    throw new RuntimeException(e);
	}
    }

    protected void replyText(@NotNull String replyToken, @NotNull String message) {
	if (replyToken.isEmpty()) {
	    throw new IllegalArgumentException("replyToken must not be empty");
	}
	if (message.length() > 1000) {
	    message = message.substring(0, 1000 - 2) + "……";
	}
	this.reply(replyToken, new TextMessage(message));
    }

    protected static String createUri(String path) {
	return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString();
    }

}
