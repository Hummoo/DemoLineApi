package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.MessageService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class JoinAndFollowEventController extends BaseEventController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @EventMapping
    public void handleUnfollowEvent(UnfollowEvent event) {
	log.info("unfollowed this bot: {}", event);
	
	String message = "[Unfollow]"
		+ "\n SenderId : " + event.getSource().getSenderId()
		+ "\n UserId : " + event.getSource().getUserId()
		+ "\n Time: " + event.getTimestamp();
	messageService.escalate(message);
    }

    @EventMapping
    public void handleFollowEvent(FollowEvent event) {
	String replyToken = event.getReplyToken();
	
	String message = "[Follow]"
		+ "\n SenderId : " + event.getSource().getSenderId()
		+ "\n UserId : " + event.getSource().getUserId()
		+ "\n Time: " + event.getTimestamp();
	messageService.escalate(message);
	
	this.replyText(replyToken, "Got followed event");
    }

    @EventMapping
    public void handleJoinEvent(JoinEvent event) {
	String replyToken = event.getReplyToken();
	
	String message = "[Join]"
		+ "\n SenderId : " + event.getSource().getSenderId()
		+ "\n UserId : " + event.getSource().getUserId()
		+ "\n Time: " + event.getTimestamp();
	messageService.escalate(message);
	
	this.replyText(replyToken, "Joined " + event.getSource());
    }
}
