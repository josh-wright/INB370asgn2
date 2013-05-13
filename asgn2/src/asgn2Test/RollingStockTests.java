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
		assertEquals(GOODSTYPE, freightCar.goodsType());
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
		final Integer GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		
		assertEquals(GROSSWEIGHT,locomotive.getGrossWeight());
		//assertEquals(CLASSIFICATION, locomotive.getClassification());
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid inputs
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalid()
		throws TrainException {
		final Integer GROSSWEIGHT = -90;
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
		final Integer GROSSWEIGHT = 90;
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
		final Integer GROSSWEIGHT = 90;
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
		final Integer GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		
		assertEquals(GROSSWEIGHT,locomotive.getGrossWeight());
		//assertEquals(CLASSIFICATION, locomotive.getClassification());
	}
	
	/**
	 *  Tests the Locomotive Constructor with zero weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorZeroWeight()
		throws TrainException {
		final Integer GROSSWEIGHT = 0;
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
		final Integer GROSSWEIGHT = -90;
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
		final Integer GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		assertTrue(locomotive.power() > GROSSWEIGHT);
	}
	
	/**
	 *  Tests the locomotive with enough power to move itself
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotivePowerLessThanWeight()
		throws TrainException {
		final Integer GROSSWEIGHT = 110;
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
		final Integer GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final Integer EXPECTEDPOWER = 400; // 4 * 100 - refer API rule
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		assertEquals(EXPECTEDPOWER, locomotive.power());
	}
	
	/**
	 * Tests ToString Output 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveToString()
		throws TrainException {
		final Integer GROSSWEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final String TOSTRING = "Loco(4S)";
		
		Locomotive locomotive = new Locomotive(GROSSWEIGHT, CLASSIFICATION);
		assertEquals(TOSTRING, locomotive.toString());
	}

	/* - Passenger Car Tests ---------------------- */
	
	/**
	 * Tests that the number of passengers have boarded correctly
	 * with no returned passengers
	 * @throws TrainException
	 */
	@Test
	public void testPassengerBoard() 
			throws TrainException {
		final int GROSSWEIGHT = 90;
		final int NUMBEROFSEATS = 20;
		final Integer PASSENGERS = 5;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		int noRoom = passengerCar.board(PASSENGERS);
		
		assertEquals(PASSENGERS, passengerCar.numberOnBoard());
	}
	
	@Test
	public void testPassengerBoardReturnUnboarded() 
			throws TrainException {
		final int GROSSWEIGHT = 90;
		final int NUMBEROFSEATS = 20;
		final int PASSENGERS = 25;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		int noRoom = passengerCar.board(PASSENGERS);		
		assertEquals(PASSENGERS - NUMBEROFSEATS, noRoom);
	}
	
	/**
	 * Tests that the number of passengers have alighted correctly
	 * with no exceptions
	 * @throws TrainException
	 */
	@Test
	public void testPassengerAlight()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final int NUMBEROFSEATS = 20;
		final Integer PASSENGERSBOARD = 10;
		final Integer PASSENGERSALIGHT = 5;
		final Integer PASSENGERSBALANCE = PASSENGERSBOARD - PASSENGERSALIGHT;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		int noRoom = passengerCar.board(PASSENGERSBOARD);		
		assertEquals(PASSENGERSBOARD, passengerCar.numberOnBoard());
		
		passengerCar.alight(PASSENGERSALIGHT);
		assertEquals(PASSENGERSBALANCE, passengerCar.numberOnBoard());
	}
	
	/**
	 * Tests that Alight Throws Exception when too many passengers try to 
	 * alight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerAlightMoreThanBoard()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final int NUMBEROFSEATS = 20;
		final Integer PASSENGERSBOARD = 10;
		final int PASSENGERSALIGHT = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		int noRoom = passengerCar.board(PASSENGERSBOARD);		
		assertEquals(PASSENGERSBOARD, passengerCar.numberOnBoard());
		
		passengerCar.alight(PASSENGERSALIGHT);
	}
	
	/**
	 * Tests PassengerCar Constructor with valid constraints
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarConstructorValid()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final Integer NUMBEROFSEATS = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		assertEquals(NUMBEROFSEATS, passengerCar.numberOfSeats());
	}
	
	/**
	 * Tests PassengerCar with invalid seat configuration
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerCarConstructorNegativeSeats()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final int NUMBEROFSEATS = -20;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
	}
	
	/**
	 * Tests PassengerCar with zero seat configuration
	 * Acceptable according to specification
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarConstructorZeroSeats()
		throws TrainException {
		final int GROSSWEIGHT = 90;
		final Integer NUMBEROFSEATS = 0;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		assertEquals(NUMBEROFSEATS, passengerCar.numberOfSeats());
	}
	
	/**
	 * Tests PassengerCar constructor with negative weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerCarConstructorNegativeWeight()
		throws TrainException {
		final int GROSSWEIGHT = -10;
		final int NUMBEROFSEATS = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
	}
	
	/**
	 * Tests PassengerCar constructor with zero weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerCarConstructorZeroWeight()
		throws TrainException {
		final Integer GROSSWEIGHT = 0;
		final Integer NUMBEROFSEATS = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
	}
	
	/**
	 * Tests that numberOfSeats returns correctly
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOfSeats() 
			throws TrainException {
		final int GROSSWEIGHT = 90;
		final Integer NUMBEROFSEATS = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		assertEquals(NUMBEROFSEATS, passengerCar.numberOfSeats());
	}
	
	/**
	 * Tests that numberOnBoard returns correctly
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOnBoard() 
			throws TrainException {
		final int GROSSWEIGHT = 90;
		final int NUMBEROFSEATS = 20;
		final Integer BOARDING = 19;
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		int noRoom = passengerCar.board(BOARDING);
		
		assertEquals(BOARDING, passengerCar.numberOnBoard());
	}
	
	/**
	 * Tests ToString functions correctly
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarToString() 
			throws TrainException {
		final Integer GROSSWEIGHT = 90;
		final Integer NUMBEROFSEATS = 20;
		final Integer BOARDING = 19;
		final String TOSTRING = "Passenger(" + BOARDING + "/" + NUMBEROFSEATS + ")";
		
		PassengerCar passengerCar = new PassengerCar(GROSSWEIGHT, NUMBEROFSEATS);
		Integer noRoom = passengerCar.board(BOARDING);
		
		assertEquals(TOSTRING, passengerCar.toString());
	}
	
	
	
	
	
	
	
}
