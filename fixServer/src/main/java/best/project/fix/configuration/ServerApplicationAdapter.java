package best.project.fix.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import best.project.fix.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import quickfix.Application;
import quickfix.Message;
import quickfix.SessionID;

@Slf4j
public class ServerApplicationAdapter implements Application {
	
	@Autowired
	IMessageService messageService;

	@Override
	public void fromAdmin(Message message, SessionID sessionId) {
		//log.info("fromAdmin: Message={}, SessionId={}", message, sessionId);
	}

	@Override
	public void fromApp(Message message, SessionID sessionId) {
		log.info("fromApp: Message={}, SessionId={}", message, sessionId);
		messageService.processReceivedMessage(message, sessionId);
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
