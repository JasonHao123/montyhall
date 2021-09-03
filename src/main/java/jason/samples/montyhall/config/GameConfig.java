package jason.samples.montyhall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.agent.impl.DefaultMontyHalGuest;
import jason.samples.montyhall.agent.impl.DefaultMontyHallHost;
import jason.samples.montyhall.agent.strategy.AlwaysChangeMindStrategy;
import jason.samples.montyhall.agent.strategy.KeepMyMindStrategy;
import jason.samples.montyhall.game.Game;
import jason.samples.montyhall.game.impl.MontyHallGame;

/**
 * Initialize components for the game, which we create two games, let two different types of guest to join them, and simulate the game.
 * 
 * @author jason
 *
 */
@Configuration
public class GameConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(GameConfig.class);

	@Value("${game.montyhall.number-of-doors}")
	private int numberOfDoors;
	
	@Value("${game.montyhall.host.number-of-box-to-open}")
	private int numberOfBoxToOpen;

	@Bean("guest1")
	public Guest guest1(@Autowired KeepMyMindStrategy strategy) {
		return new DefaultMontyHalGuest(strategy);
	}
	
	@Bean("guest2")
	public Guest guest1(@Autowired AlwaysChangeMindStrategy strategy) {
		return new DefaultMontyHalGuest(strategy);
	}
	
	@Bean
	public Host host() {
		logger.info("create host");
	
		return new DefaultMontyHallHost(numberOfBoxToOpen);
	}
	
	@Bean("game1")
	public Game game1() {
		logger.info("create game1");
		return new MontyHallGame("game1",numberOfDoors);
	}
	
	@Bean("game2")
	public Game game2() {
		logger.info("create game2");
		return new MontyHallGame("game2",numberOfDoors);
	}
}
