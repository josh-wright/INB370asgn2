package asgn2Exceptions;

@SuppressWarnings("serial")

/**
 * TrainException.java 
 * Extension of the java class Exception. Used when an exception is thrown
 * within DepartingTrain, RollingStock, FreightCar, PassengerCar, and Locomotive
 * Classes
 * @author Joshua Wright (n6366066)
 */
public class TrainException extends Exception {
	public TrainException(String expMsg) {
		super("Train Exception: " + expMsg);
	}
}
