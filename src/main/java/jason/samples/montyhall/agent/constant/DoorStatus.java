package jason.samples.montyhall.agent.constant;

/**
 * 
 * There will be 4 door status, which are empty/money multiply opened/not opened. Use digit operation to get distinct status.
 * 
 * for example when the status value is 10, which is EMPTY_NOT_OPENED, if you want to test whether it's opened, use 10(binary 1010) & 8(1000) = 8 (NOT_OPENED) 
 * 
 * @author jason
 *
 */
public interface DoorStatus {

	int EMPTY = 2;
	
	int MONEY = 4;

	int NOT_OPENED = 8;
	
}
