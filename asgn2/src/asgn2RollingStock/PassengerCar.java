package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A passenger car is designed to carry people and has a fixed
 * seating capacity.  We assume that the train is a long-distance
 * one in which all passengers are assigned a seat (unlike your 
 * peak-hour, metropolitan commuting experience!).
 * 
 * @author Robert Dempsey (Student Number: N5400872)
 * @version 1.0
 *
 */
public class PassengerCar extends RollingStock {
	
	private final static Integer BOARD_MIN = 0;
	private final static Integer ALIGHT_MIN = 0;
	private static final Integer SEATS_MIN = 0; 
	
	private Integer numberOfSeats = 0;
	private Integer numberOnBoard = 0;

	/**
	 * Constructs a passenger car with a known weight and a
	 * fixed number of seats.  (We allow a passenger car to have
	 * zero seats, although it would not be very useful.)
	 * 
	 * @param grossWeight the carriage's gross weight in tonnes (ignoring the
	 * weight of passengers, which we treat as negligible)
	 * @param numberOfSeats how many seats are available in the carriage
	 * @throws TrainException if the gross weight is not positive or if the
	 * number of seats is negative
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) 
			throws TrainException {
		super(grossWeight);
		if (numberOfSeats < SEATS_MIN) {
			throw new TrainException("Seats may not be less than " +  
					SEATS_MIN + ".");
		} 
		this.numberOfSeats = numberOfSeats;
	}
	
	/**
	 * Removes the given number of passengers from this carriage.  Attempting
	 * to remove more passengers than are on board is not allowed.
	 * 
	 * @param departingPassengers the number of passengers alighting
	 * from the carriage
	 * @throws TrainException if the number of departing passengers is
	 * negative or if the number of departing passengers exceeds the
	 * number on board
	 */
	public void alight(Integer departingPassengers) throws TrainException {
		if (departingPassengers > numberOnBoard()) {
			throw new TrainException("Number of passengers alighting cannot " +
					"exceed number on board");
		} else if (departingPassengers < ALIGHT_MIN) {
			throw new TrainException("Number of passengers alighting cannot " +
					"be less than " + ALIGHT_MIN + ".");
		}
		numberOnBoard -= departingPassengers;
	}
	
	/**
	 * Adds the given number of new passengers to the number on board the
	 * carriage.  If there are too many new passengers for the number of
	 * spare seats the left over people are not boarded.
	 * 
	 * @param newPassengers the number of people who wish to board
	 * the carriage
	 * @return the number of people who were unable to board the
	 * carriage because they couldn't get a seat
	 * @throws TrainException if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException {
		final int ZERO_EXCESS = 0;
		
		if (newPassengers < BOARD_MIN) {
			throw new TrainException("Number of passengers boarding must be " +
					"greater than " + BOARD_MIN + ".");
		}
		
		if (numberOnBoard() + newPassengers > numberOfSeats) {
			Integer excessPassengers = numberOnBoard() + newPassengers - 
					numberOfSeats();
			numberOnBoard = numberOfSeats;
			return excessPassengers;
		} else {
			numberOnBoard += newPassengers;
			return ZERO_EXCESS;
		}	
	}
	
	/**
	 * Returns the number of seats installed on this carriage.
	 * 
	 * @return the number of seats on this carriage
	 */
	public Integer numberOfSeats() {
		return numberOfSeats;
	}
	
	/**
	 * Returns the number of passengers currently on board
	 * this carriage.
	 * 
	 * @return the number of passengers on board
	 */
	public Integer numberOnBoard() {
		return numberOnBoard;
	}

	/**
	 * Returns a human-readable description of the passenger car.  This has the form
	 * "<code>Passenger(</code><em>x</em><code>/</code><em>y</em><code>)</code>" where
	 * <em>x</em> is the number of passengers currently on
	 * board and <em>y</em> is the number of seats in the carriage.
	 * 
	 * 
	 * @return a human-readable description of the passenger car
	 */
	@Override
	public String toString() {
		return "Passenger(" + this.numberOnBoard() + "/" + this.numberOfSeats()
				+ ")";
	}
	
}
