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
		final Integer SEATS = 20;
		final Integer PASSENGERS = SEATS * 2;
		final Integer GROSS_WEIGHT = 90;
		final String POWER_CLASS = "4S";
		final String GOODS_TYPE = "G";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, POWER_CLASS);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		final PassengerCar PASSENGER01 = new PassengerCar(GROSS_WEIGHT, SEATS);
		final PassengerCar PASSENGER02 = new PassengerCar(GROSS_WEIGHT, SEATS);

		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER01);
		departingTrain.addCarriage(PASSENGER02);
		departingTrain.addCarriage(FREIGHT);
		Integer noSeats = departingTrain.board(PASSENGERS);
		assertEquals(departingTrain.numberOnBoard(), PASSENGERS);
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
	@Test 
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
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that when passengers board, the individual cars are filling up, 
	 * not just a total number on the whole train
	 */
	@Test
	public void testIndividualPassengerCarsAreBeingAddedTo() throws TrainException {
		final Integer seats = 20;
		final Integer passengers = 79;
		final Integer grossWeight = 90;
		final Integer zeroPassengers = 0;
		final String classification = "4S";
		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarTwo = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarThree = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarFour = new PassengerCar(grossWeight, seats);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		departingTrain.addCarriage(passengerCarTwo);
		departingTrain.addCarriage(passengerCarThree);
		departingTrain.addCarriage(passengerCarFour);
		
		departingTrain.board(passengers);
		
		assertTrue(passengerCarOne.numberOnBoard() > zeroPassengers);
		assertTrue(passengerCarTwo.numberOnBoard() > zeroPassengers);
		assertTrue(passengerCarThree.numberOnBoard() > zeroPassengers);
		assertTrue(passengerCarFour.numberOnBoard() > zeroPassengers);
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that an exception is thrown when two locomotives are attempted to be added to one train
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testTwoLocomotivesCannotBeAdded() throws TrainException {
		final Integer grossWeight = 90;
		final String classification = "4S";
		
		final Locomotive locomotiveOne = new Locomotive(grossWeight, classification);
		final Locomotive locomotiveTwo = new Locomotive(grossWeight, classification);
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotiveOne);
		departingTrain.addCarriage(locomotiveTwo);
	}

	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests if nextCarriage() method traverses all carriages of the train
	 * @throws TrainException
	 */
	@Test
	public void testNextCarriageFullTraversalOfTrain() throws TrainException {
		final Integer seats = 20;
		final Integer grossWeight = 90;
		final String classification = "4S";
		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarTwo = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarThree = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarFour = new PassengerCar(grossWeight, seats);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		departingTrain.addCarriage(passengerCarTwo);
		departingTrain.addCarriage(passengerCarThree);
		departingTrain.addCarriage(passengerCarFour);
		
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertEquals(passengerCarOne, departingTrain.nextCarriage());
		assertEquals(passengerCarTwo, departingTrain.nextCarriage());
		assertEquals(passengerCarThree, departingTrain.nextCarriage());
		assertEquals(passengerCarFour, departingTrain.nextCarriage());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests if firstCarriage() followed by subsequent nextCarriage() calls traverses all carriages on train
	 * @throws TrainException
	 */
	@Test
	public void testFirstThenNextCarriageFullTraversalOfTrain() throws TrainException {
		final Integer seats = 20;
		final Integer grossWeight = 90;
		final String classification = "4S";
		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarTwo = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarThree = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarFour = new PassengerCar(grossWeight, seats);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		departingTrain.addCarriage(passengerCarTwo);
		departingTrain.addCarriage(passengerCarThree);
		departingTrain.addCarriage(passengerCarFour);
		
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertEquals(passengerCarOne, departingTrain.nextCarriage());
		assertEquals(passengerCarTwo, departingTrain.nextCarriage());
		assertEquals(passengerCarThree, departingTrain.nextCarriage());
		assertEquals(passengerCarFour, departingTrain.nextCarriage());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that firstCarriage() method calls always returns the correct first carriage
	 * @throws TrainException
	 */
	@Test
	public void testFirstCarriageAlwaysReturnsFirstCarriage() throws TrainException {
		final Integer seats = 20;
		final Integer grossWeight = 90;
		final String classification = "4S";
		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarTwo = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarThree = new PassengerCar(grossWeight, seats);
		final PassengerCar passengerCarFour = new PassengerCar(grossWeight, seats);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		departingTrain.addCarriage(passengerCarTwo);
		departingTrain.addCarriage(passengerCarThree);
		departingTrain.addCarriage(passengerCarFour);
		
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertEquals(locomotive, departingTrain.firstCarriage());
	}

	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that an exception is thrown if a freight car is added before a locomotive
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testAddFreightCarToEmptyTrain() throws TrainException {
		final Integer grossWeight = 90;
		final String goodsType = "G";
		final FreightCar freightCar = new FreightCar(grossWeight, goodsType);		
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(freightCar);
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that an exception is thrown if a passenger car is added before a locomotive
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testAddPassengerCarToEmptyTrain() throws TrainException {
		final Integer seats = 20;
		final Integer grossWeight = 90;
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);
		
		DepartingTrain departingTrain = new DepartingTrain();
		departingTrain.addCarriage(passengerCarOne);
	}
	
	
	// -------------------------------- trainCanMove Tests -------------------------------------------------//
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that trainCanMove() returns true when appropriate
	 * @throws TrainException 
	 */
	@Test
	public void testTrainCanMoveTrueOneCarriage() throws TrainException {
		final Integer grossWeight = 90;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);

		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		assertTrue(departingTrain.trainCanMove());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that trainCanMove() returns false when appropriate
	 * @throws TrainException
	 */
	@Test
	public void testTrainCanMoveFalseOneCarriage() throws TrainException {
		final Integer grossWeight = 590;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		assertFalse(departingTrain.trainCanMove());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that trainCanMove() returns true when appropriate with multiple carriages
	 * @throws TrainException 
	 */
	@Test
	public void testTrainCanMoveTrueMultipleCarriages() throws TrainException {
		final Integer seats = 20;
		final Integer grossWeight = 90;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);

		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
	
		assertTrue(departingTrain.trainCanMove());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that trainCanMove() returns false when appropriate with multiple carriages
	 * @throws TrainException
	 */
	@Test
	public void testTrainCanMoveFalseMultipleCarriages() throws TrainException {
		final Integer seats = 20;
		final Integer grossWeight = 290;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);

		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		
		assertFalse(departingTrain.trainCanMove());
	}
	
	
	// -------------------------------- removeCarriage Tests -----------------------------------------------//	
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that an exception is thrown if a carriage is attempted to be removed 
	 * when no carriages exist
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriageNoRollingStockOnTrain() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.removeCarriage();
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that an exception is thrown if a carriage is attempted to be removed
	 * when there are passengers on the train
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriagePassengersOnTrain() throws TrainException {
		final Integer seats = 20;
		final Integer passengers = 19;
		final Integer grossWeight = 90;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);

		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		departingTrain.board(passengers);
		departingTrain.removeCarriage();		
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that a passenger car at the end will be successfully removed 
	 * @throws TrainException
	 */
	@Test
	public void testRemoveCarriageLastCarriagePassenger() throws TrainException {
		final Integer seats = 20;
		final Integer passengers = 19;
		final Integer grossWeight = 90;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final PassengerCar passengerCarOne = new PassengerCar(grossWeight, seats);

		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(passengerCarOne);
		departingTrain.removeCarriage();
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertNull(departingTrain.nextCarriage());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that a freight car at the end will be successfully removed
	 * @throws TrainException
	 */
	@Test
	public void testRemoveCarriageLastCarriageFreight() throws TrainException {
		final Integer grossWeight = 90;
		final String classification = "4S";	
		final String goodsType = "G";
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		final FreightCar freightCar = new FreightCar(grossWeight, goodsType);

		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		departingTrain.addCarriage(freightCar);
		departingTrain.removeCarriage();
		assertEquals(locomotive, departingTrain.firstCarriage());
		assertNull(departingTrain.nextCarriage());
	}
	
	/**
	 * @author Robert Dempsey, Student Number: N5400872
	 * Tests that a locomotive at the end will be successfully removed
	 * @throws TrainException
	 */
	@Test
	public void testRemoveCarriageLastCarriageLoco() throws TrainException {		
		final Integer grossWeight = 90;
		final String classification = "4S";		
		final Locomotive locomotive = new Locomotive(grossWeight, classification);
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(locomotive);
		departingTrain.removeCarriage();
		assertNull(departingTrain.firstCarriage());
	}
	
	
	// -------------------------------- toString Tests ----------------------------------------------------//
	
	@Test
	public void testToStringNull() throws TrainException {
		DepartingTrain departingTrain = new DepartingTrain();
		assertNull(departingTrain.toString());
	}
	
	@Test
	public void testToStringSingleCarriage() throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String CLASSIFICATION = "4S";
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		final String TO_STRING = "Loco(4S)";
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(LOCO);
		
		assertEquals(TO_STRING, departingTrain.toString());
	}
	
	@Test
	public void testToStringMultipleCarriageNoPassengers() throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String GOODS_TYPE = "G";
		final Integer SEATS = 20;
		final Integer PASSENGERS = 0;
		final String CLASSIFICATION = "4S";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		
		final String TO_STRING = "Loco("
							   + CLASSIFICATION
							   + ")-Passenger("
							   + PASSENGERS
							   + "/"
							   + SEATS
							   + ")-Freight("
							   + GOODS_TYPE
							   + ")";
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		
		assertEquals(TO_STRING, departingTrain.toString());
	}
	
	@Test
	public void testToStringMultipleCarriagePassengers() throws TrainException {
		final Integer GROSS_WEIGHT = 90;
		final String GOODS_TYPE = "G";
		final Integer SEATS = 20;
		final Integer PASSENGERS = 10;
		final String CLASSIFICATION = "4S";
		
		final Locomotive LOCO = new Locomotive(GROSS_WEIGHT, CLASSIFICATION);
		final FreightCar FREIGHT = new FreightCar(GROSS_WEIGHT, GOODS_TYPE);
		final PassengerCar PASSENGER = new PassengerCar(GROSS_WEIGHT, SEATS);
		
		final String TO_STRING = "Loco("
							   + CLASSIFICATION
							   + ")-Passenger("
							   + PASSENGERS
							   + "/"
							   + SEATS
							   + ")-Freight("
							   + GOODS_TYPE
							   + ")";
		
		DepartingTrain departingTrain = new DepartingTrain();
		
		departingTrain.addCarriage(LOCO);
		departingTrain.addCarriage(PASSENGER);
		departingTrain.addCarriage(FREIGHT);
		departingTrain.board(PASSENGERS);
		
		assertEquals(TO_STRING, departingTrain.toString());
	}
}
