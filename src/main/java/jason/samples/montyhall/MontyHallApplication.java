package jason.samples.montyhall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.game.Game;

@SpringBootApplication
@EnableScheduling
public class MontyHallApplication {

	@Autowired
	private Game game;
	@Autowired
	private Guest guest;
	@Autowired
	private Host host;

	public static void main(String[] args) {
		SpringApplication.run(MontyHallApplication.class, args);
	}

	@Scheduled(fixedRate = 500)
	public void runGame() {
		game.reset();
		game.join(guest);
		game.join(host);
		game.play();
	}

}