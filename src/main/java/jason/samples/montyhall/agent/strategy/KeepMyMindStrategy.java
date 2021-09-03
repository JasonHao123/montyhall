package jason.samples.montyhall.agent.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jason.samples.montyhall.agent.constant.DoorStatus;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.GameData;
import jason.samples.montyhall.game.impl.MontyHallGameData;

/**
 * Strategy to simulate you will always keep your first choice.
 * 
 * @author jason
 *
 */
@Component("keep-my-mind")
public class KeepMyMindStrategy implements Strategy{
	private static final Logger logger = LoggerFactory.getLogger(KeepMyMindStrategy.class);

	private Random random = new Random(new Date().getTime());
	@Override
	public int perform(GameData data) {
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
		int selection = mData.getGuestSelection();
		if(options.size()>0 && mData.getGuestSelection() == -1) {
			selection = options.get(random.nextInt(options.size()));
			logger.info("I will choose {}",selection);
		}else {
			logger.info("I will keep my mind to choose {} ",selection); 
		}
		return selection;
	}

}
