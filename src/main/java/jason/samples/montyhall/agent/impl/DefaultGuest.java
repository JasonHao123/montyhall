package jason.samples.montyhall.agent.impl;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.strategy.Strategy;

public class DefaultGuest implements Guest{

	private Strategy strategy;

	public DefaultGuest(Strategy strategy) {
		this.strategy = strategy;
	}

}
