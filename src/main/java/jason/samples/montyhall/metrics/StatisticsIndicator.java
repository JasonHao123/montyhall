package jason.samples.montyhall.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import jason.samples.montyhall.game.Game;

@Component
public class StatisticsIndicator implements HealthIndicator {

	@Autowired
	private Game game;

	@Override
	public Health health() {

		return Health.up()
				.withDetail("number-of-games", game.getStatistics().getNumberOfGame())
				.withDetail("win", game.getStatistics().getWin())
				.withDetail("win-ratio", game.getStatistics().getWinRatio()+"%")
				.build();

	}

}