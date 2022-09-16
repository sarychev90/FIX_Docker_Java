package best.project.fix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.allune.quickfixj.spring.boot.starter.template.QuickFixJTemplate;
import lombok.extern.slf4j.Slf4j;
import quickfix.Message;
import quickfix.Message.Header;
import quickfix.field.BeginString;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.MsgType;
import quickfix.field.OrdType;
import quickfix.field.SenderCompID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TargetCompID;
import quickfix.field.TestReqID;

@Service
@Slf4j
public class MessageService implements IMessageService {
	
	public static final String MESSAGE_SEND_FAIL = "Message send fail!";
	public static final String MESSAGE_SEND_SUCCESS = "Message send successfully!";
	
	@Autowired
	QuickFixJTemplate quickFixJTemplate;
	
	@Override
	public String sendTestMessage(String testReqID) {
		String response = MESSAGE_SEND_SUCCESS;
		try {
			Message message = new Message();
			Header header = message.getHeader();
			header.setField(new BeginString("FIX.4.1"));
			header.setField(new SenderCompID("CLIENTFIX"));
			header.setField(new TargetCompID("EXEC"));
			header.setField(new MsgType(MsgType.TEST_REQUEST));
			message.setField(new TestReqID(testReqID)); //required=Y
			quickFixJTemplate.send(message);
		} catch (Exception e) {
			log.error("sendTestMessage exception: {}", e.getMessage(), e);
			response = MESSAGE_SEND_FAIL;
		}
		return response;
	}
	
	@Override
	public String sendTestMessageError() {
		String response = MESSAGE_SEND_SUCCESS;
		try {
			Message message = new Message();
			Header header = message.getHeader();
			header.setField(new BeginString("FIX.4.1"));
			header.setField(new SenderCompID("CLIENTFIX"));
			header.setField(new TargetCompID("EXEC"));
			header.setField(new MsgType(MsgType.TEST_REQUEST));
			quickFixJTemplate.send(message);
		} catch (Exception e) {
			log.error("sendTestMessageError exception: {}", e.getMessage(), e);
			response = MESSAGE_SEND_FAIL;
		}
		return response;
	}
	
	@Override
	public String sendNewOrder(String clOrdID) {
		String response = MESSAGE_SEND_SUCCESS;
		try {
			Message message = new Message();
			Header header = message.getHeader();
			header.setField(new BeginString("FIX.4.1"));
			header.setField(new SenderCompID("CLIENTFIX"));
			header.setField(new TargetCompID("EXEC"));
			header.setField(new MsgType(MsgType.ORDER_SINGLE)); //D
			message.setField(new ClOrdID(clOrdID)); //required=Y
			message.setField(new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION));
			message.setField(new Symbol("LNUX"));
			message.setField(new Side(Side.BUY));
			message.setField(new OrdType(OrdType.FOREX_LIMIT));
			quickFixJTemplate.send(message);
		} catch (Exception e) {
			log.error("sendNewOrder exception: {}", e.getMessage(), e);
			response = MESSAGE_SEND_FAIL;
		}
		return response;
	}
	
	@Override
	public String sendNewOrderError() {
		String response = MESSAGE_SEND_SUCCESS;
		try {
			Message message = new Message();
			Header header = message.getHeader();
			header.setField(new BeginString("FIX.4.1"));
			header.setField(new SenderCompID("CLIENTFIX"));
			header.setField(new TargetCompID("EXEC"));
			header.setField(new MsgType(MsgType.ORDER_SINGLE)); //D
			message.setField(new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION));
			message.setField(new Symbol("LNUX"));
			message.setField(new Side(Side.BUY));
			message.setField(new OrdType(OrdType.FUNARI));
			quickFixJTemplate.send(message);
		} catch (Exception e) {
			log.error("sendNewOrderError exception: {}", e.getMessage(), e);
			response = MESSAGE_SEND_FAIL;
		}
		return response;
	}
	
	@Override
	public String sendNewOrderErrorType() {
		String response = MESSAGE_SEND_SUCCESS;
		try {
			Message message = new Message();
			Header header = message.getHeader();
			header.setField(new BeginString("FIX.4.1"));
			header.setField(new SenderCompID("CLIENTFIX"));
			header.setField(new TargetCompID("EXEC"));
			header.setField(new MsgType(MsgType.ORDER_SINGLE)); //D
			message.setField(new ClOrdID("123")); //required=Y
			message.setField(new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION));
			message.setField(new Symbol("LNUX"));
			message.setField(new Side(Side.BUY));
			message.setField(new OrdType(OrdType.FUNARI)); // "I" but valid type "1,2,F"
			quickFixJTemplate.send(message);
		} catch (Exception e) {
			log.error("sendNewOrderError exception: {}", e.getMessage(), e);
			response = MESSAGE_SEND_FAIL;
		}
		return response;
	}
}
