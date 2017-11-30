package com.example.demo.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.controller.BaseEventController;
import com.example.demo.service.MessageService;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class Scheduler extends BaseEventController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static Boolean IS_SCHEDULING_ENABLED = false;

	private static final String BOT_USER_ID = "Ud23adb9abc452bac8943c4f535c8901a";
	private static final String GROUP_USER_ID = "C5a9cb6b2ab498a10ba68899499b305e3";

	@Autowired
	private MessageService messageService;

	// @Scheduled(fixedDelay = 3600000)
	// @Scheduled(fixedDelay = 10000)
	private void testScheduleMulticast() {
		log.info("testScheduleMulticast");
		if (IS_SCHEDULING_ENABLED) {
			Set<String> to = new HashSet<>();
			to.add(BOT_USER_ID);

			List<Message> messages = new ArrayList<>();
			messages.add(new TextMessage("[Multicast] อุ๊ด อุ๊ด ขณะนี้เวลา " + dateFormat.format(new Date())));

			messageService.multicast(to, messages);
		}
	}

	@Scheduled(cron = "0 0 * * * *")
	private void testSchedulePush() {
		log.info("testSchedulePush");
		if (IS_SCHEDULING_ENABLED) {
			PushMessage pushMessage = new PushMessage(GROUP_USER_ID, new TextMessage("[Push] อุ๊ด อุ๊ด ขณะนี้เวลา " + dateFormat.format(new Date())));
			messageService.push(pushMessage);
		}
	}

	/*
	 * Old code
	 * 
	 * @Scheduled(fixedDelay = 5000) private void multicast() throws Exception { if (IS_SCHEDULING_ENABLED) { String USER_AGENT = "Mozilla/5.0";
	 * 
	 * String url = "https://api.line.me/v2/bot/message/multicast";
	 * 
	 * CloseableHttpClient httpclient = HttpClients.createDefault(); HttpPost post = new HttpPost(url);
	 * 
	 * // add header post.setHeader("Content-Type", "application/json"); post.setHeader("Authorization",
	 * "Bearer {Mg6Vv2Hee5fvZ93xwC4XB2R0eM7pbUY1pMWin++zCkUDxeaVpx9ohVPE4JMD3talyUpBtvj7jm6UWC4Podh4E+nfDmyPaYXiiHCAjMVm+zpgtWN7vNrS0j4VSNWoA7R7lw+B1m5IjeRdC04LcZTt8AdB04t89/1O/w1cDnyilFU=}" );
	 * 
	 * // String message = "{\"to\": // [\"Ud23adb9abc452bac8943c4f535c8901a\"],\"messages\": [{\"type\": // \"text\",\"text\": \"สวัสดี, หมู\"},{\"type\": \"text\",\"text\": // \"Hello, world2\"}]}";
	 * Scheduler x = new Scheduler(); String message = x.getFile("multicast_example.json").replaceAll("Moo1", "ขณะนี้เวลา " + dateFormat.format(new Date()));
	 * 
	 * StringEntity entity = new StringEntity(message, ContentType.create("text/plain", "UTF-8"));
	 * 
	 * post.setEntity(entity);
	 * 
	 * CloseableHttpResponse response = httpclient.execute(post); System.out.println("\nSending 'POST' request to URL : " + url); System.out.println("Post parameters : " + post.getEntity());
	 * System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
	 * 
	 * BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	 * 
	 * StringBuffer result = new StringBuffer(); String line = ""; while ((line = rd.readLine()) != null) { result.append(line); }
	 * 
	 * System.out.println(result.toString()); } }
	 * 
	 * @Scheduled(fixedDelay = 10000) private void pushToGroup() throws Exception { if (IS_SCHEDULING_ENABLED) { String USER_AGENT = "Mozilla/5.0";
	 * 
	 * String url = "https://api.line.me/v2/bot/message/push";
	 * 
	 * CloseableHttpClient httpclient = HttpClients.createDefault(); HttpPost post = new HttpPost(url);
	 * 
	 * // add header post.setHeader("Content-Type", "application/json"); post.setHeader("Authorization",
	 * "Bearer {Mg6Vv2Hee5fvZ93xwC4XB2R0eM7pbUY1pMWin++zCkUDxeaVpx9ohVPE4JMD3talyUpBtvj7jm6UWC4Podh4E+nfDmyPaYXiiHCAjMVm+zpgtWN7vNrS0j4VSNWoA7R7lw+B1m5IjeRdC04LcZTt8AdB04t89/1O/w1cDnyilFU=}" );
	 * 
	 * // String message = "{\"to\": // [\"Ud23adb9abc452bac8943c4f535c8901a\"],\"messages\": [{\"type\": // \"text\",\"text\": \"สวัสดี, หมู\"},{\"type\": \"text\",\"text\": // \"Hello, world2\"}]}";
	 * Scheduler x = new Scheduler(); String message = x.getFile("push_example.json").replaceAll("Moo1", "อุ๊ด อุ๊ด ขณะนี้เวลา " + dateFormat.format(new Date()));
	 * 
	 * StringEntity entity = new StringEntity(message, ContentType.create("text/plain", "UTF-8"));
	 * 
	 * post.setEntity(entity);
	 * 
	 * CloseableHttpResponse response = httpclient.execute(post); System.out.println("\nSending 'POST' request to URL : " + url); System.out.println("Post parameters : " + post.getEntity());
	 * System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
	 * 
	 * BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	 * 
	 * StringBuffer result = new StringBuffer(); String line = ""; while ((line = rd.readLine()) != null) { result.append(line); }
	 * 
	 * System.out.println(result.toString()); } }
	 * 
	 * public String getFile(String fileName) {
	 * 
	 * StringBuilder result = new StringBuilder("");
	 * 
	 * // Get file from resources folder ClassLoader classLoader = getClass().getClassLoader(); File file = new File(classLoader.getResource(fileName).getFile());
	 * 
	 * try (Scanner scanner = new Scanner(file)) {
	 * 
	 * while (scanner.hasNextLine()) { String line = scanner.nextLine(); result.append(line).append("\n"); }
	 * 
	 * scanner.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return result.toString(); }
	 */
}
