package jason.samples.montyhall.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.agent.impl.DefaultGuest;
import jason.samples.montyhall.agent.impl.DefaultHost;
import jason.samples.montyhall.agent.strategy.Strategy;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.Game;
import jason.samples.montyhall.game.impl.MontyHallGame;

@Configuration
public class GameConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(GameConfig.class);

	@Value("${game.montyhall.number-of-doors}")
	private int numberOfDoors;
	
	@Value("${game.montyhall.guest.strategy}")
	private String guestStrategy;

	@Bean
	public Guest guest(@Autowired List<Strategy> strategies,@Autowired ApplicationContext context) {
		logger.info("create guest with {} strategy",guestStrategy);
		if(guestStrategy == null) {
			throw new GameInvalidArgumentException("invalid guest strategy");
		}
		Object bean = context.getBean(guestStrategy);
		if(bean == null ) {
			throw new GameInvalidArgumentException(String.format("strategy %s not found",guestStrategy));
		}
		if(!(bean instanceof Strategy)) {
			throw new GameInvalidArgumentException(String.format("strategy %s must implement Strategy interface",guestStrategy));
		}
		return new DefaultGuest((Strategy)bean);
	}
	
	@Bean
	public Host host() {
		logger.info("create host");
	
		return new DefaultHost();
	}
	
	@Bean
	public Game game() {
		logger.info("create game");
		return new MontyHallGame(numberOfDoors);
	}
	
	
}
