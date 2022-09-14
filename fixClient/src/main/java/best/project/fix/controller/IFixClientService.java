package best.project.fix.controller;

public interface IFixClientService {

	String simpleTestMessage(String testReqID) throws Exception;

	String simpleTestMessageError() throws Exception;

	String simpleOrder(String clOrdID) throws Exception;

	String simpleOrderError() throws Exception;

}