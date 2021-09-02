package jason.samples.montyhall.game.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.game.Game;

public class MontyHallGame implements Game{
	private static final Logger logger = LoggerFactory.getLogger(MontyHallGame.class);

	public MontyHallGame(int numberOfDoors) {
		
	}

	@Override
	public void reset() {
		logger.info("reset");
	}

	@Override
	public void join(Guest guest) {
		logger.info("guest join");
	}

	@Override
	public void join(Host host) {
		logger.info("host join");
	}

	@Override
	public void play() {
		logger.info("play");
	}

}
