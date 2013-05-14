/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert Dempsey (Student Number: N5400872)
 *
 */
public class Locomotive extends RollingStock {
	
	final static Integer PowerClassMinimum = 1;
	final static Integer PowerClassMaximum = 9;
	final static char EngineElectric = 'E';
	final static char EngineDiesel = 'D';
	final static char EngineSteam = 'S';
		
	public String classification;
	private int powerClass;
	
	/**
	 * Construct a locomotive
	 * @param Integer grossWeight - the weight of the locomotive
	 * @param String classification - two character representation of the locomotive's power and engine type
	 * @throws TrainException if gross weight is zero or less or its classification is invalid
	 */
	public Locomotive(Integer grossWeight, String classification) throws TrainException {
		super(grossWeight);
		
		// get first character of classification and convert to string and assign to a powerClass string
		String powerClassString = Character.toString(classification.charAt(0));
		char engineType = classification.charAt(1);
		
		// try parsing powerClassString as an int to test if it is numeric
		try {
			powerClass = Integer.parseInt(powerClassString);
		} catch (NumberFormatException nfe) {
			throw new TrainException ("First character of classification must be an integer between " + 
									   PowerClassMinimum + " and " + PowerClassMaximum);
		}
		
		// check boundary cases for powerClass
		if (powerClass < PowerClassMinimum) {
			throw new TrainException("Locomotive must have a power class of at least " + PowerClassMinimum);
		} else if (powerClass > PowerClassMaximum) {
			throw new TrainException("Locomotive must have a power class of less than " + PowerClassMaximum);
		} else if (engineType != 'E' && engineType != 'D' && engineType != 'S') {
			throw new TrainException("Locomotive must have an engine class of either " + EngineElectric + 
					", " + EngineDiesel + ", or " + EngineSteam);
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
