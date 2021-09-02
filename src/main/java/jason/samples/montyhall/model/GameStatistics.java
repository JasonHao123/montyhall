package jason.samples.montyhall.model;

public class GameStatistics {
	private int numberOfGame;
	private int win;
	private int winRatio;
	public int getNumberOfGame() {
		return numberOfGame;
	}
	public void setNumberOfGame(int numberOfGame) {
		this.numberOfGame = numberOfGame;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getWinRatio() {
		return winRatio;
	}
	public void setWinRatio(int winRatio) {
		this.winRatio = winRatio;
	}
	public void increaseNumberOfGame() {
		numberOfGame++;
		if(numberOfGame>0) {
			winRatio = win*100/numberOfGame;
		}
	}
	public void increaseWin() {
		win++;
		if(numberOfGame>0) {
			winRatio = win*100/numberOfGame;
		}
	}
	
	
}
