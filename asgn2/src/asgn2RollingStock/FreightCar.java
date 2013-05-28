/**
 * 
 */
package asgn2RollingStock;

import java.util.Arrays;

import asgn2Exceptions.TrainException;

/**
 * FreightCar.java
 * Extends RollingStock Class. Provides functions 
 * @author Robert Dempsey (n5400872)
 * @author Joshua Wright (n6366066)
 */
public class FreightCar extends RollingStock {
	private static final Character[] GOODS_TYPES = {'G', 'R', 'D'};
	
	private String goodsType;		
	
	/**
	 * Construct FreightCar 
	 * @param Integer grossWeight - the gross weight of the freight car 
	 * @param String goodsType - the type of goods the freight car will carry
	 * @throws TrainException if gross weight is zero or less or type of goods 
	 * is invalid
	 */
	public FreightCar(Integer grossWeight, String goodsType) 
			throws TrainException {
		super(grossWeight);
		if (goodsType.length() > 1){
			throw new TrainException (goodsType + 
					" is not a valid Goods Type, Please select G, R, or D.");
		} else {
			goodsType = goodsType.toUpperCase();
			Character type = goodsType.charAt(0);
			if (Arrays.asList(GOODS_TYPES).contains(type)){
				this.goodsType = goodsType;
			} else {
				throw new TrainException (goodsType + 
						" is not a valid Goods Type, Please select G, R, or D.");
			}
		}
	}
	
	/**
	 * Get goods type for the freight car
	 * @return (String) goodsType
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
