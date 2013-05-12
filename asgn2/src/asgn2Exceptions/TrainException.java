package asgn2Exceptions;

@SuppressWarnings("serial")

/**
 * This class controls exceptions thrown by the application.
 *
 * @author Joshua
 *
 */
public class TrainException extends Exception {
	public TrainException(String expMsg) {
		super("Train Exception: " + expMsg);
	}
}
