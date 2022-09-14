package best.project.fix.configuration;

import lombok.extern.slf4j.Slf4j;
import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix41.MessageCracker;
import quickfix.fix41.OrderCancelRequest;

@Slf4j
public class ApplicationMessageCracker extends MessageCracker {
	
	@Override
	public void onMessage(OrderCancelRequest orderCancelRequest, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		log.info("Message received for sessionID={}: {}", sessionID, orderCancelRequest);
	}

}
