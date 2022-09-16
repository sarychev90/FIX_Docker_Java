package best.project.fix.service;

import quickfix.Message;
import quickfix.SessionID;

public interface IMessageService {

	void processReceivedMessage(Message message, SessionID sessionId);

}