package jason.samples.montyhall.game.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.game.Game;
import jason.samples.montyhall.model.GameStatistics;

import static jason.samples.montyhall.agent.constant.DoorStatus.*;

public class MontyHallGame implements Game{
	private static final Logger logger = LoggerFactory.getLogger(MontyHallGame.class);
	private int numberOfDoors;
	private MontyHallGameData data;
	private Random random = new Random(new Date().getTime());
	private Guest guest;
	private Host host;
	private GameStatistics statistics;
	
	public MontyHallGame(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
		data = new MontyHallGameData(numberOfDoors);
		statistics = new GameStatistics();
	}

	@Override
	public void reset() {
		logger.info("reset");
		Arrays.fill(data.doors, NOT_OPENED | EMPTY);
		int pos = random.nextInt(numberOfDoors);
		logger.info("put money in {}",pos);
		data.doors[pos] = NOT_OPENED | MONEY;
		data.guestSelection = -1;
		data.setWin(false);
		data.setDone(false);
	}

	@Override
	public void join(Guest guest) {
		logger.info("guest join");
		this.guest = guest;
	}

	@Override
	public void join(Host host) {
		logger.info("host join");
		this.host = host;
	}

	@Override
	public void play() {
		logger.info("play");
		statistics.increaseNumberOfGame();
		while(!data.isDone()) {
			guest.perform(data);
			host.perform(data);
		}
		if(data.isWin()) {
			statistics.increaseWin();
		}
	}

	@Override
	public GameStatistics getStatistics() {
		return statistics;
	}


}
