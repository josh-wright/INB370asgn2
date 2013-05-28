package asgn2Train;

import java.util.ArrayList;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 * <p>
 * A train is a sequence of carriages.  This class
 * defines various operations that can be performed to prepare a
 * long-distance train for departure.
 * </p><p>
 * We assume that a train can be assembled from any available
 * rolling stock, including locomotives, passenger cars and
 * freight cars.  However, they may be configured in only a certain
 * sequence:</p>
 * <ol>
 * <li>The first carriage must be a locomotive (and there can be only
 * one locomotive per train).</li>
 * <li>This may be followed by zero or more passenger cars.</li>
 * <li>These may be followed by zero or more freight cars.</li>
 * </ol>
 * <p>
 * Any other configurations of rolling stock are disallowed.
 * </p><p>
 * The process of preparing the train for departure occurs in
 * two stages:
 * </p>
 * <ol>
 * <li>The train is assembled from individual carriages.  New
 * carriages may be added to the rear of the train only.
 * (Similarly, carriages may be removed from the rear of the train
 * only.)</li>
 * <li>Passengers board the train.  For safety reasons, no
 * carriage shunting operations may be performed when any passengers
 * are on board the train.</li>
 * </ol>
 * 
 * @author Robert Dempsey (N5400872)
 * @author Joshua Wright(n6366066)
 * @version 1.0
 */
public class DepartingTrain {
	
	// initialize private variables
	private ArrayList<RollingStock> departingTrain;
	private Integer currentCarriage = -1;
	
	/**
	 * Constructs a (potential) train object containing no carriages
	 * (yet).
	 *
	 * @author Robert Dempsey (N5400872)
	 */
	public DepartingTrain() {
		departingTrain = new ArrayList<RollingStock>();
	}
	
	/**
	 * <p>Returns the first carriage on the train (which must be a
	 * locomotive).  Special value <code>null</code> is returned
	 * if there are no carriages on the train at all.
	 * </p><p>
	 * NB: When combined with method <code>nextCarriage</code>, this
	 * method gives us a simple ability to iteratively examine each of the
	 * train's carriages.
	 * </p>
	 * 
	 * @return the first carriage in the train, or <code>null</code> if
	 * there are no carriages
	 *
	 * @author Robert Dempsey (N5400872)
	 */
	public RollingStock firstCarriage() {
		final Integer FIRST_CARRIAGE = 0;
		currentCarriage = FIRST_CARRIAGE;

		// return first carriage or null if none exists
		try {
			return departingTrain.get(FIRST_CARRIAGE);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Returns the next carriage in the train after the one returned
	 * by the immediately preceding call to either this method or
	 * method <code>firstCarriage</code>.  Special value <code>null</code>
	 * is returned if there is no such carriage.  If there has been no
	 * preceding call to either <code>firstCarriage</code> or
	 * <code>nextCarriage</code>, this method behaves like
	 * <code>firstCarriage</code>, i.e., it
	 * returns the first carriage in the train, if any.
	 * </p><p>
	 * NB: When combined with method <code>firstCarriage</code>, this
	 * method gives us a simple ability to iteratively examine each of the
	 * train's carriages.
	 * </p>
	 *  
	 * @return the train's next carriage after the one returned by the
	 * immediately preceding call to either <code>firstCarriage</code> or
	 * <code>nextCarriage</code>, or <code>null</code> if there is no
	 * such carriage
	 * 
	 * @author Robert Dempsey (N5400872)
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
	 * Returns the total number of passengers currently on the train,
	 * counting all passenger cars.
	 * 
	 * @return the number of passengers on the train
	 * 
	 * @author Robert Dempsey (N5400872)
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
	 * Returns the total number of seats on the train (whether occupied
	 * or not), counting all passenger cars.
	 * 
	 * @return the number of seats on the train
	 * 
	 * @author Robert Dempsey (N5400872)
	 */
	public Integer numberOfSeats() {
		int noOfSeats = 0;
		// add number of seats on each passenger car to total number of seats
		for (int i = 0; i < departingTrain.size(); i++) {
			if (departingTrain.get(i).getClass() == PassengerCar.class) {
				PassengerCar passengerCar = (PassengerCar) departingTrain.get(i);
				noOfSeats += passengerCar.numberOfSeats();
			}
		}
		return noOfSeats;
	}
	
	 
	/**
	 * Adds the given number of people to passenger carriages on
	 * the train.  We do not specify where the passengers must sit, so
	 * they can be allocated to any vacant seat in any passenger car.
	 * 
	 * @param newPassengers the number of people wish to board the train
	 * @return the number of people who were unable to board the train because
	 * they couldn't get a seat
	 * @throws TrainException if the number of new passengers is negative
	 * 
	 * @author Joshua Wright(n6366066)
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
	 * <p>
	 * Returns whether or not the train is capable of moving.  A train
	 * can move if its locomotive's pulling power equals or exceeds the
	 * train's total weight (including the locomotive itself).
	 * </p><p>
	 * In the degenerate case of a "train" which doesn't have any rolling
	 * stock at all yet, the method returns true.
	 * </p>
	 * 
	 * @return true if the train can move (or contains no carriages), false otherwise
	 *
	 * @author Joshua Wright(n6366066)
	 */
	public boolean trainCanMove() {
		Integer power = ((Locomotive) firstCarriage()).power();
		Integer weight = firstCarriage().getGrossWeight();
		
		for (int i = 0; i < departingTrain.size() - 1; i++){
			weight += nextCarriage().getGrossWeight();
		}
		
		if (power >= weight) { return true; } else { return false; }
	}
	
	/**
	 * <p>Adds a new carriage to the end of the train.  However, a new
	 * carriage may be added only if the resulting train configuration is
	 * valid, as per the rules listed above.
	 * Furthermore, shunting operations may
	 * not be performed if there are passengers on the train.
	 * </p><p>
	 * <strong>Hint:</strong> You may find Java's in-built
	 * <code>instanceof</code> operator useful when implementing this method
	 * (and others in this class).</p>
	 * 
	 * @param newCarriage the new carriage to be added
	 * @throws TrainException if adding the new carriage would produce an
	 * invalid train configuration, or if there are passengers on the train
	 *
	 * @author Robert Dempsey (N5400872)
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
	 * Removes the last carriage from the train.  (This may be the locomotive if
	 * it is the only item of rolling stock on the train.)  However, shunting
	 * operations may not be performed if there are passengers on the train.
	 * 
	 * @throws TrainException if there is no rolling stock on the "train", or
	 * if there are passengers on the train.
	 *
	 * @author Joshua Wright(n6366066)
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
	 
	/**
	 * <p>
	 * Returns a human-readable description of the entire train.  This has the
	 * form of a hyphen-separated list of carriages, starting with the
	 * locomotive on the left.  The description is thus a string
	 * "<em>a</em><code>-</code><em>b</em><code>-</code>...<code>-</code><em>z</em>",
	 * where <em>a</em> is the human-readable description of the first carriage
	 * (the locomotive), <em>b</em> is the description of the second carriage,
	 * etc, until the description of the last carriage <em>z</em>.  (Note that
	 * there should be no hyphen after the last carriage.)  For example,
	 * a possible train description may be
	 * "<code>Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)</code>".
	 * </p><p>
	 * In the degenerate case of a "train" with no carriages, the empty string is
	 * returned.
	 * </p>
	 * 
	 * @return a human-readable description of the entire train
	 * 
	 * @author Joshua Wright(n6366066)
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
