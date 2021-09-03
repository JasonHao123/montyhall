package jason.samples.montyhall.exception;

/**
 * Exception class for invalid argument
 * 
 * @author jason
 *
 */
public class GameInvalidArgumentException extends GameBaseRuntimeException {

	public GameInvalidArgumentException(String message) {
		super(message);
	}

}
