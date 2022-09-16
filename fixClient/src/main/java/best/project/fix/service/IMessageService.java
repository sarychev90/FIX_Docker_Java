package best.project.fix.service;

public interface IMessageService {

	String sendTestMessage(String testReqID);

	String sendTestMessageError();

	String sendNewOrder(String clOrdID);

	String sendNewOrderError();

	String sendNewOrderErrorType();

}