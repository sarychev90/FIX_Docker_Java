package best.project.fix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.allune.quickfixj.spring.boot.starter.EnableQuickFixJServer;
import quickfix.Acceptor;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.FileLogFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionSettings;
import quickfix.ThreadedSocketAcceptor;

@EnableQuickFixJServer
@Configuration
public class FixServerConfiguration {
	
	@Bean
	public Application serverApplication() {
		return new ServerApplicationAdapter();
	}

	@Bean
	public Acceptor serverAcceptor(quickfix.Application serverApplication, MessageStoreFactory serverMessageStoreFactory,
	                               SessionSettings serverSessionSettings, LogFactory serverLogFactory,
	                               MessageFactory serverMessageFactory) throws ConfigError {

		return new ThreadedSocketAcceptor(serverApplication, serverMessageStoreFactory, serverSessionSettings,
				serverLogFactory, serverMessageFactory);
	}

	@Bean
	public LogFactory serverLogFactory(SessionSettings serverSessionSettings) {
		return new FileLogFactory(serverSessionSettings);
	}

}
