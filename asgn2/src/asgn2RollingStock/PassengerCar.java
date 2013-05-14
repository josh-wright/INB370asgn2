/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert
 *
 */
public class PassengerCar extends RollingStock {
	
	private final static Integer MinimumDeparture = 0; //??? - JW
	private static final Integer MinimumSeats = 0;
	
	private Integer numberOfSeats = 0;
	private Integer numberOnBoard = 0;

	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException {
		super(grossWeight);
		if (numberOfSeats < MinimumSeats) {
			throw new TrainException("Seats may not be less than " +  MinimumSeats + ".");
		} 
		this.numberOfSeats = numberOfSeats;
	}
	
	public void alight(Integer departingPassengers) throws TrainException {
		if (departingPassengers > this.numberOnBoard()) {
			throw new TrainException("Number of passengers alighting cannot exceed number on board");
		}
		numberOnBoard -= departingPassengers;
	}
	
	public Integer board(Integer newPassengers) throws TrainException {
		numberOnBoard += newPassengers;
		return numberOnBoard - numberOfSeats();
	}
	
	public Integer numberOfSeats() {
		return this.numberOfSeats;
	}
	
	public Integer numberOnBoard() {
		return this.numberOnBoard;
	}

	@Override
	public String toString() {
		return "Passenger(" + this.numberOnBoard() + "/" + this.numberOfSeats() + ")";
	}
	
}
