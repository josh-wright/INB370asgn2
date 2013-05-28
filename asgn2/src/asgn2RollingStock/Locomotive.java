package asgn2RollingStock;

import java.util.Arrays;

import asgn2Exceptions.TrainException;

/**
 * <p>A locomotive is a railway carriage with the ability to
 * propel itself and pull (or push) other carriages.  Thus
 * the primary distinguishing characteristic of a locomotive
 * is how much weight it can pull.
 * </p><p>  
 * However, calculating the total amount of weight a locomotive can
 * move depends on how much "tractive force" it can generate,
 * which in turn depends on the raw horsepower of the engine,
 * the amount of friction between the train's wheels and
 * the track, the track's grade, and whether we are referring
 * to the "starting" force or the "continuous" force.  (As
 * anyone who has ever pushed a stalled car knows,
 * it takes much more effort to get a vehicle moving
 * than to keep it moving once it is already in motion.)  
 * </p><p>
 * Therefore, to keep things simple, most railway operators use
 * a system of discrete "classification codes" to describe how
 * powerful a locomotive is.  For our purposes we adopt a model
 * similar to that used by various UK regional railways in
 * which locomotives are classified by a two-character code:
 * </p><p><ol>
 * <li> A numeric "power class" in the range 1 to 9.
 * </li>
 * <li> An alphabetic "engine type" consisting of either
 * "E" for electric, "D" for diesel or "S" for steam.
 * </li>
 * </ol>
 * </p><p> For instance, a locomotive with classification code
 * "4S" is a steam engine in power class 4.
 * </p><p>
 * To determine how much weight a locomotive can move, we
 * shall use a simple formula in which the maximum weight
 * the locomotive can pull, in tonnes, is its power class
 * times 100.  For instance, a locomotive with classification
 * "4S" can pull at most 4&nbsp;X&nbsp;100&nbsp;=&nbsp;400&nbsp;tonnes.
 * <strong>NB:</strong> This figure includes the weight of the
 * locomotive itself.  Thus a locomotive classified as only "1D"
 * which weighs 180 tonnes cannot move!
 * </p>
 * 
 * @author Robert Dempsey (n5400872)
 * @author Joshua Wright (n6366066)
 * @version 1.1
 *
 */
public class Locomotive extends RollingStock {
	private static final Character[] ENGINGE_TYPE = {'E', 'D', 'S'};
	private final static Integer POWERCLASS_MIN = 1;
	private final static Integer POWERCLASS_MAX = 9;
		
	private String classification;
	private Integer powerClass;
	
	/**
	 * Constructs a new locomotive object with a fixed gross weight and
	 * classification code.
	 * 
	 * @param grossWeight the locomotive's (fully-laden) weight in tonnes
	 * @param classification the locomotive's two-character classification code
	 * @throws TrainException if the locomotive's weight is not strictly
	 * positive or if its classification code is invalid
	 * 
	 * @author Robert Dempsey (n5400872)
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
	 * Returns how much total weight the locomotive can pull (including itself),
	 * calculated as explained above.
	 * 
	 * @return the locomotive's "pulling power" in tonnes
	 * @author Robert Dempsey (n5400872)
	 */
	public Integer power() {
		final int POWER_MULTIPLIER = 100;
			
		return powerClass * POWER_MULTIPLIER;
	}

	/**
	 * Returns a human-readable description of the locomotive.  This has the form
	 * "<code>Loco(</code><em>x</em><code>)</code>" where <em>x</em>
	 * is the locomotive's two-character classification code.
	 * 
	 * @return a human-readable description of the locomotive
	 * @author Robert Dempsey (n5400872)
	 */
	@Override
	public String toString() {
		return "Loco(" + this.classification + ")";
	}
	
}
