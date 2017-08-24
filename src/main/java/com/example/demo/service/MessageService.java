package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Multicast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

@Service
public class MessageService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private static String ADMIN_USER_ID = "C5a9cb6b2ab498a10ba68899499b305e3";

	@Autowired
	private LineMessagingClient lineMessagingClient;

	public BotApiResponse multicast(Set<String> to, List<Message> messages) {
		log.info("multicast");

		Multicast multicast = new Multicast(to, messages);
		try {
			BotApiResponse response = lineMessagingClient.multicast(multicast).get();
			return response;
		} catch (Exception e) {
			String errorMessage = "Error: " + e.getMessage() + "\n" + "To: " + to.toString() + "\n" + "Messages: " + messages.toString();
			escalate(errorMessage);
		}
		return null;
	}

	public BotApiResponse push(PushMessage pushMessage) {
		log.info("push");

		try {
			BotApiResponse response = lineMessagingClient.pushMessage(pushMessage).get();
			return response;
		} catch (Exception e) {
			String errorMessage = "Error: " + e.getMessage() + "\n" + "To: " + pushMessage.getTo() + "\n" + "Messages: " + pushMessage.getMessages().toString();
			escalate(errorMessage);
		}
		return null;
	}

	public void escalate(String message) {
		log.info("escalate");

		List<Message> messages = new ArrayList<>();
		messages.add(new TextMessage(message));

		PushMessage pushMessage = new PushMessage(ADMIN_USER_ID, new TextMessage(message));
		try {
			BotApiResponse response = lineMessagingClient.pushMessage(pushMessage).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
