/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * RollingStock.java 
 * Implements global functions for RollingStock class 
 * @author Joshua Wright (n6366066)
 * @author Robert Dempsey (n5400872)
 */
public abstract class RollingStock extends Object {
	private static final Integer GROSSWEIGHT_MIN = 1;
	
	private Integer grossWeight;	
	
	/**
	 * Construct the carriage
	 * @param Integer grossWeight - gross weight of the carriage
	 * @throws TrainException if gross weight is 0 or less
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight < GROSSWEIGHT_MIN) {
			throw new TrainException("Gross Weight must be greater than zero (0)");
		} else {
			this.grossWeight = grossWeight;
		}
	}
	
	
	/**
	 * Get gross weight of carriage
	 * @return Integer - gross weight
	 */
	public Integer getGrossWeight() {
		return grossWeight;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
}
