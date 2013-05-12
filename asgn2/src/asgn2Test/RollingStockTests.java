package asgn2Test;

import asgn2RollingStock.*;
import asgn2Exceptions.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RollingStock Test Class
 * Test interactions of the RollingStock class for expected outcomes
 * 
 * @author JoshuaWright(n6366066@student.qut.edu.au)
 *
 */
public class RollingStockTests {

	/* - Freight Car Tests ------------------------ */
	
	/**
	 * Tests the FreightCar Constructor with valid constraints
	 */
	@Test
	public void testFreightCarConstructorValid() 
			throws TrainException {
		final String GOODSTYPE = "G";
		final Integer GROSSWEIGHT = 9;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
		assertEquals(GOODSTYPE, freightCar.getGoodsType(), 0);
		assertEquals(GROSSWEIGHT, freightCar.getGrossWeight(), 0);
	}
	
	/**
	 * Tests the FreightCar Constructor with invalid constraints
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorInvalid() 
			throws TrainException {
		final String GOODSTYPE = "X";
		final Integer GROSSWEIGHT = -9;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with invalid Goods Type
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorInvalidGoodsType() 
			throws TrainException {
		final String GOODSTYPE = "X";
		final Integer GROSSWEIGHT = 9;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with Zero Weight
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorZeroWeight() 
			throws TrainException {
		final String GOODSTYPE = "D";
		final Integer GROSSWEIGHT = 0;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with positive weight
	 */
	@Test
	public void testFreightCarConstructorPostiveWeight() 
			throws TrainException {
		final String GOODSTYPE = "D";
		final Integer GROSSWEIGHT = 9;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with negative weight
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorNegativeWeight() 
			throws TrainException {
		final String GOODSTYPE = "D";
		final Integer GROSSWEIGHT = -9;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Test constructor FreightCar with non-numeric weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorNonNumericWeight() 
			throws TrainException {
		final String GOODSTYPE = "D";
		final String GROSSWEIGHT = "A";
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Test constructor FreightCar with non-integer weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorNonIntWeight() 
			throws TrainException {
		final String GOODSTYPE = "D";
		final double GROSSWEIGHT = -9.0;
		
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor ToString()
	 */
	@Test
	public void testFreightCarToString() 
			throws TrainException {
		final String GOODSTYPE = "D";
		final Integer GROSSWEIGHT = 9;
		final String TOSTRING = "Freight(D)"; // Rob - See API
		FreightCar freightCar = new FreightCar(GROSSWEIGHT, GOODSTYPE);
		String rollingStock = freightCar.toString();
		assertEquals(TOSTRING, rollingStock);
	}

	/* - Locomotive Tests ------------------------- */
	
	/**
	 *  Tests the Locomotive Constructor with valid inputs
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveConstructorValid()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		
		assertEquals(GROSSWEIGHT,locomotive.getGrossWeight());
		assertEquals(CLASSIFICATION, locomotive.getClassification());
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid inputs
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalid()
		throws TrainException {
		final int GROSSWEIGHT = -90;
		final String CLASSIFICATION = "11T";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid Engine Type
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalidEngineType()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "9T";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid power rating
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalidPowerRating()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "11S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with positive weight
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveConstructorPositiveWeight()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		
		assertEquals(GROSSWEIGHT,locomotive.getGrossWeight());
		assertEquals(CLASSIFICATION, locomotive.getClassification());
	}
	
	/**
	 *  Tests the Locomotive Constructor with zero weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorZeroWeight()
		throws TrainException {
		final int GROSSWEIGHT = 0;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with negative weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorNegativeWeight()
		throws TrainException {
		final int GROSSWEIGHT = -90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the locomotive with enough power to move itself
	 * @throws TrainException
	 */
	@Test
	public void testLocomotivePowerGreaterThanWeight()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		assertTrue(locomotive.getPower() > GROSSWEIGHT);
	}
	
	/**
	 *  Tests the locomotive with enough power to move itself
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotivePowerEqualsWeight()
		throws TrainException {
		final int GROSSWEIGHT = 100;
		final String CLASSIFICATION = "1S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the locomotive with enough power to move itself
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotivePowerLessThanWeight()
		throws TrainException {
		final int GROSSWEIGHT = 110;
		final String CLASSIFICATION = "1S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the locomotive for expected power output
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveCheckPowerOutput()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final int EXPECTEDPOWER = 400; // 4 * 100 - refer API rule
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		assertEquals(EXPECTEDPOWER, locomotive.getPower());
	}
	
	/**
	 * Tests ToString Output 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveToString()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final String TOSTRING = "Loco(4S)";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		assertEquals(TOSTRING, locomotive.ToString());
	}

	/* - Passenger Car Tests ---------------------- */
	
		//Tests Required
}
