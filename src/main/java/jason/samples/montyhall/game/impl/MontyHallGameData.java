package jason.samples.montyhall.game.impl;

import jason.samples.montyhall.game.GameData;

public class MontyHallGameData extends GameData{

	int[] doors;
	int guestSelection;
	
	public MontyHallGameData(int numberOfDoors) {
		doors = new int[numberOfDoors];
	}

	public int[] getDoors() {
		return doors;
	}

	public void setDoors(int[] doors) {
		this.doors = doors;
	}

	public int getGuestSelection() {
		return guestSelection;
	}

	public void setGuestSelection(int guestSelection) {
		this.guestSelection = guestSelection;
	}
	
	
}
