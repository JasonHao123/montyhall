package jason.samples.montyhall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jason.samples.montyhall.game.Game;

/**
 * This web controller is used to visualize the game result
 * 
 * @author jason
 *
 */
@Controller
public class StatisticsController {
	
	@Autowired
	private Game game1;
	
	@Autowired
	private Game game2;

	@GetMapping("/")
	public String greeting(Model model) {
		return "home";
	}
	
	@RequestMapping("/data")
	public @ResponseBody int[] data() {
		int[] result = new int[4];
		result[0] = game1.getStatistics().getNumberOfGame();
		result[1] = game1.getStatistics().getWinRatio();
		result[2] = game2.getStatistics().getNumberOfGame();
		result[3] = game2.getStatistics().getWinRatio();
		return result;
	}
}
