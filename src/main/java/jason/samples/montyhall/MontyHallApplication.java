package jason.samples.montyhall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import jason.samples.montyhall.agent.Guest;
import jason.samples.montyhall.agent.Host;
import jason.samples.montyhall.game.Game;

/**
 * 
 * MontyHall game application, it schedule the game for 500ms each round
 * 
 * @author jason
 *
 */
@SpringBootApplication
@EnableScheduling
public class MontyHallApplication {

	@Autowired
	private Game game1;
	
	@Autowired
	private Game game2;
	
	@Autowired
	private Guest guest1;
	
	@Autowired
	private Guest guest2;
	
	@Autowired
	private Host host;

	public static void main(String[] args) {
		SpringApplication.run(MontyHallApplication.class, args);
	}

	/**
	 * schedule the game for each round
	 */
	@Scheduled(initialDelay = 5000,fixedRate = 500)
	public void runGame() {
		game1.reset();
		game1.join(guest1);
		game1.join(host);
		game1.play();
		
		game2.reset();
		game2.join(guest2);
		game2.join(host);
		game2.play();
	}

}