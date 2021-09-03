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

/**
 * Default Host implementation for Monty Hall game, put the open box action and judge win/loss in this class.
 * 
 * @author jason
 *
 */
public class DefaultMontyHallHost implements Host{
	private static final Logger logger = LoggerFactory.getLogger(DefaultMontyHallHost.class);
	private Random random = new Random(new Date().getTime());
	private int numberOfBoxToOpen;
	private static final int DEFAULT_BOX_TO_OPEN = 1;
	public DefaultMontyHallHost() {
		this(DEFAULT_BOX_TO_OPEN);
	}
	public DefaultMontyHallHost(int boxToOpen) {
		if(boxToOpen <= 0) {
			throw new GameInvalidArgumentException("number of box to open must be positive number");
		}
		this.numberOfBoxToOpen = boxToOpen;
	}
	@Override
	public void perform(GameData data) {
		if(!(data instanceof MontyHallGameData)) {
			throw new GameInvalidArgumentException("AlwaysChangeMindStrategy only accept MontyHallGameData");
		}
		MontyHallGameData mData = (MontyHallGameData) data;
		// check whether there is box opened
		int opened = 0;
		int moneyPosition = -1;
		int[] doors = mData.getDoors();
		for(int i=0;i<doors.length;i++) {
			if((doors[i] & DoorStatus.NOT_OPENED) == 0 ) {
				opened++;
			}
			if((doors[i] & DoorStatus.MONEY) == DoorStatus.MONEY) {
				moneyPosition = i;
			}
		}
		int numberNeedToKeep = 1;
		if(mData.getGuestSelection()!=moneyPosition) {
			numberNeedToKeep = 2;
		}
		if(opened < numberOfBoxToOpen && opened <doors.length-numberNeedToKeep) {
			logger.info("{} {} {} {}",doors.length,numberOfBoxToOpen,opened,numberNeedToKeep);
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
				doors[option] = doors[option] ^ DoorStatus.NOT_OPENED;
			}else {
				logger.info("no empty box to open, skip the step");
			}
		}else {
			// check final result
			if(mData.getGuestSelection()>=0 && mData.getGuestSelection()<mData.getDoors().length) {
				if((doors[mData.getGuestSelection()] & DoorStatus.MONEY) > 0) {
					mData.setWin(true);
					logger.info("you win");
				}else {
					mData.setWin(false);
					logger.info("you loss");
				}
			}else {
				logger.warn("invalid selection {}",mData.getGuestSelection());
			}
			mData.setDone(true);
		}
	}

}
