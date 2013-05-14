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
		final String GOODS_TYPE = "G";
		final Integer GROSS_WEIGHT = 9;
		
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		assertEquals(GOODS_TYPE, freightCar.goodsType());
		assertEquals(GROSS_WEIGHT, freightCar.getGrossWeight(), 0);
	}
	
	/**
	 * Tests the FreightCar Constructor with invalid constraints
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorInvalid() 
			throws TrainException {
		final String GOODS_TYPE = "X";
		final Integer GROSS_WEIGHT = -9;
		
		@SuppressWarnings("unused")
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with invalid Goods Type
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorInvalidGOODS_TYPE() 
			throws TrainException {
		final String GOODS_TYPE = "X";
		final Integer GROSS_WEIGHT = 9;
		
		@SuppressWarnings("unused")
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with Zero Weight
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorZeroWeight() 
			throws TrainException {
		final String GOODS_TYPE = "D";
		final Integer GROSS_WEIGHT = 0;
		
		@SuppressWarnings("unused")
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with positive weight
	 */
	@Test
	public void testFreightCarConstructorPostiveWeight() 
			throws TrainException {
		final String GOODS_TYPE = "D";
		final Integer GROSS_WEIGHT = 9;
		
		@SuppressWarnings("unused")
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor with negative weight
	 */
	@Test (expected = TrainException.class)
	public void testFreightCarConstructorNegativeWeight() 
			throws TrainException {
		final String GOODS_TYPE = "D";
		final Integer GROSS_WEIGHT = -9;
		
		@SuppressWarnings("unused")
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
	}
	
	/**
	 * Tests the FreightCar Constructor ToString()
	 */
	@Test
	public void testFreightCarToString() 
			throws TrainException {
		final String GOODS_TYPE = "D";
		final Integer GROSS_WEIGHT = 9;
		final String TO_STRING = "Freight(D)"; // Rob - See API
		FreightCar freightCar = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		String rollingStock = freightCar.toString();
		assertEquals(TO_STRING, rollingStock);
	}

	/* - Locomotive Tests ------------------------- */
	
	/**
	 *  Tests the Locomotive Constructor with valid inputs
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveConstructorValid()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		
		assertEquals(GROSS_WEIGHT,locomotive.getGrossWeight());
		//assertEquals(CLASSIFICATION, locomotive.getClassification());
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid inputs
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalid()
		throws TrainException {
		final Integer GROSS_WEIGHT = -90;
		final String CLASSIFICATION = "11T";
		
		@SuppressWarnings("unused")
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid Engine Type
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalidEngineType()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "9T";
		
		@SuppressWarnings("unused")
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with invalid power rating
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorInvalidPowerRating()
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "11S";
		
		@SuppressWarnings("unused")
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
	}
	
	public void testLocomotiveNegativePowerRating() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "-1S";
		
		@SuppressWarnings("unused")
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
	}
	
	
	/**
	 *  Tests the Locomotive Constructor with positive weight
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveConstructorPositiveWeight()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "4S";
		
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		
		assertEquals(GROSS_WEIGHT,locomotive.getGrossWeight());
		//assertEquals(CLASSIFICATION, locomotive.getClassification());
	}
	
	/**
	 *  Tests the Locomotive Constructor with zero weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorZeroWeight()
		throws TrainException {
		final Integer GROSS_WEIGHT = 0;
		final String CLASSIFICATION = "4S";
		
		@SuppressWarnings("unused")
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the Locomotive Constructor with negative weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocomotiveConstructorNegativeWeight()
		throws TrainException {
		final Integer GROSS_WEIGHT = -90;
		final String CLASSIFICATION = "4S";
		
		@SuppressWarnings("unused")
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
	}
	
	/**
	 *  Tests the locomotive for expected power output
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveCheckPowerOutput()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final Integer EXPECTED_POWER = 400; // 4 * 100 - refer API rule
		
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		assertEquals(EXPECTED_POWER, locomotive.power());
	}
	
	/**
	 * Tests ToString Output 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotiveToString()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final String TO_STRING = "Loco(4S)";
		
		Locomotive locomotive = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		assertEquals(TO_STRING, locomotive.toString());
	}

	/* - Passenger Car Tests ---------------------- */
	
	/**
	 * Tests that the number of passengers have boarded correctly
	 * with no returned passengers
	 * @throws TrainException
	 */
	@Test
	public void testPassengerBoardPositive() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer PASSENGERS = 5;
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
		int noRoom = passengerCar.board(PASSENGERS);
		
		assertEquals(PASSENGERS, passengerCar.numberOnBoard());
	}
	
	/**
	 * Tests that a negative number of passengers boarding throws exception
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerBoardNegative() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer PASSENGERS = -5;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
		int noRoom = passengerCar.board(PASSENGERS);
	}
	
	@Test
	public void testPassengerBoardReturnUnboarded() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final int PASSENGERS = 25;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		int noRoom = passengerCar.board(PASSENGERS);		
		assertEquals(PASSENGERS - NUMBER_OF_SEATS, noRoom);
	}
	
	/**
	 * Tests that the number of passengers have alighted correctly
	 * with no exceptions
	 * @throws TrainException
	 */
	@Test
	public void testPassengerAlightPositive()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer PASSENGERS_BOARD = 10;
		final Integer PASSENGERS_ALIGHT = 5;
		final Integer PASSENGERSBALANCE = PASSENGERS_BOARD - PASSENGERS_ALIGHT;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
		int noRoom = passengerCar.board(PASSENGERS_BOARD);		
		assertEquals(PASSENGERS_BOARD, passengerCar.numberOnBoard());
		
		passengerCar.alight(PASSENGERS_ALIGHT);
		assertEquals(PASSENGERSBALANCE, passengerCar.numberOnBoard());
	}
	
	/**
	 * Tests that exception is thrown on negative passengers alight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerAlightNegative()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer PASSENGERS_BOARD = 10;
		final Integer PASSENGERS_ALIGHT = -5;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
		int noRoom = passengerCar.board(PASSENGERS_BOARD);		
		
		passengerCar.alight(PASSENGERS_ALIGHT);
	}
	
	/**
	 * Tests that Alight Throws Exception when too many passengers try to 
	 * alight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerAlightMoreThanBoard()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer PASSENGERS_BOARD = 10;
		final int PASSENGERS_ALIGHT = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
		int noRoom = passengerCar.board(PASSENGERS_BOARD);		
		assertEquals(PASSENGERS_BOARD, passengerCar.numberOnBoard());
		
		passengerCar.alight(PASSENGERS_ALIGHT);
	}
	
	/**
	 * Tests PassengerCar Constructor with valid constraints
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarConstructorValid()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		assertEquals(NUMBER_OF_SEATS, passengerCar.numberOfSeats());
	}
	
	/**
	 * Tests PassengerCar with invalid seat configuration
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerCarConstructorNegativeSeats()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = -20;
		
		@SuppressWarnings("unused")
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
	}
	
	/**
	 * Tests PassengerCar with zero seat configuration
	 * Acceptable according to specification
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarConstructorZeroSeats()
		throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 0;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		assertEquals(NUMBER_OF_SEATS, passengerCar.numberOfSeats());
	}
	
	/**
	 * Tests PassengerCar constructor with negative weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerCarConstructorNegativeWeight()
		throws TrainException {
		final Integer GROSS_WEIGHT = -10;
		final Integer NUMBER_OF_SEATS = 20;
		
		@SuppressWarnings("unused")
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
	}
	
	/**
	 * Tests PassengerCar constructor with zero weight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerCarConstructorZeroWeight()
		throws TrainException {
		final Integer GROSS_WEIGHT = 0;
		final Integer NUMBER_OF_SEATS = 20;
		
		@SuppressWarnings("unused")
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
	}
	
	/**
	 * Tests that numberOfSeats returns correctly
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOfSeats() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		assertEquals(NUMBER_OF_SEATS, passengerCar.numberOfSeats());
	}
	
	/**
	 * Tests that numberOnBoard returns correctly
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarNumberOnBoard() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer BOARDING = 19;
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
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
		final Integer GROSS_WEIGHT = 90;
		final Integer NUMBER_OF_SEATS = 20;
		final Integer BOARDING = 19;
		final String TO_STRING = "Passenger(" + BOARDING + "/" + NUMBER_OF_SEATS + ")";
		
		PassengerCar passengerCar = new PassengerCar(GROSS_WEIGHT, NUMBER_OF_SEATS);
		@SuppressWarnings("unused")
		Integer noRoom = passengerCar.board(BOARDING);
		
		assertEquals(TO_STRING, passengerCar.toString());
	}
	
	/**
	 * @author Robert Dempsey Student Number: N5400872
	 * Tests that when more passengers than there are seats try to board, the carriage will
	 * be at full capacity; no more, no less
	 * @throws TrainException
	 */
	@Test
	public void testExcessPassengersNotAllowedToBoard() throws TrainException {
		final Integer grossWeight = 90;
		final Integer numberOfSeats = 20;
		final Integer boarding = 40;

		PassengerCar passengerCar = new PassengerCar(grossWeight, numberOfSeats);
				
		passengerCar.board(boarding);
		
		assertEquals(numberOfSeats, passengerCar.numberOnBoard());
	}
	
	/**
	 * @author Robert Dempsey Student Number: N5400872
	 * Tests board function will return zero if less passengers than there are seats
	 * try to board
	 * @throws TrainException
	 */
	@Test
	public void testBoardReturnsZeroIfCarriageNotFull() throws TrainException {
		final Integer grossWeight = 90;
		final Integer numberOfSeats = 20;
		final Integer boarding = 15;
		final Integer remainder = 0;
		
		PassengerCar passengerCar = new PassengerCar(grossWeight, numberOfSeats);
			
		assertEquals(remainder, passengerCar.board(boarding));
	}
	
}
