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
	
	final static Integer PowerClassMinimum = 1;
	final static Integer PowerClassMaximum = 9;
	final static char EngineElectric = 'E';
	final static char EngineDiesel = 'D';
	final static char EngineSteam = 'S';
		
	public String classification;
	private int powerClass;
	
	public Locomotive(Integer grossWeight, String classification) throws TrainException {
		super(grossWeight);
		
		String classString = Character.toString(classification.charAt(0));
		char engineType = classification.charAt(1);
		
		if (!Character.isLetter(engineType)) {
			throw new TrainException("Second character of classification must be a letter matching" +
									 "either of the following: " + EngineElectric + ", " + EngineDiesel
									 + ", or " + EngineSteam);
		}
		
		try {
			powerClass = Integer.parseInt(classString);
		} catch (NumberFormatException nfe) {
			throw new TrainException ("First character of classification must be an integer between " + 
									   PowerClassMinimum + " and " + PowerClassMaximum);
		}
			
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
	
	public Integer power() {
		final int POWER_MULTIPLIER = 100;
			
		return this.powerClass * POWER_MULTIPLIER;
	}

	@Override
	public String toString() {
		return "Loco(" + this.classification + ")";
	}
	
}
