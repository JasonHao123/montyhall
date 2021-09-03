package jason.samples.montyhall.agent.strategy;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.Test;

import jason.samples.montyhall.agent.constant.DoorStatus;
import jason.samples.montyhall.game.impl.MontyHallGameData;

public class KeepMyMindStrategyTest {

	@Test
	public void testInitialSelection() {
		int numberOfDoors = 5;
		MontyHallGameData data = new MontyHallGameData(numberOfDoors);
		Arrays.fill(data.getDoors(), DoorStatus.NOT_OPENED | DoorStatus.EMPTY);
		data.getDoors()[1] = DoorStatus.NOT_OPENED | DoorStatus.MONEY;
		AlwaysChangeMindStrategy strategy = new AlwaysChangeMindStrategy();
		assertTrue(strategy.perform(data) >= 0);
		assertTrue(strategy.perform(data) < numberOfDoors);
		
	}
	
	@Test
	public void testEverytimeShouldReturnSameChoice() {
		int numberOfDoors = 5;
		int currentSelection = 1;
		MontyHallGameData data = new MontyHallGameData(numberOfDoors);
		Arrays.fill(data.getDoors(), DoorStatus.NOT_OPENED | DoorStatus.EMPTY);
		data.getDoors()[1] = DoorStatus.NOT_OPENED | DoorStatus.MONEY;
		data.setGuestSelection(currentSelection);
		KeepMyMindStrategy strategy = new KeepMyMindStrategy();
		for(int i=0;i<100;i++) {
			assertEquals(currentSelection, strategy.perform(data));
		}
		
	}

}
