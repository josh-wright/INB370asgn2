/**
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Robert
 *
 */
public class Locomotive extends RollingStock {
	
	final Integer POWER_CLASS_MINIMUM = 1;
	final Integer POWER_CLASS_MAXIMUM = 9;
	final char ENGINE_ELECTRIC = 'E';
	final char ENGINE_DIESEL = 'D';
	final char ENGINE_STEAM = 'S';
		
	public String classification;
	private int powerClass;
	
	public Locomotive(Integer grossWeight, String classification) throws TrainException {
		super(grossWeight);
		
		String classString = Character.toString(classification.charAt(0));
		char engineType = classification.charAt(1);
		
		if (!Character.isLetter(engineType)) {
			throw new TrainException("Second character of classification must be a letter matching" +
									 "either of the following: " + ENGINE_ELECTRIC + ", " + ENGINE_DIESEL
									 + ", or " + ENGINE_STEAM);
		}
		
		try {
			powerClass = Integer.parseInt(classString);
		} catch (NumberFormatException nfe) {
			throw new TrainException ("First character of classification must be an integer between " + 
									   POWER_CLASS_MINIMUM + " and " + POWER_CLASS_MAXIMUM);
		}
			
		if (powerClass < POWER_CLASS_MINIMUM) {
			throw new TrainException("Locomotive must have a power class of at least " + POWER_CLASS_MINIMUM);
		} else if (powerClass > POWER_CLASS_MAXIMUM) {
			throw new TrainException("Locomotive must have a power class of less than " + POWER_CLASS_MAXIMUM);
		} else if (engineType != 'E' && engineType != 'D' && engineType != 'S') {
			throw new TrainException("Locomotive must have an engine class of either " + ENGINE_ELECTRIC + 
					", " + ENGINE_DIESEL + ", or " + ENGINE_STEAM);
		} else {
			this.classification = classification;
		}
	}
	
	public Integer power() {
		final int POWER_MULTIPLIER = 100;
			
		return this.powerClass * POWER_MULTIPLIER;
	}

	@Override
	public String toString() {
		return "Loco(" + this.classification + ")";
	}
	
}
