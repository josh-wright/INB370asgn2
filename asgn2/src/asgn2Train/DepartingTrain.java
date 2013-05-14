package asgn2Train;

import java.util.ArrayList;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 *
 * @author Robert Dempsey (Student Number: N5400872)
 */
public class DepartingTrain {
	
	// initialise private variables
	private ArrayList<RollingStock> departingTrain;
	private Integer currentCarriage = 0;
	private Integer numberOnBoard = 0;
	private Integer numberOfSeats = 0;
	
	/**
	 * Constructs empty train
	 */
	public DepartingTrain() {
		departingTrain = new ArrayList<RollingStock>();
	}
	
	/**
	 * Get first carriage of train
	 * @return RollingStock - first carriage of train
	 */
	public RollingStock firstCarriage() {
		final int FirstCarriage = 0;
		
		// return first carriage or null if none exists
		try {
			return departingTrain.get(FirstCarriage);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Get carriage after previously determined carriage
	 * @return RollingStock - next carriage
	 */
	public RollingStock nextCarriage() {
		currentCarriage++;
		
		// return next carriage or null if none exists
		try {
			return departingTrain.get(currentCarriage);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Get number of passengers on board whole train
	 * @return Integer of number of passengers currently on board
	 */
	public Integer numberOnBoard() {
		
		// add number of passengers on each passenger car to total number on board
		for (int i = 0; i < departingTrain.size(); i++) {
			if (departingTrain.get(i).getClass() == PassengerCar.class) {
				PassengerCar passengerCar = (PassengerCar) departingTrain.get(i);
				numberOnBoard += passengerCar.numberOnBoard();
			}
		}
		return numberOnBoard;
	}
	
	/**
	 * Get total number of passenger seats on the train
	 * @return Integer - Number of seats on the train
	 */
	public Integer numberOfSeats() {
		
		// add number of seats on each passenger car to total number of seats
		for (int i = 0; i < departingTrain.size(); i++) {
			if (departingTrain.get(i).getClass() == PassengerCar.class) {
				PassengerCar passengerCar = (PassengerCar) departingTrain.get(i);
				numberOfSeats += passengerCar.numberOfSeats();
			}
		}
		return numberOfSeats;
	}
	
	/**
	 * Add passengers to train
	 * @param Integer newPassengers - the number of passengers wanting to board the train
	 * @return Integer - number of passengers that could not fit on the train
	 * @throws TrainException if passengers boarding is negative
	 */
	public Integer board(Integer newPassengers)
            throws TrainException {
		
		final Integer MinimumBoard = 0;
		Integer excessPassengers = 0;
		
		if (newPassengers < MinimumBoard) {
			throw new TrainException("Number of passengers boarding must be greater than " + 
									  MinimumBoard + ".");
		}
		
		// if boarding passengers would cause the train to be overloaded, fill the carriage 
		if ((numberOnBoard() + newPassengers) > numberOfSeats()) {
			excessPassengers = numberOnBoard() + newPassengers - numberOfSeats();
			numberOnBoard = numberOfSeats();
		} else {
			numberOnBoard += newPassengers;
		}
		return excessPassengers;
	}
	
	/**
	 * @return
	 */
	public boolean trainCanMove() {
		
		// not yet implemented
		return false;
	}
	
	/**
	 * Add a carriage to the train
	 * @param RollingStock newCarriage - carriage to be added to the train
	 * @throws TrainException if passengers are currently on board or addition would result
	 * in invalid train configuration
	 */
	public void addCarriage(RollingStock newCarriage)
            throws TrainException {
		
		// ensure first carriage is a Locomotive
		if (this.firstCarriage() == null && newCarriage.getClass() != Locomotive.class) {
			throw new TrainException("First carriage of a train must be a Locomotive.");	
		} else if (numberOnBoard() > 0) {
			throw new TrainException("A new carriage cannot be added when there are passengers on board.");		
		} 
		
		// ensure a passenger car cannot be added after a freight car
		for (int i = 0; i < departingTrain.size(); i++) {
			if (departingTrain.get(i).getClass() == FreightCar.class && 
				newCarriage.getClass() == PassengerCar.class) {
				
				throw new TrainException("Cannot add a passenger car after a freight car.");
			}
		}
		departingTrain.add(newCarriage);
	}
	
	 /**
	 * @throws TrainException
	 */
	public void removeCarriage()
             throws TrainException {		 
	 }
	 
	 /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		 return null;
	 }
}
