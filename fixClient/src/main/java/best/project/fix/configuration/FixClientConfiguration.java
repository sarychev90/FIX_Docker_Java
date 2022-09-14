package best.project.fix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.allune.quickfixj.spring.boot.starter.EnableQuickFixJClient;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.FileLogFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionSettings;
import quickfix.ThreadedSocketInitiator;
import quickfix.fix41.MessageCracker;

@EnableQuickFixJClient
@Configuration
public class FixClientConfiguration {
	
	@Bean
	public Application clientApplication(MessageCracker messageCracker) {
		return new ClientApplicationAdapter(messageCracker);
	}

	@Bean
	public MessageCracker messageCracker() {
		return new ApplicationMessageCracker();
	}

	@Bean
	public Initiator clientInitiator(Application clientApplication, MessageStoreFactory clientMessageStoreFactory,
	                                 SessionSettings clientSessionSettings, LogFactory clientLogFactory,
	                                 MessageFactory clientMessageFactory) throws ConfigError {

		return new ThreadedSocketInitiator(clientApplication, clientMessageStoreFactory, clientSessionSettings,
				clientLogFactory, clientMessageFactory);
	}

	@Bean
	public LogFactory clientLogFactory(SessionSettings clientSessionSettings) {
		return new FileLogFactory(clientSessionSettings);
	}

}
