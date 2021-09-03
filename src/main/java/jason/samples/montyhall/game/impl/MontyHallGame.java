package jason.samples.montyhall.game.impl;

import static jason.samples.montyhall.agent.constant.DoorStatus.EMPTY;
import static jason.samples.montyhall.agent.constant.DoorStatus.MONEY;
import static jason.samples.montyhall.agent.constant.DoorStatus.NOT_OPENED;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.Game;
import jason.samples.montyhall.model.GameStatistics;

/**
 * Concrete Monty Hall game class to simulate the whole game process
 * 
 * @author jason
 *
 */
public class MontyHallGame implements Game{
	private static final Logger logger = LoggerFactory.getLogger(MontyHallGame.class);
	private int numberOfDoors;
	private MontyHallGameData data;

	private Random random = new Random(new Date().getTime());
	private Guest guest;
	private Host host;
	private GameStatistics statistics;
	private String name;
	
	public MontyHallGame(String name,int numberOfDoors) {
		if(numberOfDoors <= 0) {
			throw new GameInvalidArgumentException("number of doors must be positive number");
		}
		this.numberOfDoors = numberOfDoors;
		this.name = name;
		data = new MontyHallGameData(numberOfDoors);
		statistics = new GameStatistics();
	}

	@Override
	public void reset() {
		logger.info("----------------reset-----------------");
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
	
	public MontyHallGameData getData() {
		return data;
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
		logger.info(name+": "+statistics.toString());
	}

	@Override
	public GameStatistics getStatistics() {
		return statistics;
	}


}
