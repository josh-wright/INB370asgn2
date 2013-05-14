/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert Dempsey (Student Number: N5400872)
 *
 */
public abstract class RollingStock extends Object {
	private static final Integer FreightLowerLimit = 1;
	
	private Integer grossWeight;	
	
	/**
	 * Construct the carriage
	 * @param Integer grossWeight - gross weight of the carriage
	 * @throws TrainException if gross weight is 0 or less
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight < FreightLowerLimit) {
			throw new TrainException("Freight must be greater than zero (0)");
		}
		this.grossWeight = grossWeight;
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
