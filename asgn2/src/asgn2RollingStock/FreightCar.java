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
	private static final String General = "G";
	private static String Refrigerated = "R"; //final missing here? - JW
	private static final String Dangerous = "D";
	
	// I'd possibly create a char array and then do if(in array){..} else {..} - JW
	
	private String goodsType;		
	
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException {
		super(grossWeight);
		if (goodsType != General && goodsType != Refrigerated && goodsType != Dangerous) {
			throw new TrainException ("Goods type must be one of the following: " +
					General + ", " + Refrigerated + ",  or " + Dangerous);
		}
		this.goodsType = goodsType;
	}
	
	public String goodsType() {
		return this.goodsType;
	}

	@Override
	public String toString() {
		return "Freight(" + this.goodsType() + ")";
	}
	
	
}
