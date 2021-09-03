package jason.samples.montyhall.game;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.model.GameStatistics;

/**
 * Common game interface
 * 
 * @author jason
 *
 */
public interface Game {

	/**
	 * after each round, reset the status
	 */
	void reset();

	/** Let guest to join the game
	 * @param guest
	 */
	void join(Guest guest);

	/** Let host to join the game
	 * @param host
	 */
	void join(Host host);

	/**
	 * Start to play the game
	 */
	void play();
	
	/**
	 * @return statistics for this game
	 */
	GameStatistics getStatistics();

}
