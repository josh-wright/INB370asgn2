/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert
 *
 */
public abstract class RollingStock extends Object {
	private static final Integer FreightLowerLimit = 1;
	
	// fields
	private Integer grossWeight;
	
	
	// Constructor
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight < FreightLowerLimit) {
			throw new TrainException("Freight must be greater than zero (0)");
		}
		this.grossWeight = grossWeight;
	}
	
	// Accessor for grossWeight;
	public Integer getGrossWeight() {
		return grossWeight;
	}
	
	// Overrides toString() in Object to output train details in human readable format
	@Override
	public abstract String toString();
}
