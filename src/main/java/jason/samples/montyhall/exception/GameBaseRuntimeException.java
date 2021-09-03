package jason.samples.montyhall.exception;

/**
 * Base Runtime exception for the game
 * 
 * @author jason
 *
 */
public class GameBaseRuntimeException extends RuntimeException{

	public GameBaseRuntimeException(String message) {
		super(message);
	}

}
