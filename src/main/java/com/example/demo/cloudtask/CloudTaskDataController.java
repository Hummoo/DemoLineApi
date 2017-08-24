package com.example.demo.cloudtask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.carinspection.CarInspectionToSrikrungRespDto;
import com.example.demo.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

@RestController
@RequestMapping("/cloudtask/")
public class CloudTaskDataController {
	private static final String BOT_USER_ID = "Ud23adb9abc452bac8943c4f535c8901a";
	private static final String GROUP_USER_ID = "C5a9cb6b2ab498a10ba68899499b305e3";

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DataDto> generateSampleData() {
		int loop = Integer.parseInt(RandomStringUtils.randomNumeric(1));

		if (loop < 1) {
			loop = 1;
		}

		List<DataDto> dataList = new ArrayList<>();
		for (int i = 0; i < loop; i++) {
			DataDto data = new DataDto();
			data.setName(RandomStringUtils.randomAlphabetic(2));
			data.setValue(RandomStringUtils.randomNumeric(2));
			dataList.add(data);
		}

		return dataList;
	}

	@RequestMapping(value = "/receive", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataDto receiveData(@RequestBody List<DataDto> dataList) {
		DataDto result = new DataDto();
		try {
			String dataStr = new ObjectMapper().writeValueAsString(dataList);

			Set<String> to = new HashSet<>();
			to.add(BOT_USER_ID);
			List<Message> messages = new ArrayList<>();
			messages.add(new TextMessage("[Multicast] receiveCloudTask : " + dataStr));
			messageService.multicast(to, messages);

			PushMessage pushMessage = new PushMessage(GROUP_USER_ID, new TextMessage("[Push] receiveCloudTask : " + dataStr));
			messageService.push(pushMessage);

			result.setName("STATUS");
			result.setValue("SUCCESS");
		} catch (JsonProcessingException e) {
			result.setName("STATUS");
			result.setValue("FAIL");
		}

		return result;
	}
}
