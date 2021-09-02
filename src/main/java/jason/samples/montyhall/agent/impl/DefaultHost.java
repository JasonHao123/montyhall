package jason.samples.montyhall.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.agent.constant.DoorStatus;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.GameData;
import jason.samples.montyhall.game.impl.MontyHallGameData;

public class DefaultHost implements Host{
	private static final Logger logger = LoggerFactory.getLogger(DefaultHost.class);
	private Random random = new Random(new Date().getTime());
	@Override
	public void perform(GameData data) {
		if(!(data instanceof MontyHallGameData)) {
			throw new GameInvalidArgumentException("AlwaysChangeMindStrategy only accept MontyHallGameData");
		}
		MontyHallGameData mData = (MontyHallGameData) data;
		// check whether there is box opened
		boolean opened = false;
		for(int door:mData.getDoors()) {
			if((door & DoorStatus.NOT_OPENED) == 0 ) {
				opened = true;
				break;
			}
		}
		int[] doors = mData.getDoors();
		if(!opened) {
			// try to open a box, which is empty
			List<Integer> options = new ArrayList<>();
			for(int i=0;i<doors.length;i++) {
				if(i!=mData.getGuestSelection() && (doors[i] & DoorStatus.NOT_OPENED) == DoorStatus.NOT_OPENED && (doors[i] & DoorStatus.EMPTY) == DoorStatus.EMPTY ) {
					options.add(i);
				}
			}
			if(options.size()>0) {
				int option =  options.get(random.nextInt(options.size()));
				logger.info("open empty box  {}, do you want to change your mind?",option);
				doors[option] = doors[option] & DoorStatus.OPENED;
			}
		}else {
			// check final result
			if(mData.getGuestSelection()>0 && mData.getGuestSelection()<mData.getDoors().length) {
				if((doors[mData.getGuestSelection()] & DoorStatus.MONEY) > 0) {
					mData.setWin(true);
					logger.info("you win");
				}else {
					mData.setWin(false);
					logger.info("you loss");
				}
			}
			mData.setDone(true);
		}
	}

}
