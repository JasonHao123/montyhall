package jason.samples.montyhall.agent.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import jason.samples.montyhall.agent.constant.DoorStatus;
import jason.samples.montyhall.exception.GameInvalidArgumentException;
import jason.samples.montyhall.game.impl.MontyHallGameData;

public class DefaultMontyHallHostTest {
	
	@Test
	public void testMinusDoorsToOpenShouldThrowException() {
		assertThrows(GameInvalidArgumentException.class, () -> {
	        new DefaultMontyHallHost(-1);
	    });
	}

	@Test
	public void testShouldNotOpenUserSelectionAndMoney() {
		MontyHallGameData data = new MontyHallGameData(5);
		Arrays.fill(data.getDoors(), DoorStatus.NOT_OPENED | DoorStatus.EMPTY);
		data.setGuestSelection(0);
		data.getDoors()[1] = DoorStatus.NOT_OPENED | DoorStatus.MONEY;
		int numberToOpen=10;
		DefaultMontyHallHost host = new DefaultMontyHallHost(numberToOpen);
		for(int i=0;i<3;i++) {
			host.perform(data);
		}
		assertEquals(false, data.isDone());
		assertEquals(false, data.isWin());
		assertTrue((data.getDoors()[0] & DoorStatus.NOT_OPENED) == DoorStatus.NOT_OPENED);
		assertTrue((data.getDoors()[1] & DoorStatus.NOT_OPENED) == DoorStatus.NOT_OPENED);
		assertTrue((data.getDoors()[2] & DoorStatus.NOT_OPENED) == 0);
		assertTrue((data.getDoors()[3] & DoorStatus.NOT_OPENED) == 0);
		assertTrue((data.getDoors()[4] & DoorStatus.NOT_OPENED) == 0);
	}
	
	@Test
	public void testGameResultJudgeWin() {
		MontyHallGameData data = new MontyHallGameData(5);
		Arrays.fill(data.getDoors(), DoorStatus.NOT_OPENED | DoorStatus.EMPTY);
		data.setGuestSelection(0);
		data.getDoors()[0] = DoorStatus.NOT_OPENED | DoorStatus.MONEY;
		int numberToOpen=10;
		DefaultMontyHallHost host = new DefaultMontyHallHost(numberToOpen);
		for(int i=0;i<5;i++) {
			host.perform(data);
		}
		assertEquals(true, data.isDone());
		assertEquals(true, data.isWin());
	}
	
	@Test
	public void testGameResultJudgeLoss() {
		MontyHallGameData data = new MontyHallGameData(5);
		Arrays.fill(data.getDoors(), DoorStatus.NOT_OPENED | DoorStatus.EMPTY);
		data.setGuestSelection(0);
		data.getDoors()[1] = DoorStatus.NOT_OPENED | DoorStatus.MONEY;
		int numberToOpen=10;
		DefaultMontyHallHost host = new DefaultMontyHallHost(numberToOpen);
		for(int i=0;i<4;i++) {
			host.perform(data);
		}
		assertEquals(true, data.isDone());
		assertEquals(false, data.isWin());
	}

}
