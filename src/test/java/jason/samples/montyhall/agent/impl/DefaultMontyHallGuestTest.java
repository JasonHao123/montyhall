package jason.samples.montyhall.agent.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import jason.samples.montyhall.agent.strategy.Strategy;
import jason.samples.montyhall.game.impl.MontyHallGameData;

public class DefaultMontyHallGuestTest {

	@Test
	public void testPerformShouldUpdateSelection() {
		Strategy strategy = Mockito.mock(Strategy.class);
		MontyHallGameData data = Mockito.mock(MontyHallGameData.class);
		when(strategy.perform(data)).thenReturn(1);
		DefaultMontyHalGuest guest = new DefaultMontyHalGuest(strategy);
		guest.perform(data);
		verify(data,Mockito.only()).setGuestSelection(1);
	}

}
