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
 * @author JoshuaWright(n6366066)
 */
public class DepartingTrain {
	
	// initialise private variables
	private ArrayList<RollingStock> departingTrain;
	private Integer currentCarriage = -1;
	//private Integer numberOnBoard = 0;
	private Integer numberOfSeats = 0;
	
	/**
	 * Constructs train with no rolling stock attached
	 */
	public DepartingTrain() {
		departingTrain = new ArrayList<RollingStock>();
	}
	
	/**
	 * Get first carriage of train
	 * @return RollingStock - first carriage of train
	 */
	public RollingStock firstCarriage() {
		final Integer firstCarriage = 0;
		currentCarriage = firstCarriage;

		// return first carriage or null if none exists
		try {
			return departingTrain.get(firstCarriage);
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
		int noOnBoard = 0;
		// add number of passengers on each passenger car to total number on board
		for (int i = 0; i < departingTrain.size(); i++) {
			if (departingTrain.get(i).getClass() == PassengerCar.class) {
				PassengerCar passengerCar = (PassengerCar) departingTrain.get(i);
				noOnBoard += passengerCar.numberOnBoard();
			}
		}
		return noOnBoard;
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
		Integer remainingPassengers = newPassengers;
		Integer tempCurrentCarriage = currentCarriage;
		firstCarriage(); //Skip Loco
		
		for (int i = 0; i < departingTrain.size()-1; i++){
			RollingStock carriage = nextCarriage();
			if (carriage.getClass() == PassengerCar.class) {
				if (((PassengerCar)carriage).numberOfSeats() >= ((PassengerCar)carriage).numberOnBoard()){
					Integer board = ((PassengerCar)carriage).numberOfSeats() - ((PassengerCar)carriage).numberOnBoard();
					if (board >= remainingPassengers){
						remainingPassengers = ((PassengerCar)carriage).board(remainingPassengers);
					} else {
						remainingPassengers -= board;
						((PassengerCar)carriage).board(board);
					}
				}
			}
		}
		currentCarriage = tempCurrentCarriage;
		return remainingPassengers;
	}
	
	/**
	 * Remove passengers from train
	 * @param (Integer) alightPassengers - the number of passengers wanting to board the train
	 * @throws TrainException if passengers boarding is negative
	 */
	public void alight(Integer alightPassengers)
            throws TrainException {	
		Integer tempCurrentCarriage = currentCarriage;
		firstCarriage(); //Skip Loco
		if (alightPassengers <= numberOnBoard()){
			for (int i = 0; i < departingTrain.size() - 1; i++){
				RollingStock carriage = nextCarriage();
				if (carriage.getClass() == PassengerCar.class){
					PassengerCar passengerCarriage = (PassengerCar) carriage;
					if (alightPassengers > passengerCarriage.numberOnBoard()) {
						Integer alight = alightPassengers - passengerCarriage.numberOnBoard();
						alightPassengers -= alight;
						passengerCarriage.alight(alight);
					} else {
						
					}
				}
			}
		} else {
			throw new TrainException("Trying to alight too many passengers!");
		}
		currentCarriage = tempCurrentCarriage;
	}
	
	/**
	 * @return
	 */
	public boolean trainCanMove() {
		Integer power = ((Locomotive) firstCarriage()).power();
		Integer weight = firstCarriage().getGrossWeight();
		
		for (int i = 0; i < departingTrain.size() - 1; i++){
			weight += nextCarriage().getGrossWeight();
		}
		
		if (power > weight) { return true; } else { return false; }
	}
	
	/**
	 * Add a carriage to the train
	 * @param RollingStock newCarriage - carriage to be added to the train
	 * @throws TrainException if passengers are currently on board or addition would result
	 * in invalid train configuration
	 */
	public void addCarriage(RollingStock newCarriage)
            throws TrainException {
		
		boolean trainEmpty = departingTrain.size() == 0;
		
		// ensure first carriage is a Locomotive
		if (trainEmpty && newCarriage.getClass() != Locomotive.class) {
			throw new TrainException("First carriage of a train must be a Locomotive.");	
		} else if (numberOnBoard() > 0) {
			throw new TrainException("A new carriage cannot be added when there are passengers on board.");		
		} else if (!trainEmpty && newCarriage.getClass() == Locomotive.class) {
			throw new TrainException("Only one locomotive is allowed per train");		
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
		if (numberOnBoard() > 0) {
			throw new TrainException("Cannot shunt a train whilst passengers are on board");
		} else if(departingTrain.size() == 0){
			throw new TrainException("No carriages to remove from train");
		} else {
			departingTrain.remove(departingTrain.size()-1);
		}
	 }
	 
	 /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String trainString = null;
		RollingStock firstcar = firstCarriage();
		if (firstcar == null) { return null; }
		else {
			trainString = ((Locomotive)firstcar).toString();
			for (int i = 0; i < departingTrain.size() - 1; i++){
				trainString += "-";
				RollingStock car = nextCarriage();
				if (car.getClass() == PassengerCar.class){
					trainString += ((PassengerCar) car).toString();
				} else {
					trainString += ((FreightCar) car).toString();
				}
			}
		}
		
		return trainString;
	 }
}
