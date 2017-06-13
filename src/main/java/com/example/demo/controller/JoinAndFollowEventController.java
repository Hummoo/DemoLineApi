package com.example.demo.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.MessageService;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
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
	
	escalateUnfollowMessage(event);
    }

    @EventMapping
    public void handleFollowEvent(FollowEvent event) {
	String replyToken = event.getReplyToken();
	
	escalateFollowMessage(event);
	
	this.replyText(replyToken, "Got followed event");
    }

    @EventMapping
    public void handleJoinEvent(JoinEvent event) {
	String replyToken = event.getReplyToken();

	String registerSite = "https://thawing-escarpment-39910.herokuapp.com/lineGroup/register/"
		+ event.getSource().getSenderId();

	ButtonsTemplate buttonsTemplate = new ButtonsTemplate(null, "AZCP Admin Menu", "Description",
		Arrays.asList(new URIAction("Register Line Group", registerSite)));
	TemplateMessage templateMessage = new TemplateMessage("Button alt text", buttonsTemplate);
	this.reply(replyToken, templateMessage);

	escalateJoinMessage(event);

	this.replyText(replyToken, "Joined " + event.getSource());
    }
    
    private void escalateUnfollowMessage(UnfollowEvent event) {
	String message = "[Unfollow]"
		+ "\n SenderId : " + event.getSource().getSenderId()
		+ "\n UserId : " + event.getSource().getUserId()
		+ "\n Time: " + event.getTimestamp();
	messageService.escalate(message);
    }
    
    private void escalateFollowMessage(FollowEvent event) {
	String message = "[Follow]"
		+ "\n SenderId : " + event.getSource().getSenderId()
		+ "\n UserId : " + event.getSource().getUserId()
		+ "\n Time: " + event.getTimestamp();
	messageService.escalate(message);
    }

    private void escalateJoinMessage(JoinEvent event) {
	String message = "[Join]"
		+ "\n SenderId : " + event.getSource().getSenderId()
		+ "\n UserId : " + event.getSource().getUserId()
		+ "\n Time: " + event.getTimestamp();
	messageService.escalate(message);
    }
}
