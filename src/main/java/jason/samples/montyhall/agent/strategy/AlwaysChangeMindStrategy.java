package jason.samples.montyhall.agent.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jason.samples.montyhall.agent.constant.DoorStatus;
import jason.samples.montyhall.agent.impl.DefaultGuest;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.GameData;
import jason.samples.montyhall.game.impl.MontyHallGameData;

@Component("always-change-mind")
public class AlwaysChangeMindStrategy implements Strategy{
	private static final Logger logger = LoggerFactory.getLogger(AlwaysChangeMindStrategy.class);

	private Random random = new Random(new Date().getTime());
	@Override
	public int perform(GameData data) {
		logger.debug("perform");
		if(!(data instanceof MontyHallGameData)) {
			throw new GameInvalidArgumentException("AlwaysChangeMindStrategy only accept MontyHallGameData");
		}
		MontyHallGameData mData = (MontyHallGameData) data;
		List<Integer> options = new ArrayList<>();
		int[] doors = mData.getDoors();
		for(int i=0;i<doors.length;i++) {
			if(i!=mData.getGuestSelection() && (doors[i] & DoorStatus.NOT_OPENED) == DoorStatus.NOT_OPENED ) {
				options.add(i);
			}
		}
		int selection =  mData.getGuestSelection();
		if(options.size()>0) {
			selection = options.get(random.nextInt(options.size()));
			logger.info("I changed my mind, I want to choose {}",selection);
		}else {
			logger.info("I have no other choice, I can only choose {}",selection);
		}
		return selection;
	}

}
