/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert Dempsey (Student Number: N5400872)
 *
 */
public class FreightCar extends RollingStock {
	private static final String General = "G";
	private static final String Refrigerated = "R";
	private static final String Dangerous = "D";
	
	// I'd possibly create a char array and then do if(in array){..} else {..} - JW
	// Thought about it. The code might look a bit cleaner but I think it's okay like this - RD
	
	private String goodsType;		
	
	/**
	 * Construct FreightCar 
	 * @param Integer grossWeight - the gross weight of the freight car 
	 * @param String goodsType - the type of goods the freight car will carry
	 * @throws TrainException if gross weight is zero or less or type of goods is invalid
	 */
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException {
		super(grossWeight);
		if (!goodsType.equals(General) && !goodsType.equals(Refrigerated) && !goodsType.equals(Dangerous)) {
			throw new TrainException ("Goods type must be one of the following: " +
					General + ", " + Refrigerated + ",  or " + Dangerous);
		}
		this.goodsType = goodsType;
	}
	
	/**
	 * Get goods type for the freight car
	 * @return String goodsType
	 */
	public String goodsType() {
		return goodsType;
	}

	/* (non-Javadoc)
	 * @see asgn2RollingStock.RollingStock#toString()
	 */
	@Override
	public String toString() {
		return "Freight(" + this.goodsType() + ")";
	}
	
	
}
