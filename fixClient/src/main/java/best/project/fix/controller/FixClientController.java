package best.project.fix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import best.project.fix.service.IMessageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/fix")
public class FixClientController implements IFixClientService {

	@Autowired
	IMessageService messageService;
	
	@Override
	@ApiOperation(value = "Send test message with 'testReqID'")
	@PostMapping("/test/{testReqID}")
	public String simpleTestMessage(@PathVariable("testReqID") String testReqID) throws Exception {
		String response = messageService.sendTestMessage(testReqID);
		log.info("simpleTestMessage response = {}", response);
		return response;
	}
	
	@Override
	@ApiOperation(value = "Send test message with wrong data")
	@PostMapping("/test/error")
	public String simpleTestMessageError() throws Exception {
		String response = messageService.sendTestMessageError();
		log.info("simpleTestMessage response = {}", response);
		return response;
	}
	
	@Override
	@ApiOperation(value = "Send new order message with 'clOrdID'")
	@PostMapping("/order/new/{clOrdID}")
	public String simpleOrder(@PathVariable("clOrdID") String clOrdID) throws Exception {
		String response = messageService.sendNewOrder(clOrdID);
		log.info("simpleOrder response = {}", response);
		return response;
	}
	
	@Override
	@ApiOperation(value = "Send new order message with wrong data")
	@PostMapping("/order/new/error")
	public String simpleOrderError() throws Exception {
		String response = messageService.sendNewOrderError();
		log.info("simpleOrderError response = {}", response);
		return response;
	}
	
	@Override
	@ApiOperation(value = "Send new order message with wrong order type")
	@PostMapping("/order/new/wrongtype")
	public String simpleOrderWrongType() throws Exception {
		String response = messageService.sendNewOrderErrorType();
		log.info("simpleOrderWrongType response = {}", response);
		return response;
	}
}
