package jason.samples.montyhall.game;

/**
 * Common game data structure, to keep real time game data, participants can react based on the data.
 * 
 * @author jason
 *
 */
public class GameData {
	private boolean win;
	private boolean done;
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	
}
