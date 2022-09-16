package best.project.fix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import best.project.fix.service.IMessageService;
import best.project.fix.service.MessageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MessageServiceTest {
	
	@Autowired
	IMessageService messageService;
	
	@Test
	void testSendNewOrderErrorType() {
		String response = messageService.sendNewOrderErrorType();
		log.info("sendNewOrderErrorType response = {}", response);
		assertEquals(MessageService.MESSAGE_SEND_FAIL, response);
	}
	
	@Test
	void testSendNewOrder() {
		String response = messageService.sendNewOrder("123");
		log.info("sendNewOrder response = {}", response);
		assertEquals(MessageService.MESSAGE_SEND_SUCCESS, response);
	}
}
