package com.example.demo.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.MessageImagemapAction;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class TextMessageEventController extends BaseEventController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LineMessagingClient lineMessagingClient;

    // @EventMapping
    // public TextMessage
    // handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    // TextMessageContent message = event.getMessage();
    // try {
    // handleTextContent(event.getReplyToken(), event, message);
    // } catch (Exception e) {
    // log.error(e.getMessage());
    // }
    //
    // return new TextMessage("Resp: " + event.getMessage().getText() + " UserId
    // : " + event.getSource().getUserId()
    // + " SenderId : " + event.getSource().getSenderId() + " Time : " +
    // event.getTimestamp().toString());
    // }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
	TextMessageContent message = event.getMessage();
	handleTextContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
	System.out.println("event: " + event);
    }

    private void handleTextContent(String replyToken, Event event, TextMessageContent content) throws Exception {
	String text = content.getText();

	log.info("Got text message from {}: {}", replyToken, text);
	switch (text) {
	case "aaa": {
	    this.replyText(replyToken, "AAA");
	    break;
	}
	case "profile": {
	    String userId = event.getSource().getUserId();
	    if (userId != null) {
		lineMessagingClient.getProfile(userId).whenComplete((profile, throwable) -> {
		    if (throwable != null) {
			this.replyText(replyToken, throwable.getMessage());
			return;
		    }

		    this.reply(replyToken, Arrays.asList(
			    new TextMessage("Display name: " + profile.getDisplayName()),
			    new TextMessage("Status message: " + profile.getStatusMessage()),
			    new TextMessage("Profile User id: " + profile.getUserId()),
			    new TextMessage("Source User id: " + event.getSource().getUserId()),
			    new TextMessage("Source Sender id: " + event.getSource().getSenderId())
			    ));
		});
	    } else {
		this.replyText(replyToken, "Bot can't use profile API without user ID");
	    }
	    break;
	}
	case "bye": {
	    Source source = event.getSource();
	    if (source instanceof GroupSource) {
		this.replyText(replyToken, "Leaving group");
		lineMessagingClient.leaveGroup(((GroupSource) source).getGroupId()).get();
	    } else if (source instanceof RoomSource) {
		this.replyText(replyToken, "Leaving room");
		lineMessagingClient.leaveRoom(((RoomSource) source).getRoomId()).get();
	    } else {
		this.replyText(replyToken, "Bot can't leave from 1:1 chat");
	    }
	    break;
	}
	case "ชิ่วๆ": {
	    Source source = event.getSource();
	    if (source instanceof GroupSource) {
		this.replyText(replyToken, "ออกกรุปละจ้า บรัย");
		lineMessagingClient.leaveGroup(((GroupSource) source).getGroupId()).get();
	    } else if (source instanceof RoomSource) {
		this.replyText(replyToken, "ออกห้องละจ้า บรัย");
		lineMessagingClient.leaveRoom(((RoomSource) source).getRoomId()).get();
	    } else {
		this.replyText(replyToken, "กุออกไม่ได้ ฟาย");
	    }
	    break;
	}
	case "confirm": {
	    ConfirmTemplate confirmTemplate = new ConfirmTemplate("Do it?", new MessageAction("Yes", "Yes!"),
		    new MessageAction("No", "No!"));
	    TemplateMessage templateMessage = new TemplateMessage("Confirm alt text", confirmTemplate);
	    this.reply(replyToken, templateMessage);
	    break;
	}
	case "buttons": {
	    String imageUrl = createUri("/static/buttons/1040.jpg");
	    ButtonsTemplate buttonsTemplate = new ButtonsTemplate(imageUrl, "My button sample", "Hello, my button",
		    Arrays.asList(new URIAction("Go to line.me", "https://line.me"),
			    new PostbackAction("Say hello1", "hello こんにちは"),
			    new PostbackAction("言 hello2", "hello こんにちは", "hello こんにちは"),
			    new MessageAction("Say message", "Rice=米")));
	    TemplateMessage templateMessage = new TemplateMessage("Button alt text", buttonsTemplate);
	    this.reply(replyToken, templateMessage);
	    break;
	}
	case "carousel": {
	    String imageUrl = createUri("/static/buttons/1040.jpg");
	    CarouselTemplate carouselTemplate = new CarouselTemplate(Arrays.asList(
		    new CarouselColumn(imageUrl, "hoge", "fuga",
			    Arrays.asList(new URIAction("Go to line.me", "https://line.me"),
				    new PostbackAction("Say hello1", "hello こんにちは"))),
		    new CarouselColumn(imageUrl, "hoge", "fuga",
			    Arrays.asList(new PostbackAction("言 hello2", "hello こんにちは", "hello こんにちは"),
				    new MessageAction("Say message", "Rice=米")))));
	    TemplateMessage templateMessage = new TemplateMessage("Carousel alt text", carouselTemplate);
	    this.reply(replyToken, templateMessage);
	    break;
	}
	case "imagemap":
	    // this.replyText(replyToken, "Moo1 : " +
	    // createUri("/static/rich"));
	    this.replyText(replyToken, "Moo1 : " + createUri("/static/buttons/1040.jpg"));
	    this.reply(replyToken,
		    new ImagemapMessage(createUri("/static/rich"), "This is alt text", new ImagemapBaseSize(1040, 1040),
			    Arrays.asList(
				    new URIImagemapAction("https://store.line.me/family/manga/en",
					    new ImagemapArea(0, 0, 520, 520)),
				    new URIImagemapAction("https://store.line.me/family/music/en",
					    new ImagemapArea(520, 0, 520, 520)),
				    new URIImagemapAction("https://store.line.me/family/play/en",
					    new ImagemapArea(0, 520, 520, 520)),
				    new MessageImagemapAction("URANAI!", new ImagemapArea(520, 520, 520, 520)))));
	    break;
	default:
	    log.info("Returns echo message {}: {}", replyToken, text);
	    this.replyText(replyToken, text);
	    break;
	}
    }

    protected static String createUri(String path) {
	return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString();
    }
}
