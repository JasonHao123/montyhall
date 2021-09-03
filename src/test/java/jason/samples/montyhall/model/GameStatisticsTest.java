package jason.samples.montyhall.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStatisticsTest {

	@Test
	public void testIncreaseWin() {
		GameStatistics statistics = new GameStatistics();
		statistics.setNumberOfGame(10);
		statistics.setWin(5);
		statistics.increaseWin();
		assertEquals(60, statistics.getWinRatio());
	}
	
	@Test
	public void testIncreaseNumberOfRound() {
		GameStatistics statistics = new GameStatistics();
		statistics.setNumberOfGame(9);
		statistics.setWin(5);
		statistics.increaseNumberOfGame();
		assertEquals(50, statistics.getWinRatio());
	}
	

}
