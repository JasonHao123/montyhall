package jason.samples.montyhall.agent.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.strategy.Strategy;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.GameData;
import jason.samples.montyhall.game.impl.MontyHallGameData;

public class DefaultGuest implements Guest{
	private static final Logger logger = LoggerFactory.getLogger(DefaultGuest.class);

	private Strategy strategy;

	public DefaultGuest(Strategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void perform(GameData data) {
		logger.info("perform");
		if(!(data instanceof MontyHallGameData)) {
			throw new GameInvalidArgumentException("AlwaysChangeMindStrategy only accept MontyHallGameData");
		}
		MontyHallGameData mData = (MontyHallGameData) data;
		mData.setGuestSelection(strategy.perform(data));
	}

}
