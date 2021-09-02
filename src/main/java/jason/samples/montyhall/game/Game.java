package jason.samples.montyhall.game;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;

public interface Game {

	void reset();

	void join(Guest guest);

	void join(Host host);

	void play();

}
