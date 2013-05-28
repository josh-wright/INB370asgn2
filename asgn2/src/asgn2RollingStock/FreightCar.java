package asgn2RollingStock;

import java.util.Arrays;

import asgn2Exceptions.TrainException;

/**
 * <p>Freight cars are designed to handle a variety of goods.
 * For the purposes of this assignment we assume there are three
 * freight car types of interest, characterised by the kinds of
 * goods they are designed to carry:</p>
 * <ul>
 * <li>"G" - General goods</li>
 * <li>"R" - Refrigerated goods</li>
 * <li>"D" - Dangerous materials</li>
 * </ul>
 * 
 * @author Robert Dempsey (n5400872)
 * @author Joshua Wright (n6366066)
 * @version 1.0
 */
public class FreightCar extends RollingStock {
	private static final Character[] GOODS_TYPES = {'G', 'R', 'D'};
	
	private String goodsType;		
	
	/**
	 * Constructs a freight car object.
	 * 
	 * @param grossWeight the freight car's gross weight (fully-laden), in tonnes
	 * @param goodsType the type of goods the car is designed to carry (either
	 * "G", "R" or "D")
	 * @throws TrainException if the gross weight is not positive or if
	 * the goods' type is invalid
	 * 
	 * @author Robert Dempsey (n5400872)
	 * @author Joshua Wright (n6366066)
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
	 * Returns the type of goods this carriage was designed to carry.
	 * (Simulates someone checking the label on the freight car to
	 * determine what's inside.)
	 * 
	 * @return the goodsType (G", "R" or "D")
	 * @author Robert Dempsey (n5400872)
	 */
	public String goodsType() {
		return goodsType;
	}

	/**
	 * Returns a human-readable description of the freight car.  This has the form
	 * "<code>Freight(</code><em>x</em><code>)</code>" where <em>x</em> is a character
	 * ("G", "R" or "D") indicating the type of goods the car is
	 * designed to carry.
	 * 
	 * @return a human-readable description of the freight car
	 * 
	 * @author Robert Dempsey (n5400872)
	 */
	@Override
	public String toString() {
		return "Freight(" + this.goodsType() + ")";
	}
	
	
}
