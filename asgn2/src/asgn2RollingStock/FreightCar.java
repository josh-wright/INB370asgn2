/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert
 *
 */
public class FreightCar extends RollingStock {
	private final String GENERAL = "G";
	private final String REFRIGERATED = "R";
	private final String DANGEROUS = "D";
	
	private String goodsType;		
	
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException {
		super(grossWeight);
		if (goodsType != GENERAL && goodsType != REFRIGERATED && goodsType != DANGEROUS) {
			throw new TrainException ("Goods type must be one of the following: " +
					GENERAL + ", " + REFRIGERATED + ",  or " + DANGEROUS);
		} else {
			this.goodsType = goodsType;
		}
	}
	
	public String goodsType() {
		return this.goodsType;
	}

	@Override
	public String toString() {
		return "Freight(" + this.goodsType() + ")";
	}
	
	
}
