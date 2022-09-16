package best.project.fix.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.field.ClOrdID;
import quickfix.field.MsgType;
import quickfix.field.TestReqID;
import quickfix.fix41.TestRequest;

@Service
@Slf4j
public class MessageService implements IMessageService {
	
	@Override
	public void processReceivedMessage(Message message, SessionID sessionId) {
		try {
			if (null != message && null != message.getHeader()
					&& MsgType.ORDER_SINGLE.equals(message.getHeader().getString(MsgType.FIELD))) {
				TestRequest testRequest = new TestRequest(new TestReqID(message.getString(ClOrdID.FIELD)));
				Session.sendToTarget(testRequest, sessionId);
			}
		} catch (Exception e) {
			log.error("processReceivedMessage exception: {}", e.getMessage(), e);
		}
	}
}
