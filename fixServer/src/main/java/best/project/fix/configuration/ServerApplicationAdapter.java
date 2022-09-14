package best.project.fix.configuration;

import lombok.extern.slf4j.Slf4j;
import quickfix.Application;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.field.ClOrdID;
import quickfix.field.MsgType;
import quickfix.field.TestReqID;
import quickfix.fix41.TestRequest;

@Slf4j
public class ServerApplicationAdapter implements Application {

	@Override
	public void fromAdmin(Message message, SessionID sessionId) {
		//log.info("fromAdmin: Message={}, SessionId={}", message, sessionId);
	}

	@Override
	public void fromApp(Message message, SessionID sessionId) {
		log.info("fromApp: Message={}, SessionId={}", message, sessionId);
		try {
			if (null != message && null != message.getHeader()
					&& MsgType.ORDER_SINGLE.equals(message.getHeader().getString(MsgType.FIELD))) {
				TestRequest testRequest = new TestRequest(new TestReqID(message.getString(ClOrdID.FIELD)));
				Session.sendToTarget(testRequest, sessionId);
			}
		} catch (Exception e) {
			log.error("fromApp exception: {}", e.getMessage(), e);
		}
	}

	@Override
	public void onCreate(SessionID sessionId) {
		//log.info("onCreate: SessionId={}", sessionId);
	}

	@Override
	public void onLogon(SessionID sessionId) {
		//log.info("onLogon: SessionId={}", sessionId);
	}

	@Override
	public void onLogout(SessionID sessionId) {
		//log.info("onLogout: SessionId={}", sessionId);
	}

	@Override
	public void toAdmin(Message message, SessionID sessionId) {
		//log.info("toAdmin: Message={}, SessionId={}", message, sessionId);
	}

	@Override
	public void toApp(Message message, SessionID sessionId) {
		//log.info("toApp: Message={}, SessionId={}", message, sessionId);
	}
}
