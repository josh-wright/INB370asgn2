/**
 * 
 */
package asgn2Test;

import static org.junit.Assert.*;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;
import asgn2Train.DepartingTrain;

/**
 * @author Joshua
 *
 */
public class TrainTests {
	
	/**
	 * Test DepartingTrain Constructor
	 * @throws TrainException
	 */
	@Test
	public void testDepartingTrain() 
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		
		assertNull(departingTrain.firstCarriage());
	}
	
	/**
	 * Tests Train Successfully Added
	 * @throws TrainException
	 */
	@Test
	public void testAddCarriage() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		assertEquals(departingTrain.firstCarriage(), LOCO);
	}
	
	/**
	 * Tests First Carriage is a Locomotive
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocoFirstFalse() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		final FreightCar FREIGHT = new FreightCar(90, "G");
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(FREIGHT);
		departingTrain.addCarriage(LOCO);
		assertEquals(departingTrain.firstCarriage(), LOCO);
	}
	
	/**
	 * Tests First Carriage is a Locomotive
	 * @throws TrainException
	 */
	@Test
	public void testLocoFirstTrue() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		final FreightCar FREIGHT = new FreightCar(90, "G");
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(FREIGHT);
		assertEquals(departingTrain.firstCarriage(), LOCO);
	}
	
	/**
	 * Tests Add FreightCar to Departing Train with Loco Only
	 * @throws TrainException
	 */
	@Test
	public void testAddFreight() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		final FreightCar FREIGHT = new FreightCar(90, "G");
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(FREIGHT);
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), FREIGHT);
	}
	
	/**
	 * Tests Add PassengerCar to Departing Train with Loco Only
	 * @throws TrainException
	 */
	@Test
	public void testAddPassenger() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		final PassengerCar PASSENGER = new PassengerCar(90, 20);
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), PASSENGER);
	}
	
	/**
	 * Tests Add PassengerCar to Departing Train with Loco, Freight
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testAddPassengerToLocoFreight() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		final FreightCar FREIGHT = new FreightCar(90, "G");
		final PassengerCar PASSENGER = new PassengerCar(90, 20);
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(FREIGHT);
		departingTrain.addCarriage(PASSENGER);
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), FREIGHT);
		assertEquals(departingTrain.nextCarriage(), PASSENGER);
	}
	
	/**
	 * Tests Add FreightCar to Departing Train with Loco, Passenger
	 * @throws TrainException
	 */
	@Test
	public void testAddFreightToLocoPassenger() 
			throws TrainException {
		final Locomotive LOCO = new Locomotive(90, "4S");
		final FreightCar FREIGHT = new FreightCar(90, "G");
		final PassengerCar PASSENGER = new PassengerCar(90, 20);
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), PASSENGER);
		assertEquals(departingTrain.nextCarriage(), FREIGHT);
	}
	
	/**
	 * Tests Add Carriage while Passengers Aboard
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testAddCarAfterBoarding() 
			throws TrainException {
		final Integer PASSENGERS = 20;
		final Locomotive LOCO = new Locomotive(90, "4S");
		final FreightCar FREIGHT = new FreightCar(90, "G");
		final PassengerCar PASSENGER = new PassengerCar(90, PASSENGERS);
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.board(PASSENGERS);
		departingTrain.addCarriage(FREIGHT);
	}
	
	/**
	 * Test Boarding Passengers to a Valid Train
	 * @throws TrainException
	 */
	@Test
	public void testBoardPassengersValidTrainPositive() 
			throws TrainException {
		final Integer PASSENGERS = 20;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, PASSENGERS);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		Integer noSeats = departingTrain.board(PASSENGERS);
		assertEquals(departingTrain.numberOnBoard(), PASSENGERS);
	}	
	
	/**
	 * Test Boarding Passengers to a Valid Train with negative
	 * boarding passengers
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testBoardPassengersValidTrainNegative() 
			throws TrainException {
		final Integer PASSENGERS = -20;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, PASSENGERS);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		@SuppressWarnings("unused")
		Integer noSeats = departingTrain.board(PASSENGERS);
	}
	
	/**
	 * Test Boarding Passengers to a Valid Combo Train with
	 * a single PassengerCar carriage and single FreightCar carriage
	 * @throws TrainException
	 */
	@Test
	public void testBoardPassengersValidComboTrainFreight() 
			throws TrainException {
		final Integer PASSENGERS = 20;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, PASSENGERS);

		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		Integer noSeats = departingTrain.board(PASSENGERS);
		
		assertEquals(departingTrain.numberOnBoard(), PASSENGERS);
	}
	
	/**
	 * Test Boarding Passengers to a Valid Combo Train with multiple
	 * PassengerCar carriages and single FreightCar carriages
	 * @throws TrainException
	 */
	@Test
	public void testBoardPassengersValidComboTrainPassengerMultiple() 
			throws TrainException {
		final Integer PASSENGERS_CAR = 20;
		final Integer PASSENGERS_TOTAL = PASSENGERS_CAR * 2;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		final PassengerCar PASSENGER01 = new PassengerCar(GROSS_WEIGHT, PASSENGERS_CAR);
		final PassengerCar PASSENGER02 = new PassengerCar(GROSS_WEIGHT, PASSENGERS_CAR);

		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER01);
		departingTrain.addCarriage(PASSENGER02);
		departingTrain.addCarriage(FREIGHT);
		Integer noSeats = departingTrain.board(PASSENGERS_TOTAL);
		assertEquals(departingTrain.numberOnBoard(), PASSENGERS_TOTAL);
	}
	
	/**
	 * Test Boarding Passengers to a Invalid Train
	 * @throws TrainException
	 */
	@Test 
	public void testBoardPassengersInvalidTrain() 
			throws TrainException {
		final Integer PASSENGERS = 20;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(FREIGHT);
		Integer noSeats = departingTrain.board(PASSENGERS);
		
		assertEquals(noSeats, PASSENGERS);
	}
	
	/**
	 * Test Boarding To Many Passengers to a Valid Train
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testBoardTooManyPassengersValidTrain() 
			throws TrainException {
		final Integer SEATS = 20;
		final Integer PASSENGERS = 40;
		final Integer EXPECTED_OVER = PASSENGERS - SEATS;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		Integer noSeats = departingTrain.board(PASSENGERS);
		assertEquals(noSeats, EXPECTED_OVER);
	}
	
	/**
	 * Test Get First Carriage on a Train with No Carriages
	 * @throws TrainException
	 */
	@Test
	public void testGetFirstCarriage_NoCarriages() 
			throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		
		assertNull(departingTrain.firstCarriage());
	}
	
	/**
	 * Test Get First Carriage on a Train with a Single Carriage
	 * @throws TrainException
	 */
	@Test
	public void testGetFirstCarriage_SingleCarriage() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		
		assertEquals(departingTrain.firstCarriage(), LOCO);
	}
	
	/**
	 * Test Get First Carriage on a Train with Multiple Carriages
	 * @throws TrainException
	 */
	@Test
	public void testGetFirstCarriage_MultipleCarriage() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final Integer SEATS = 20;
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.firstCarriage(), LOCO);
	}
	
	/**
	 * Test Get Second Carriage on a Train with Multiple Carriages
	 * @throws TrainException
	 */
	@Test
	public void testGetSecondCarriage() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final Integer SEATS = 20;
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), PASSENGER);
	}
	
	/**
	 * Test Get Third or higher Carriage on a Train with Multiple Carriages
	 * @throws TrainException
	 */
	@Test
	public void testGetAdditionalCarriages() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final Integer SEATS = 20;
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), PASSENGER);
		assertEquals(departingTrain.nextCarriage(), FREIGHT);
	}
	
	/**
	 * Test Get Third or higher Carriage on a Train with Multiple Carriages
	 * but doesn't exist and returns null
	 * @throws TrainException
	 */
	@Test
	public void testGetNextCarriage_Null() 
			throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final Integer SEATS = 20;
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.firstCarriage(), LOCO);
		assertEquals(departingTrain.nextCarriage(), PASSENGER);
		assertEquals(departingTrain.nextCarriage(), FREIGHT);
		assertNull(departingTrain.nextCarriage());
	}
	
	/**
	 * Tests numberOfSeats with no Cars
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfSeats_NoCars() 
			throws TrainException {
		final Integer EXPECTED_SEATS = 0;
		
		DepartingTrain departingTrain = new DepartingTrain();
		assertEquals(departingTrain.numberOfSeats(), EXPECTED_SEATS);
	}
	
	/**
	 * Tests numberOfSeats with no PassengerCars
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfSeats_NoPassengerCars() 
			throws TrainException {
		final Integer EXPECTED_SEATS = 0;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);

		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(FREIGHT);
				
		assertEquals(departingTrain.numberOfSeats(), EXPECTED_SEATS);
	}
	
	/**
	 * Tests numberOfSeats with a single PassengerCar
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfSeats_SinglePassengerCar() 
			throws TrainException {
		final Integer SEATS = 20;
		final Integer EXPECTED_SEATS = SEATS;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.numberOfSeats(), EXPECTED_SEATS);
	}
	
	/**
	 * Tests numberOfSeats with two PassengerCars
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfSeats_DualPassengerCars() 
			throws TrainException {
		final Integer SEATS = 20;
		final Integer EXPECTED_SEATS = SEATS * 2;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER01 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final PassengerCar PASSENGER02 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER01);
		departingTrain.addCarriage(PASSENGER02);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.numberOfSeats(), EXPECTED_SEATS);
	}
	
	/**
	 * Tests numberOfSeats with more than two PassengerCars
	 * Verifies code is dynamic to handle multiples
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfSeats_MultiplePassengerCar() 
			throws TrainException {
		final Integer SEATS = 20;
		final Integer EXPECTED_SEATS = SEATS * 4;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final PassengerCar PASSENGER01 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final PassengerCar PASSENGER02 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final PassengerCar PASSENGER03 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final PassengerCar PASSENGER04 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER01);
		departingTrain.addCarriage(PASSENGER02);
		departingTrain.addCarriage(PASSENGER03);
		departingTrain.addCarriage(PASSENGER04);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(departingTrain.numberOfSeats(), EXPECTED_SEATS);
	}
}
