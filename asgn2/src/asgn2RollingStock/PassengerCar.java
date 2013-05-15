/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert Dempsey (Student Number: N5400872)
 *
 */
public class PassengerCar extends RollingStock {
	
	private final static Integer MinimumBoard = 0;
	private final static Integer MinimumDeparture = 0;
	private static final Integer MinimumSeats = 0;
	
	private Integer numberOfSeats = 0;
	private Integer numberOnBoard = 0;

	/**
	 * Construct passenger car
	 * @param Integer grossWeight - the gross weight of the passenger car
	 * @param numberOfSeats - the number of passenger seats in the car
	 * @throws TrainException if grossWeight zero or less or number of seats is negative
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException {
		super(grossWeight);
		if (numberOfSeats < MinimumSeats) {
			throw new TrainException("Seats may not be less than " +  MinimumSeats + ".");
		} 
		this.numberOfSeats = numberOfSeats;
	}
	
	/**
	 * Allows passengers to leave the train
	 * @param Integer departingPassengers - the number of passengers leaving the train
	 * @throws TrainException if more passengers than are on board try to leave, 
	 * or if negative passengers try to leave
	 */
	public void alight(Integer departingPassengers) throws TrainException {
		if (departingPassengers > numberOnBoard()) {
			throw new TrainException("Number of passengers alighting cannot exceed number on board");
		} else if (departingPassengers < MinimumDeparture) {
			throw new TrainException("Number of passengers alighting cannot be less than " +
									  MinimumDeparture + ".");
		}
		numberOnBoard -= departingPassengers;
	}
	
	/**
	 * Allow passengers to board the train
	 * @param Integer newPassengers - number of passengers wanting to board the train
	 * @return Integer - number of passengers that were unable to board due lack of seats
	 * @throws TrainException if negative passengers try to board the train
	 */
	public Integer board(Integer newPassengers) throws TrainException {
		final int zeroExcess = 0;
		
		if (newPassengers < MinimumBoard) {
			throw new TrainException("Number of passengers boarding must be greater than " + 
									  MinimumBoard + ".");
		}
		
		if (numberOnBoard() + newPassengers > numberOfSeats) {
			Integer excessPassengers = numberOnBoard() + newPassengers - numberOfSeats();
			numberOnBoard = numberOfSeats;
			return excessPassengers;
		} else {
			numberOnBoard += newPassengers;
			return zeroExcess;
		}	
	}
	
	/**
	 * Get number of passenger seats on carriage
	 * @return Integer - number of seats
	 */
	public Integer numberOfSeats() {
		return numberOfSeats;
	}
	
	/**
	 * Get number of passengers currently on board
	 * @return Integer - number of passengers on board
	 */
	public Integer numberOnBoard() {
		return numberOnBoard;
	}

	/* (non-Javadoc)
	 * @see asgn2RollingStock.RollingStock#toString()
	 */
	@Override
	public String toString() {
		return "Passenger(" + this.numberOnBoard() + "/" + this.numberOfSeats() + ")";
	}
	
}
