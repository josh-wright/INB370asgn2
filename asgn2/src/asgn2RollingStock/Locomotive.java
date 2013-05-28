/**
 * 
 */
package asgn2RollingStock;

import java.util.Arrays;

import asgn2Exceptions.TrainException;

/**
 * Locomotive.java
 * Extends RollingStock Class. Provides functions relating to Locomotives
 * @author Robert Dempsey (n5400872)
 * @author Joshua Wright (n6366066)
 */
public class Locomotive extends RollingStock {
	private static final Character[] ENGINGE_TYPE = {'E', 'D', 'S'};
	final static Integer POWERCLASS_MIN = 1;
	final static Integer POWERCLASS_MAX = 9;
		
	public String classification;
	private Integer powerClass;
	
	/**
	 * Construct a locomotive
	 * @param Integer grossWeight - the weight of the locomotive
	 * @param String classification - two character representation of the 
	 * 		  locomotive's power and engine type
	 * @throws TrainException 
	 */
	public Locomotive(Integer grossWeight, String classification) 
			throws TrainException {
		super(grossWeight);
		
		if (classification.length() > 2){ // Classification must be two chars
			throw new TrainException(classification + " is not a valid class" +
					"ification, Locomotive Classifications must be two chara" +
					"cters long consisting of a Power Class between " + 
					POWERCLASS_MIN + " and " + POWERCLASS_MAX + " and a " +
					"Engine Type of either " + ENGINGE_TYPE[0] + ", " +
					ENGINGE_TYPE[1] + " or " + ENGINGE_TYPE[2]);
		} else {
			classification = classification.toUpperCase();
			//Check First Char is Integer and second is Character
			try {
				this.powerClass = Integer.parseInt(Character.toString(
						classification.charAt(0)));
				if(powerClass < POWERCLASS_MIN ||
						powerClass > POWERCLASS_MAX){
					throw new TrainException ("The power class you have entered " +
							"is invalid. Locomotive Power Class must be an integ" +
							"er between " + POWERCLASS_MIN + " and " + 
							POWERCLASS_MAX);
				}
			} catch (Exception e){
				throw new TrainException ("The power class you have entered " +
						"is invalid. Locomotive Power Class must be an integ" +
						"er between " + POWERCLASS_MIN + " and " + 
						POWERCLASS_MAX);
			}
			try {
				@SuppressWarnings("unused")
				String engineTypeParse = Character.toString(
						classification.charAt(1));
				throw new TrainException ("The engine type you have entered " +
						"is invalid. Locomotive Engine Type must be either " 
						+ ENGINGE_TYPE[0] + ", " + ENGINGE_TYPE[1] + " or " + 
						ENGINGE_TYPE[2]);
			} catch (Exception e){
				if (!Arrays.asList(ENGINGE_TYPE).contains(
						classification.charAt(1))){
					throw new TrainException ("The engine type you have entered " +
							"is invalid. Locomotive Engine Type must be either " 
							+ ENGINGE_TYPE[0] + ", " + ENGINGE_TYPE[1] + " or " + 
							ENGINGE_TYPE[2]);
				}
			}
		}
		this.classification = classification;
	}
	
	/**
	 * Get power of locomotive
	 * @return Integer - power of locomotive
	 */
	public Integer power() {
		final int POWER_MULTIPLIER = 100;
			
		return powerClass * POWER_MULTIPLIER;
	}

	/* (non-Javadoc)
	 * @see asgn2RollingStock.RollingStock#toString()
	 */
	@Override
	public String toString() {
		return "Loco(" + this.classification + ")";
	}
	
}
