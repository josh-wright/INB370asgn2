package asgn2Exceptions;

@SuppressWarnings("serial")

/**
/**
 * <p>A simple class for exceptions thrown by
 * railway shunting and boarding operations.
 * </p><p>
 * <strong>Hint:</strong> When developing your
 * user interface you may want to consider getting
 * the <code>TrainException</code> constructor to display the
 * problem to the user, rather than catching the
 * exception and then displaying it.</p>
 * 
 * @author Joshua Wright (n6366066)
 * @version 1.0
 */
public class TrainException extends Exception {
	
	/**
	 * Constructs a new TrainException object.
	 * 
	 * @param message an informative message describing the
	 * cause of the problem
	 * @author Joshua Wright (n6366066)
	 */
	public TrainException(String expMsg) {
		super("Train Exception: " + expMsg);
	}
}
