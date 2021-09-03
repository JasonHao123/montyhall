package jason.samples.montyhall.game.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import jason.samples.montyhall.agent.constant.DoorStatus;
import jason.samples.montyhall.agent.impl.DefaultMontyHallHost;
import jason.samples.montyhall.exception.GameInvalidArgumentException;

public class MontyHallGameTest {

	@Test
	public void createGameWithNegativeNumberOfDoorsShouldThrowException() {
		assertThrows(GameInvalidArgumentException.class, () -> {
			new MontyHallGame("test",-1);
		});
	}

	@Test
	public void testNumberOfDoorCreateCorrectly() {
		int numberOfDoors = 5;
		MontyHallGame game = new MontyHallGame("test", numberOfDoors);
		assertEquals(numberOfDoors, game.getData().doors.length);
	}

	@Test
	public void testStatusAfterReset() {
		MontyHallGame game = new MontyHallGame("test", 3);
		game.getData().setDone(true);
		game.getData().setWin(true);
		game.getData().doors[1] = 99;
		game.reset();
		assertEquals(false, game.getData().isDone());
		assertEquals(false, game.getData().isWin());
		assertNotEquals(99, game.getData().doors[1]);
		int countOfMoney = 0;
		int countOfOpened = 0;
		for (int value : game.getData().getDoors()) {
			if ((value & DoorStatus.MONEY) == DoorStatus.MONEY) {
				countOfMoney++;
			}
			if ((value & DoorStatus.NOT_OPENED) == 0) {
				countOfOpened++;
			}
		}
		assertEquals(1, countOfMoney);
		assertEquals(0, countOfOpened);
	}

}
