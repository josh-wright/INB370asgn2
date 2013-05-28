package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Rolling stock are the individual carriages from which
 * a train is constructed.  This abstract class defines
 * characteristics which they all share, most notably having a
 * known gross weight, measured here in tonnes.  (There
 * are, of course many other important shared characteristics
 * of railway carriages, such as identifying codes, a
 * certain number of wheels, the track gauge they're designed
 * for, etc, but we don't need these for this assignment.)
 * 
 * @author Robert Dempsey (n5400872)
 * @version 1.0
 */
public abstract class RollingStock extends Object {
	private static final Integer GROSSWEIGHT_MIN = 1;
	
	private Integer grossWeight;	
	
	/**
	 * <p>Constructs a railway carriage with a specific gross
	 * weight (i.e., the carriage's weight when fully laden).
	 * We assume that this weight does not change once
	 * shunting operations have begun.  (Freight carriages
	 * are assumed to arrive at the marshalling yard already
	 * loaded, and we consider the weight of passengers to
	 * be negligible compared to the weight of the carriage
	 * itself.)
	 * </p><p>
	 * We require a railway carriage to have at least some weight,
	 * but put no arbitrary upper limit on its weight.  (In practice,
	 * though, locomotives normally weigh around 90 to 180 tonnes,
	 * passenger carriages weigh around 50 to 100 tonnes and laden
	 * freight cars weigh around 40 to 90 tonnes.)
	 * </p>
	 * 
	 * @param grossWeight the carriage's gross weight in tonnes
	 * @throws TrainException if the gross weight is not positive
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight < GROSSWEIGHT_MIN) {
			throw new TrainException("Gross Weight must be greater than zero (0)");
		} else {
			this.grossWeight = grossWeight;
		}
	}
	
	
	/**
	 * Returns the railway carriage's gross weight.
	 * 
	 * @return the carriage's gross weight, in tonnes
	 */
	public Integer getGrossWeight() {
		return grossWeight;
	}
	
	/**
	 * <p>
	 * Returns a human-readable description of this railway carriage.
	 * </p><p>
	 * In the context of the assignment, this method provides you with a textual
	 * description of the carriage which you can use to display the current
	 * train configuration in your user interface.
	 * </p><p>
	 * (Note for Javaphiles: The root <code>Object</code> class already provides a
	 * default <code>toString</code> method for all classes, so the
	 * purpose of this abstract method is to
	 * override the default one and <em>force</em> us to implement a
	 * <code>toString</code> method in all subclasses of
	 * <code>RollingStock</code>.)
	 * </p>
	 * 
	 * @return a printable description of the rolling stock
	 */
	@Override
	public abstract String toString();
}
