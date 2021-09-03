package jason.samples.montyhall.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import jason.samples.montyhall.MontyHallTestApplication;
import jason.samples.montyhall.game.Game;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK,classes = MontyHallTestApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private Game game1;
	
	@Autowired
	private Game game2;
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Live Game possibilities")));
	}
	
	
	@Test
	public void testDataEndpointShouldReturnCurrentStatistics() throws Exception {
		game1.getStatistics().setNumberOfGame(10);
		game1.getStatistics().setWin(3);
		game1.getStatistics().setWinRatio(30);
		game2.getStatistics().setNumberOfGame(20);
		game2.getStatistics().setWin(10);
		game2.getStatistics().setWinRatio(50);		
		
		this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", containsString("application/json")))
				.andExpect(content().string("[10,30,20,50]"));
	}

}
