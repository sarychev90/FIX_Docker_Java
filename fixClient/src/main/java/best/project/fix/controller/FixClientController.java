package best.project.fix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import best.project.fix.service.IMessageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fix")
public class FixClientController {

	@Autowired
	IMessageService messageService;
	
	@PostMapping("/test/{testReqID}")
	public String simpleTestMessage(@PathVariable("testReqID") String testReqID) throws Exception {
		String response = messageService.sendTestMessage(testReqID);
		log.info("simpleTestMessage response = {}", response);
		return response;
	}
	
	@PostMapping("/test/error")
	public String simpleTestMessageError() throws Exception {
		String response = messageService.sendTestMessageError();
		log.info("simpleTestMessage response = {}", response);
		return response;
	}
	
	@PostMapping("/order/new/{clOrdID}")
	public String simpleOrder(@PathVariable("clOrdID") String clOrdID) throws Exception {
		String response = messageService.sendNewOrder(clOrdID);
		log.info("simpleOrder response = {}", response);
		return response;
	}
	
	@PostMapping("/order/new/error")
	public String simpleOrderError() throws Exception {
		String response = messageService.sendNewOrderError();
		log.info("simpleOrderError response = {}", response);
		return response;
	}
}
