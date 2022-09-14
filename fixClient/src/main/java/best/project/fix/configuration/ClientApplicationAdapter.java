package best.project.fix.configuration;

import lombok.extern.slf4j.Slf4j;
import quickfix.Application;
import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix41.MessageCracker;

@Slf4j
public class ClientApplicationAdapter implements Application {

	private final MessageCracker messageCracker;

	public ClientApplicationAdapter(MessageCracker messageCracker) {
		this.messageCracker = messageCracker;
	}

	@Override
	public void fromAdmin(Message message, SessionID sessionId) {
		log.info("fromAdmin: Message={}, SessionId={}", message, sessionId);
	}

	@Override
	public void fromApp(Message message, SessionID sessionId) {
		log.info("fromApp: Message={}, SessionId={}", message, sessionId);
		try {
			messageCracker.crack(message, sessionId);
		} catch (UnsupportedMessageType | FieldNotFound | IncorrectTagValue e) {
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
		log.info("toApp: Message={}, SessionId={}", message, sessionId);
	}
}
