package com.example.demo.carinspection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

@RestController
@RequestMapping("/carinspection/")
public class BrokerCarInspectionController {
	private static final String BOT_USER_ID = "Ud23adb9abc452bac8943c4f535c8901a";
	private static final String GROUP_USER_ID = "C5a9cb6b2ab498a10ba68899499b305e3";

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "receive", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SendCarInspectRespDto receiveCarInspectionResult(@Valid @RequestBody SendCarInspectReqDto request) throws Exception {
		Set<String> to = new HashSet<>();
		to.add(BOT_USER_ID);

		List<Message> messages = new ArrayList<>();
		messages.add(new TextMessage("[Multicast] receiveCarInspectionResult : " + new ObjectMapper().writeValueAsString(request)));

		messageService.multicast(to, messages);

		SendCarInspectRespDto result = new SendCarInspectRespDto();
		result.setTransactionId(request.getTransactionId());
		result.setResponseCode("000");
		result.setResponseDesc("Moo");
		return result;
	}

	@RequestMapping(value = "receiveSrikrung", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public CarInspectionToSrikrungRespDto receiveSrikrungCarInspectionResult(@Valid @RequestBody CarInspectionToSrikrungReqDto request) throws Exception {
		Set<String> to = new HashSet<>();
		to.add(BOT_USER_ID);

		List<Message> messages = new ArrayList<>();
		messages.add(new TextMessage("[Multicast] receiveSrikrungCarInspectionResult : " + new ObjectMapper().writeValueAsString(request)));

		messageService.multicast(to, messages);

		CarInspectionToSrikrungRespDto result = new CarInspectionToSrikrungRespDto();
		result.setSaveStatus("SUCCESS");
		result.setResSaveRemark(null);
		return result;
	}
}
