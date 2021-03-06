package asgn2GUI;

/**
 * DepartingTrainGUI.java 
 * Implementation of a Graphical User Interface for the DepartingTrain.java 
 * class. Includes functionality for beginning an empty train, adding carriages
 * removing the last carriage, shunting the train at any desired carriage link 
 * and inserting a different valid carriage, boarding passengers and alighting
 * passengers.
 * @author Joshua Wright (n6366066)
 * @author Robert Dempsey (n5400872)
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import asgn2Exceptions.*;
import asgn2RollingStock.*;
import asgn2Train.*;

public class DepartingTrainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -5400452591692641009L;
	
	private final int DEFAULT_PADDING_POS = 10;
	private final int DEFAULT_PADDING_NEG = -10;
	
	// Declare Standard train
	private DepartingTrain departingTrain;
	// Declare Train on spur track
	private DepartingTrain spurLine;
	

	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	// Declare layouts
	private GridLayout baseLayout = new GridLayout(2,0);
	private GridLayout trainInfoLayout = new GridLayout(1,20);
	
	private SpringLayout trainLayout = new SpringLayout();
	private SpringLayout driverLayout = new SpringLayout();
	private SpringLayout conductorLayout = new SpringLayout();
	private SpringLayout shuntTrainLayout = new SpringLayout();
	private SpringLayout beginTrainLayout = new SpringLayout();
	private SpringLayout addCarriageLayout = new SpringLayout();
	private SpringLayout boardPassengersLayout = new SpringLayout();
	private SpringLayout alightPassengersLayout = new SpringLayout();
	
	// Declare global fields
	private JPanel train;
	private JPanel users;
	private JPanel driver;
	private JPanel shuntInfo;
	private JPanel trainInfo;
	private JPanel conductor;
	private JPanel shuntTrain;
	private JPanel beginTrain;
	private JPanel addCarriage;
	private JPanel trainControl;
	private JPanel boardPassengers;
	private JPanel alightPassengers;
	
	private JButton shuntButton;
	private JButton newTrainButton;
	private JButton boardNowButton;
	private JButton alightNowButton;
	private JButton joinTrainButton;
	private JButton addFreightButton;
	private JButton resetTrainButton;
	private JButton shuntTrainButton;
	private JButton addCarriageButton;
	private JButton addPassengerButton;
	private JButton addLocomotiveButton;
	private JButton removeCarriageButton;
	private JButton boardPassengersButton;
	private JButton alightPassengersButton;
	
	private JTextField grossWeightInput;
	private JTextField numberOfSeatsInput;
	private JTextField boardPassengersInput;
	private JTextField alightPassengersInput;
	private JTextField grossWeightFreightInput;
	private JTextField grossWeightPassengerInput;
	
	private JComboBox<String> goodsTypeInput;
	private JComboBox<String> engineTypeInput;
	private JComboBox<Integer> powerClassInput;
	private JComboBox<Integer> shuntIndexInput;
		
	private JRadioButton freightCarSelect;
	private JRadioButton passengerCarSelect;
	
	private JLabel goodsTypeLabel;
	private JLabel trainMoveLabel;
	private JLabel trainPowerLabel;
	private JLabel trainWeightLabel;
	private JLabel trainCapacityLabel;
	private JLabel numberOfSeatsLabel;
	private JLabel trainLeftBehindLabel;
	private JLabel boardPassengersLabel;
	private JLabel shuntIndexSelectLabel;
	private JLabel alightPassengersLabel;
	private JLabel grossWeightFreightLabel;
	private JLabel grossWeightPassengerLabel;
	
	private Integer shuntNumber;
	private Integer trainPower = 0;
	private Integer leftBehind = 0;
	private Integer trainWeight = 0;
	private boolean isShunted = false;
	private Integer lastCarriageGrossWeight = 0;
	
	
	/**
	 * Constructs a DepartingTrainGUI
	 * @param name - Name of the JFrame
	 */
	public DepartingTrainGUI(String name) {
		super(name);
		setResizable(true);
	}
	
	public void addComponentsToPanel(final Container pane){
		/* Form Structure
		 * -> trainControl (2 rows 1 column)
		 * 		-> Train
		 * 			-> Train Information (up to 3x20 carriages)
		 *  	-> User
		 *  		-> Driver
		 *  			-> Driver Controls
		 *  		-> Conductor
		 *  			-> Conductor Controls
		 */
		
		/* - Main Panel ----------------------------------------------------------------------------------------------------------------------------------------------- */
		
		trainControl = new JPanel();
		trainControl.setLayout(baseLayout);
		trainControl.setSize(screenSize);
		getContentPane().add(trainControl);
		
		/* - train (Part of trainControl) ----------------------------------------------------------------------------------------------------------------------------- */
		
		train = new JPanel();
		train.setLayout(trainLayout);
		trainControl.add(train);
				
		JLabel trainTitle = new JLabel("Train Information");
		trainTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		trainLayout.putConstraint(SpringLayout.NORTH, trainTitle, 20, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trainTitle, 0, SpringLayout.HORIZONTAL_CENTER, train);
		train.add(trainTitle);
		
		trainWeightLabel = new JLabel("Train Weight: No Train Started");
		trainWeightLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		trainLayout.putConstraint(SpringLayout.NORTH, trainWeightLabel, 10, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainWeightLabel, 10, SpringLayout.WEST, train);
		train.add(trainWeightLabel);
		
		trainPowerLabel = new JLabel("Pulling Power: No Train Started");
		trainPowerLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		trainLayout.putConstraint(SpringLayout.NORTH, trainPowerLabel, 5, SpringLayout.SOUTH, trainWeightLabel);
		trainLayout.putConstraint(SpringLayout.WEST, trainPowerLabel, 10, SpringLayout.WEST, train);
		train.add(trainPowerLabel);
		
		trainMoveLabel = new JLabel("Enough Power?: No Train Started");
		trainMoveLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		trainLayout.putConstraint(SpringLayout.NORTH, trainMoveLabel, 5, SpringLayout.SOUTH, trainPowerLabel);
		trainLayout.putConstraint(SpringLayout.WEST, trainMoveLabel, 10, SpringLayout.WEST, train);
		train.add(trainMoveLabel);
		
		trainCapacityLabel = new JLabel("Train Capacity: No Train Started");
		trainCapacityLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		trainLayout.putConstraint(SpringLayout.NORTH, trainCapacityLabel, 10, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.EAST, trainCapacityLabel, -10, SpringLayout.EAST, train);
		train.add(trainCapacityLabel);
		
		trainLeftBehindLabel = new JLabel("Passengers Left Behind: No Train Started");
		trainLeftBehindLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		trainLayout.putConstraint(SpringLayout.NORTH, trainLeftBehindLabel, 5, SpringLayout.SOUTH, trainCapacityLabel);
		trainLayout.putConstraint(SpringLayout.EAST, trainLeftBehindLabel, -10, SpringLayout.EAST, train);
		train.add(trainLeftBehindLabel);
		
		
		/* - trainInfo (Part of train) -------------------------------------------------------------------------------------------------------------------------------- */
		
		trainInfo = new JPanel();
		trainInfo.setBackground(Color.WHITE);
		trainInfo.setLayout(trainInfoLayout);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		trainInfo.setBorder(loweredbevel);
		trainLayout.putConstraint(SpringLayout.NORTH, trainInfo, 20, SpringLayout.SOUTH, trainTitle);
		trainLayout.putConstraint(SpringLayout.EAST, trainInfo, DEFAULT_PADDING_NEG, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, trainInfo, -250, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainInfo, DEFAULT_PADDING_POS, SpringLayout.WEST, train);
		train.add(trainInfo);
		
		
		/* - shuntInfo (Part of train) ----------------------------------------------------------------------------------------------------------------------------- */
		
		shuntInfo = new JPanel();
		shuntInfo.setLayout(trainInfoLayout);
		shuntInfo.setBackground(Color.WHITE);
		shuntInfo.setBorder(loweredbevel);
		trainLayout.putConstraint(SpringLayout.NORTH, shuntInfo, 10, SpringLayout.SOUTH, trainInfo);
		trainLayout.putConstraint(SpringLayout.EAST, shuntInfo, DEFAULT_PADDING_NEG, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, shuntInfo, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, shuntInfo, DEFAULT_PADDING_POS, SpringLayout.WEST, train);	
		shuntInfo.setVisible(false);
		train.add(shuntInfo);

		
		/* - Users (Part of trainControl) ----------------------------------------------------------------------------------------------------------------------------- */
		
		users = new JPanel();
		users.setLayout(new GridLayout(0,2));
		trainControl.add(users);
		
		/* - Driver (Part of user) ------------------------------------------------------------------------------------------------------------------------------------ */
		
		driver = new JPanel();
		driver.setLayout(driverLayout);
		users.add(driver);
		
		JLabel driverTitle = new JLabel("Driver Controls");
		driverTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		driverLayout.putConstraint(SpringLayout.NORTH, driverTitle, 20, SpringLayout.NORTH, driver);
		driverLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, driverTitle, 0, SpringLayout.HORIZONTAL_CENTER, driver);
		driver.add(driverTitle);
		
		Dimension driverButtonSize = new Dimension(200,30);
		
		newTrainButton = new JButton("Begin Train");
		newTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, newTrainButton, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, newTrainButton, 10, SpringLayout.WEST, driver);
		newTrainButton.addActionListener(this);
		newTrainButton.setEnabled(true);
		driver.add(newTrainButton);
				
		resetTrainButton = new JButton("Cancel Train");
		resetTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, resetTrainButton, 0, SpringLayout.SOUTH, newTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, resetTrainButton, 10, SpringLayout.WEST, driver);
		resetTrainButton.addActionListener(this);
		resetTrainButton.setEnabled(false);
		driver.add(resetTrainButton);
				
		shuntTrainButton = new JButton("Shunt Train");
		shuntTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, shuntTrainButton, 0, SpringLayout.SOUTH, resetTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, shuntTrainButton, 10, SpringLayout.WEST, driver);
		shuntTrainButton.addActionListener(this);
		shuntTrainButton.setEnabled(false);
		driver.add(shuntTrainButton);
		

		
		joinTrainButton = new JButton ("Connect Shunted Train");
		joinTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, joinTrainButton, 0, SpringLayout.SOUTH, shuntTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, joinTrainButton, 10, SpringLayout.WEST, driver);
		joinTrainButton.addActionListener(this);
		joinTrainButton.setEnabled(false);
		driver.add(joinTrainButton);
		

		
		addCarriageButton = new JButton ("Add New Carriage");
		addCarriageButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, addCarriageButton, 0, SpringLayout.SOUTH, joinTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, addCarriageButton, 10, SpringLayout.WEST, driver);
		addCarriageButton.addActionListener(this);
		addCarriageButton.setEnabled(false);
		driver.add(addCarriageButton);

		
		removeCarriageButton = new JButton ("Remove Last Carriage");
		removeCarriageButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, removeCarriageButton, 0, SpringLayout.SOUTH, addCarriageButton);
		driverLayout.putConstraint(SpringLayout.WEST, removeCarriageButton, 10, SpringLayout.WEST, driver);
		removeCarriageButton.addActionListener(this);
		removeCarriageButton.setEnabled(false);
		driver.add(removeCarriageButton);
		
		beginTrain = new JPanel();
		beginTrain.setLayout(beginTrainLayout);
		beginTrain.setVisible(false);
		driverLayout.putConstraint(SpringLayout.NORTH, beginTrain, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, beginTrain, DEFAULT_PADDING_POS, SpringLayout.EAST, newTrainButton);
		driverLayout.putConstraint(SpringLayout.EAST, beginTrain, DEFAULT_PADDING_NEG, SpringLayout.EAST, driver);
		driverLayout.putConstraint(SpringLayout.SOUTH, beginTrain, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, driver);
		driver.add(beginTrain);
		
		JLabel beginTrainTitle = new JLabel("Create a Locomotve");
		beginTrainTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		beginTrainLayout.putConstraint(SpringLayout.NORTH, beginTrainTitle, 14, SpringLayout.NORTH, beginTrain);
		beginTrainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, beginTrainTitle, 0, SpringLayout.HORIZONTAL_CENTER, beginTrain);
		beginTrain.add(beginTrainTitle);
		
		JLabel grossWeightLabel = new JLabel("Gross Weight (t)");
		grossWeightLabel.setPreferredSize(driverButtonSize);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, grossWeightLabel, 20, SpringLayout.SOUTH, beginTrainTitle);
		beginTrainLayout.putConstraint(SpringLayout.WEST, grossWeightLabel, 10, SpringLayout.WEST, beginTrain);
		beginTrain.add(grossWeightLabel);
		
		grossWeightInput = new JTextField(10);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, grossWeightInput, 20, SpringLayout.SOUTH, beginTrainTitle);
		beginTrainLayout.putConstraint(SpringLayout.WEST, grossWeightInput, 0, SpringLayout.EAST, grossWeightLabel);
		beginTrainLayout.putConstraint(SpringLayout.EAST, grossWeightInput, -10, SpringLayout.EAST, beginTrain);
		beginTrainLayout.putConstraint(SpringLayout.SOUTH, grossWeightInput, 0, SpringLayout.SOUTH, grossWeightLabel);
		beginTrain.add(grossWeightInput);
		
		JLabel powerClassLabel = new JLabel("Power Class");
		powerClassLabel.setPreferredSize(driverButtonSize);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, powerClassLabel, 5, SpringLayout.SOUTH, grossWeightLabel);
		beginTrainLayout.putConstraint(SpringLayout.WEST, powerClassLabel, 10, SpringLayout.WEST, beginTrain);
		beginTrain.add(powerClassLabel);
		
		Integer[] powerClasses = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		
		powerClassInput = new JComboBox<Integer>(powerClasses);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, powerClassInput, 5, SpringLayout.SOUTH, grossWeightInput);
		beginTrainLayout.putConstraint(SpringLayout.WEST, powerClassInput, 0, SpringLayout.EAST, powerClassLabel);
		beginTrainLayout.putConstraint(SpringLayout.EAST, powerClassInput, -10, SpringLayout.EAST, beginTrain);
		beginTrainLayout.putConstraint(SpringLayout.SOUTH, powerClassInput, 0, SpringLayout.SOUTH, powerClassLabel);
		beginTrain.add(powerClassInput);
		
	
		JLabel engineTypeLabel = new JLabel("Engine Type");
		engineTypeLabel.setPreferredSize(driverButtonSize);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, engineTypeLabel, 5, SpringLayout.SOUTH, powerClassLabel);
		beginTrainLayout.putConstraint(SpringLayout.WEST, engineTypeLabel, 10, SpringLayout.WEST, beginTrain);
		beginTrain.add(engineTypeLabel);
		
		String[] engineTypes = { "Steam", "Diesel", "Electric" };
		
		engineTypeInput = new JComboBox<String>(engineTypes);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, engineTypeInput, 5, SpringLayout.SOUTH, powerClassInput);
		beginTrainLayout.putConstraint(SpringLayout.WEST, engineTypeInput, 0, SpringLayout.EAST, engineTypeLabel);
		beginTrainLayout.putConstraint(SpringLayout.EAST, engineTypeInput, -10, SpringLayout.EAST, beginTrain);
		beginTrainLayout.putConstraint(SpringLayout.SOUTH, engineTypeInput, 0, SpringLayout.SOUTH, engineTypeLabel);
		beginTrain.add(engineTypeInput);
		
		addLocomotiveButton = new JButton ("Add Locomotive");
		addLocomotiveButton.setPreferredSize(driverButtonSize);
		beginTrainLayout.putConstraint(SpringLayout.NORTH, addLocomotiveButton, 10, SpringLayout.SOUTH, engineTypeInput);
		beginTrainLayout.putConstraint(SpringLayout.WEST, addLocomotiveButton, 5, SpringLayout.WEST, beginTrain);
		addLocomotiveButton.addActionListener(this);
		addLocomotiveButton.setEnabled(true);
		beginTrain.add(addLocomotiveButton);
		
		/* - shuntTrain Panel (Part of Driver) ------------------------------------------------------------------------------------------------------------------------ */
		
		shuntTrain = new JPanel();
		shuntTrain.setLayout(shuntTrainLayout);
		shuntTrain.setVisible(false);
		driverLayout.putConstraint(SpringLayout.NORTH, shuntTrain, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, shuntTrain, DEFAULT_PADDING_POS, SpringLayout.EAST, newTrainButton);
		driverLayout.putConstraint(SpringLayout.EAST, shuntTrain, DEFAULT_PADDING_NEG, SpringLayout.EAST, driver);
		driverLayout.putConstraint(SpringLayout.SOUTH, shuntTrain, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, driver);
		driver.add(shuntTrain);
		
		JLabel shuntTrainTitle = new JLabel("Shunt Train");
		shuntTrainTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		shuntTrainLayout.putConstraint(SpringLayout.NORTH, shuntTrainTitle, 14, SpringLayout.NORTH, shuntTrain);
		shuntTrainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, shuntTrainTitle, 0, SpringLayout.HORIZONTAL_CENTER, shuntTrain);
		shuntTrain.add(shuntTrainTitle);
		
		shuntIndexSelectLabel = new JLabel("Shunt after carriage: ");
		shuntIndexSelectLabel.setPreferredSize(driverButtonSize);
		shuntTrainLayout.putConstraint(SpringLayout.NORTH, shuntIndexSelectLabel, 20, SpringLayout.SOUTH, shuntTrainTitle);
		shuntTrainLayout.putConstraint(SpringLayout.WEST, shuntIndexSelectLabel, 10, SpringLayout.WEST, shuntTrain);
		shuntTrain.add(shuntIndexSelectLabel);		
			
		shuntIndexInput = new JComboBox<Integer>();
		shuntTrainLayout.putConstraint(SpringLayout.NORTH, shuntIndexInput, 20, SpringLayout.SOUTH, shuntTrainTitle);
		shuntTrainLayout.putConstraint(SpringLayout.WEST, shuntIndexInput, 0, SpringLayout.EAST, shuntIndexSelectLabel);
		shuntTrainLayout.putConstraint(SpringLayout.EAST, shuntIndexInput, -10, SpringLayout.EAST, shuntTrain);
		shuntTrainLayout.putConstraint(SpringLayout.SOUTH, shuntIndexInput, 0, SpringLayout.SOUTH, shuntIndexSelectLabel);
		shuntTrain.add(shuntIndexInput);
		
		shuntButton = new JButton ("Shunt Train Now");
		shuntButton.setPreferredSize(driverButtonSize);
		shuntTrainLayout.putConstraint(SpringLayout.NORTH, shuntButton, 10, SpringLayout.SOUTH, shuntIndexInput);
		shuntTrainLayout.putConstraint(SpringLayout.WEST, shuntButton, 5, SpringLayout.WEST, shuntTrain);
		shuntButton.addActionListener(this);
		shuntButton.setEnabled(true);
		shuntTrain.add(shuntButton);
		
		/* - addCarriage Panel (Part of Driver) ----------------------------------------------------------------------------------------------------------------------- */
		
		addCarriage = new JPanel();
		addCarriage.setLayout(addCarriageLayout);
		addCarriage.setVisible(false);
		driverLayout.putConstraint(SpringLayout.NORTH, addCarriage, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, addCarriage, DEFAULT_PADDING_POS, SpringLayout.EAST, newTrainButton);
		driverLayout.putConstraint(SpringLayout.EAST, addCarriage, DEFAULT_PADDING_NEG, SpringLayout.EAST, driver);
		driverLayout.putConstraint(SpringLayout.SOUTH, addCarriage, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, driver);
		driver.add(addCarriage);
		
		
		JLabel addCarriageTitle = new JLabel("Add a Carriage");
		addCarriageTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		addCarriageLayout.putConstraint(SpringLayout.NORTH, addCarriageTitle, 14, SpringLayout.NORTH, addCarriage);
		addCarriageLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addCarriageTitle, 0, SpringLayout.HORIZONTAL_CENTER, addCarriage);
		addCarriage.add(addCarriageTitle);
		
		JPanel radioBox = new JPanel();
		radioBox.setLayout(new GridLayout(0,2));
		freightCarSelect = new JRadioButton("Freight Car");
		freightCarSelect.addActionListener(this);
		passengerCarSelect = new JRadioButton("Passenger Car");
		passengerCarSelect.addActionListener(this);
		radioBox.add(freightCarSelect);
		radioBox.add(passengerCarSelect);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, radioBox, 5, SpringLayout.SOUTH, addCarriageTitle);
		addCarriageLayout.putConstraint(SpringLayout.EAST, radioBox, -30, SpringLayout.EAST, addCarriage);
		addCarriageLayout.putConstraint(SpringLayout.WEST, radioBox, 30, SpringLayout.WEST, addCarriage);
		addCarriage.add(radioBox);
		
		/* FREIGHTCAR *******************************************/
		
		grossWeightFreightLabel = new JLabel("Gross Weight (t)");
		grossWeightFreightLabel.setPreferredSize(driverButtonSize);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, grossWeightFreightLabel, 20, SpringLayout.SOUTH, radioBox);
		addCarriageLayout.putConstraint(SpringLayout.WEST, grossWeightFreightLabel, 10, SpringLayout.WEST, addCarriage);
		addCarriage.add(grossWeightFreightLabel);
		
		grossWeightFreightInput = new JTextField(10);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, grossWeightFreightInput, 0, SpringLayout.NORTH, grossWeightFreightLabel);
		addCarriageLayout.putConstraint(SpringLayout.WEST, grossWeightFreightInput, 0, SpringLayout.EAST, grossWeightFreightLabel);
		addCarriageLayout.putConstraint(SpringLayout.EAST, grossWeightFreightInput, -10, SpringLayout.EAST, addCarriage);
		addCarriageLayout.putConstraint(SpringLayout.SOUTH, grossWeightFreightInput, 0, SpringLayout.SOUTH, grossWeightFreightLabel);
		addCarriage.add(grossWeightFreightInput);
		
		goodsTypeLabel = new JLabel("Goods Type");
		goodsTypeLabel.setPreferredSize(driverButtonSize);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, goodsTypeLabel, 5, SpringLayout.SOUTH, grossWeightFreightLabel);
		addCarriageLayout.putConstraint(SpringLayout.WEST, goodsTypeLabel, 10, SpringLayout.WEST, addCarriage);
		addCarriage.add(goodsTypeLabel);
		
		String[] goodsTypes = { "General Goods", "Refrigerated Goods", "Dangerous Materials" };
		
		goodsTypeInput = new JComboBox<String>(goodsTypes);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, goodsTypeInput, 5, SpringLayout.SOUTH, grossWeightFreightInput);
		addCarriageLayout.putConstraint(SpringLayout.WEST, goodsTypeInput, 0, SpringLayout.EAST, goodsTypeLabel);
		addCarriageLayout.putConstraint(SpringLayout.EAST, goodsTypeInput, -10, SpringLayout.EAST, addCarriage);
		addCarriageLayout.putConstraint(SpringLayout.SOUTH, goodsTypeInput, 0, SpringLayout.SOUTH, goodsTypeLabel);
		addCarriage.add(goodsTypeInput);
		
		addFreightButton = new JButton ("Add Freight Car");
		addFreightButton.setPreferredSize(driverButtonSize);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, addFreightButton, 10, SpringLayout.SOUTH, goodsTypeInput);
		addCarriageLayout.putConstraint(SpringLayout.WEST, addFreightButton, 5, SpringLayout.WEST, addCarriage);
		addFreightButton.addActionListener(this);
		addFreightButton.setEnabled(true);
		addCarriage.add(addFreightButton);
		
		setFreightVisible(false);
		
		/****************************************************************************************************************************************************************/
		/* PASSENGERCAR *******************************************/
		
		grossWeightPassengerLabel = new JLabel("Gross Weight (t)");
		grossWeightPassengerLabel.setPreferredSize(driverButtonSize);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, grossWeightPassengerLabel, 20, SpringLayout.SOUTH, radioBox);
		addCarriageLayout.putConstraint(SpringLayout.WEST, grossWeightPassengerLabel, 10, SpringLayout.WEST, addCarriage);
		addCarriage.add(grossWeightPassengerLabel);
		
		grossWeightPassengerInput = new JTextField(10);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, grossWeightPassengerInput, 0, SpringLayout.NORTH, grossWeightPassengerLabel);
		addCarriageLayout.putConstraint(SpringLayout.WEST, grossWeightPassengerInput, 0, SpringLayout.EAST, grossWeightPassengerLabel);
		addCarriageLayout.putConstraint(SpringLayout.EAST, grossWeightPassengerInput, -10, SpringLayout.EAST, addCarriage);
		addCarriageLayout.putConstraint(SpringLayout.SOUTH, grossWeightPassengerInput, 0, SpringLayout.SOUTH, grossWeightPassengerLabel);
		addCarriage.add(grossWeightPassengerInput);
		
		numberOfSeatsLabel = new JLabel("Number of Seats");
		numberOfSeatsLabel.setPreferredSize(driverButtonSize);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, numberOfSeatsLabel, 5, SpringLayout.SOUTH, grossWeightPassengerLabel);
		addCarriageLayout.putConstraint(SpringLayout.WEST, numberOfSeatsLabel, 10, SpringLayout.WEST, addCarriage);
		addCarriage.add(numberOfSeatsLabel);
		
		numberOfSeatsInput = new JTextField(10);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, numberOfSeatsInput, 5, SpringLayout.SOUTH, grossWeightPassengerInput);
		addCarriageLayout.putConstraint(SpringLayout.WEST, numberOfSeatsInput, 0, SpringLayout.EAST, numberOfSeatsLabel);
		addCarriageLayout.putConstraint(SpringLayout.EAST, numberOfSeatsInput, -10, SpringLayout.EAST, addCarriage);
		addCarriageLayout.putConstraint(SpringLayout.SOUTH, numberOfSeatsInput, 0, SpringLayout.SOUTH, numberOfSeatsLabel);
		addCarriage.add(numberOfSeatsInput);
		
		addPassengerButton = new JButton ("Add Passenger Car");
		addPassengerButton.setPreferredSize(driverButtonSize);
		addCarriageLayout.putConstraint(SpringLayout.NORTH, addPassengerButton, 10, SpringLayout.SOUTH, numberOfSeatsInput);
		addCarriageLayout.putConstraint(SpringLayout.WEST, addPassengerButton, 5, SpringLayout.WEST, addCarriage);
		addPassengerButton.addActionListener(this);
		addPassengerButton.setEnabled(true);
		addCarriage.add(addPassengerButton);
		
		setPassengerVisible(false);
		
		/****************************************************************************************************************************************************************/
		
		/* - Conductor (Part of user) --------------------------------------------------------------------------------------------------------------------------------- */
		
		conductor = new JPanel();
		conductor.setLayout(conductorLayout);
		users.add(conductor);
		
		JLabel conductorTitle = new JLabel("Conductor Controls");
		conductorTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		conductorLayout.putConstraint(SpringLayout.NORTH, conductorTitle, 20, SpringLayout.NORTH, conductor);
		conductorLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, conductorTitle, 0, SpringLayout.HORIZONTAL_CENTER, conductor);
		conductor.add(conductorTitle);
		
		Dimension conductorButtonSize = new Dimension(200,30);
		
		boardPassengersButton = new JButton("Board Passengers");
		boardPassengersButton.setPreferredSize(conductorButtonSize);
		conductorLayout.putConstraint(SpringLayout.NORTH, boardPassengersButton, 20, SpringLayout.SOUTH, conductorTitle);
		conductorLayout.putConstraint(SpringLayout.WEST, boardPassengersButton, 10, SpringLayout.WEST, conductor);
		boardPassengersButton.addActionListener(this);
		boardPassengersButton.setEnabled(false);
		conductor.add(boardPassengersButton);
		
		/* - BoardPassengers (Part of Conductor) --------------------------------------------------------------------------------------------------------------------------------- */
		
		boardPassengers = new JPanel();
		boardPassengers.setLayout(boardPassengersLayout);
		boardPassengers.setVisible(false);
		conductorLayout.putConstraint(SpringLayout.NORTH, boardPassengers, 20, SpringLayout.SOUTH, conductorTitle);
		conductorLayout.putConstraint(SpringLayout.WEST, boardPassengers, DEFAULT_PADDING_POS, SpringLayout.EAST, boardPassengersButton);
		conductorLayout.putConstraint(SpringLayout.EAST, boardPassengers, DEFAULT_PADDING_NEG, SpringLayout.EAST, conductor);
		conductorLayout.putConstraint(SpringLayout.SOUTH, boardPassengers, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, conductor);
		conductor.add(boardPassengers);
		
		// BoardPassengers title
		JLabel boardPassengersTitle = new JLabel("Board Passengers");
		boardPassengersTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		boardPassengersLayout.putConstraint(SpringLayout.NORTH, boardPassengersTitle, 14, SpringLayout.NORTH, boardPassengers);
		boardPassengersLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boardPassengersTitle, 0, SpringLayout.HORIZONTAL_CENTER, boardPassengers);
		boardPassengers.add(boardPassengersTitle);
		
		// BoardPassengers text input label
		boardPassengersLabel = new JLabel("Amount boarding:");
		boardPassengersLabel.setPreferredSize(driverButtonSize);
		boardPassengersLayout.putConstraint(SpringLayout.NORTH, boardPassengersLabel, 20, SpringLayout.NORTH, boardPassengersTitle);
		boardPassengersLayout.putConstraint(SpringLayout.WEST, boardPassengersLabel, 10, SpringLayout.WEST, boardPassengers);
		boardPassengers.add(boardPassengersLabel);
		
		// BoardPassengers text input
		boardPassengersInput = new JTextField(10);
		boardPassengersLayout.putConstraint(SpringLayout.NORTH, boardPassengersInput, 0, SpringLayout.NORTH, boardPassengersLabel);
		boardPassengersLayout.putConstraint(SpringLayout.WEST, boardPassengersInput, 0, SpringLayout.EAST, boardPassengersLabel);
		boardPassengersLayout.putConstraint(SpringLayout.EAST, boardPassengersInput, -10, SpringLayout.EAST, boardPassengers);
		boardPassengersLayout.putConstraint(SpringLayout.SOUTH, boardPassengersInput, 0, SpringLayout.SOUTH, boardPassengersLabel);
		boardPassengers.add(boardPassengersInput);	
		
		// BoardPassengers button
		boardNowButton = new JButton ("Board Now");
		boardNowButton.setPreferredSize(driverButtonSize);
		boardPassengersLayout.putConstraint(SpringLayout.NORTH, boardNowButton, 10, SpringLayout.SOUTH, boardPassengersInput);
		boardPassengersLayout.putConstraint(SpringLayout.WEST, boardNowButton, 5, SpringLayout.WEST, boardPassengers);
		boardNowButton.addActionListener(this);
		boardNowButton.setEnabled(true);
		boardPassengers.add(boardNowButton);
		
		/* - AlightPassengers (Part of Conductor) --------------------------------------------------------------------------------------------------------------------------------- */
		
		alightPassengersButton = new JButton("Alight Passengers");
		alightPassengersButton.setPreferredSize(conductorButtonSize);
		conductorLayout.putConstraint(SpringLayout.NORTH, alightPassengersButton, 0, SpringLayout.SOUTH, boardPassengersButton);
		conductorLayout.putConstraint(SpringLayout.WEST, alightPassengersButton, 10, SpringLayout.WEST, conductor);
		alightPassengersButton.addActionListener(this);
		alightPassengersButton.setEnabled(true);
		conductor.add(alightPassengersButton);
		
		// AlightPassengers Panel		
		alightPassengers = new JPanel();
		alightPassengers.setLayout(alightPassengersLayout);
		alightPassengers.setVisible(false);
		conductorLayout.putConstraint(SpringLayout.NORTH, alightPassengers, 20, SpringLayout.SOUTH, conductorTitle);
		conductorLayout.putConstraint(SpringLayout.WEST, alightPassengers, DEFAULT_PADDING_POS, SpringLayout.EAST, boardPassengersButton);
		conductorLayout.putConstraint(SpringLayout.EAST, alightPassengers, DEFAULT_PADDING_NEG, SpringLayout.EAST, conductor);
		conductorLayout.putConstraint(SpringLayout.SOUTH, alightPassengers, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, conductor);
		conductor.add(alightPassengers);
		
		// AlightPassengers Title Label		
		JLabel alightPassengersTitle = new JLabel("Alight Passengers");
		alightPassengersTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		alightPassengersLayout.putConstraint(SpringLayout.NORTH, alightPassengersTitle, 14, SpringLayout.NORTH, alightPassengers);
		alightPassengersLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, alightPassengersTitle, 0, SpringLayout.HORIZONTAL_CENTER, alightPassengers);
		alightPassengers.add(alightPassengersTitle);
		
		// AlightPassengers text input label
		alightPassengersLabel = new JLabel("Amount alighting:");
		alightPassengersLabel.setPreferredSize(driverButtonSize);
		alightPassengersLayout.putConstraint(SpringLayout.NORTH, alightPassengersLabel, 20, SpringLayout.NORTH, alightPassengersTitle);
		alightPassengersLayout.putConstraint(SpringLayout.WEST, alightPassengersLabel, 10, SpringLayout.WEST, alightPassengers);
		alightPassengers.add(alightPassengersLabel);
		
		// AlightPassengers text input
		alightPassengersInput = new JTextField(10);
		alightPassengersLayout.putConstraint(SpringLayout.NORTH, alightPassengersInput, 0, SpringLayout.NORTH, alightPassengersLabel);
		alightPassengersLayout.putConstraint(SpringLayout.WEST, alightPassengersInput, 0, SpringLayout.EAST, alightPassengersLabel);
		alightPassengersLayout.putConstraint(SpringLayout.EAST, alightPassengersInput, -10, SpringLayout.EAST, alightPassengers);
		alightPassengersLayout.putConstraint(SpringLayout.SOUTH, alightPassengersInput, 0, SpringLayout.SOUTH, alightPassengersLabel);
		alightPassengers.add(alightPassengersInput);
		
		// AlightPassengers button
		alightNowButton = new JButton ("Alight Now");
		alightNowButton.setPreferredSize(driverButtonSize);
		alightPassengersLayout.putConstraint(SpringLayout.NORTH, alightNowButton, 10, SpringLayout.SOUTH, alightPassengersInput);
		alightPassengersLayout.putConstraint(SpringLayout.WEST, alightNowButton, 5, SpringLayout.WEST, alightPassengers);
		alightNowButton.addActionListener(this);
		alightNowButton.setEnabled(true);
		alightPassengers.add(alightNowButton);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DepartingTrainGUI frame = new DepartingTrainGUI("Marshalling Yard");
					frame.addComponentsToPanel(frame.getContentPane());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(screenSize);
					frame.pack();
					frame.setVisible(true);
					frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @author Robert Dempsey N5400872
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		
		switch(buttonString) {
		case "Begin Train":
			
			// Construct new train and enable/disable appropriate
			// buttons and panels
			departingTrain = new DepartingTrain();
			
			beginTrain.setVisible(true);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			
			driver.repaint();
			
			break;
		case "Cancel Train":
			
			// Nullify actual trains
			departingTrain = null;
			spurLine = null;
			
			// Remove all carraiges from screen
			trainInfo.removeAll();
			shuntInfo.removeAll();
			trainInfo.repaint();
			shuntInfo.repaint();
			
			driverButtonEnable(false);
					
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			boardPassengers.setVisible(false);
			alightPassengers.setVisible(false);
			
			// Reset global variables
			trainWeight = 0;
			trainPower = 0;
			leftBehind = 0;
			shuntIndexInput.removeAllItems();
			updateTrainStatistics();
			
			driver.repaint();
			
			break;
		case "Shunt Train":
			
			// enable/disable appropriate buttons and panels
			boardPassengers.setVisible(false);
			boardPassengersButton.setEnabled(false);
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(true);
			break;
		case "Connect Shunted Train":
			
			isShunted = false;
			
			// enable/disable appropriate buttons and panels
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			try {
				RollingStock carriage = spurLine.firstCarriage();
				carriage = spurLine.nextCarriage();
				while (carriage != null){
					departingTrain.addCarriage(carriage);
					trainWeight += carriage.getGrossWeight();
					carriage = spurLine.nextCarriage();
				}
				spurLine = null;
				shuntInfo.removeAll();
				shuntInfo.setVisible(false);
				trainInfo = redrawTrainImage(departingTrain, trainInfo);
				updateTrainStatistics();	
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
			joinTrainButton.setEnabled(false);
			shuntTrainButton.setEnabled(true);
			boardPassengersButton.setEnabled(true);
			shuntInfo.setVisible(false);
			trainInfo.repaint();
			break;
		case "Add New Carriage":
			trainInfo.repaint();
			beginTrain.setVisible(false);
			addCarriage.setVisible(true);
			shuntTrain.setVisible(false);
			
			break;
		case "Remove Last Carriage":
			final int oneCarriage = 1;
			try {
				departingTrain.firstCarriage();
				
				// Get last carriage's weight to update trainWeight
				for (int i = 0; i < trainInfo.getComponentCount() - 1; i++) {
					RollingStock carriage;
					if (trainInfo.getComponentCount() == oneCarriage) {
						carriage = departingTrain.firstCarriage();
					} else {
					carriage = departingTrain.nextCarriage(); 
					}
					lastCarriageGrossWeight = carriage.getGrossWeight();
				}
				
				trainWeight -= lastCarriageGrossWeight;
				departingTrain.removeCarriage();
				// reset variables if all carriages removed
				if (departingTrain.firstCarriage() == null) {
					trainInfo.removeAll();
					trainInfo.repaint();
					beginTrain.setVisible(true);
					addCarriage.setVisible(false);
					driverButtonEnable(false);
					newTrainButton.setEnabled(false);
					resetTrainButton.setEnabled(true);
					shuntIndexInput.removeAllItems();
				} else {
					trainInfo = redrawTrainImage(departingTrain, trainInfo);
				}
				updateTrainStatistics();
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			} catch (IndexOutOfBoundsException trainException) {
				JOptionPane.showMessageDialog(null, "There are no carriages to remove.");
			}
			break;
		case "Board Passengers":
			boardPassengers.setVisible(true);
			alightPassengers.setVisible(false);
			break;
		case "Board Now":
			
			// add passengers and determine number left behind, update display and stats
			try {
				Integer passengersBoarding = Integer.parseInt(boardPassengersInput.getText());
				leftBehind += departingTrain.board(passengersBoarding);
				train.repaint();
				alightPassengersButton.setEnabled(true);
				updateTrainStatistics();
				trainInfo = redrawTrainImage(departingTrain, trainInfo);
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
			
			break;
		case "Alight Passengers":
			alightPassengers.setVisible(true);
			boardPassengers.setVisible(false);
			break;
		case "Alight Now":
			final Integer zeroPassengers = 0;
			
			// remove passengers, update display and stats
			try {
				Integer passengersAlighting = Integer.parseInt(alightPassengersInput.getText());
				alight(departingTrain, passengersAlighting);
				updateTrainStatistics();
				trainInfo= redrawTrainImage(departingTrain, trainInfo);
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
			
			if (departingTrain.numberOnBoard() == zeroPassengers) {
				alightPassengersButton.setEnabled(false);
				alightPassengers.setVisible(false);
			}
	
			break;
		case "Add Locomotive":
			Integer grossWeight = Integer.parseInt(grossWeightInput.getText());
			Integer powerClass = powerClassInput.getSelectedIndex() + 1;
			String engineType = engineTypeInput.getSelectedItem().toString().substring(0, 1);
			trainWeight += grossWeight;
			String classification = powerClass + engineType;
			
			// add locomotive, update display and stats
			try {
				Locomotive loco = new Locomotive(grossWeight, classification);
				departingTrain.addCarriage(loco);
				trainPower = loco.power();
				updateTrainStatistics();
				trainInfo = redrawTrainImage(departingTrain, trainInfo);
				shuntIndexInput.addItem(trainInfo.getComponentCount()); // update shuntIndex options
				this.pack();
				driverButtonEnable(true);
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			
			beginTrain.setVisible(false);
			addCarriage.setVisible(true);
			break;
		case "Freight Car":
			passengerCarSelect.setSelected(false);
			setFreightVisible(true);
			setPassengerVisible(false);
			break;
		case "Passenger Car":
			freightCarSelect.setSelected(false);
			setFreightVisible(false);
			setPassengerVisible(true);
			break;
		case "Add Passenger Car":
			Integer grossWeightPassenger = Integer.parseInt(grossWeightPassengerInput.getText());
			Integer numberOfSeats = Integer.parseInt(numberOfSeatsInput.getText());
			try {
				
				// add passenger car, update display and stats
				PassengerCar passengerCar = new PassengerCar(grossWeightPassenger, numberOfSeats);
				departingTrain.addCarriage(passengerCar);
				trainWeight += passengerCar.getGrossWeight();
				updateTrainStatistics();
				trainInfo = redrawTrainImage(departingTrain, trainInfo);
				shuntIndexInput.addItem(trainInfo.getComponentCount()); // update shuntIndex options
				buttonDisableAddCarriage(true);
				this.pack();
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			break;
		case "Add Freight Car":
			Integer grossWeightFreight = Integer.parseInt(grossWeightFreightInput.getText());
			String goodsType = goodsTypeInput.getSelectedItem().toString().substring(0,1);
			
			try {
				
				// add freight car, update display and stats
				FreightCar freightCar = new FreightCar(grossWeightFreight, goodsType);
				departingTrain.addCarriage(freightCar);
				trainWeight += freightCar.getGrossWeight();
				updateTrainStatistics();
				buttonDisableAddCarriage(true);
				trainInfo = redrawTrainImage(departingTrain, trainInfo);
				shuntIndexInput.addItem(trainInfo.getComponentCount()); // update shuntIndex options
				updateTrainStatistics();
				this.pack();
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			break;
		case "Shunt Train Now":
			isShunted = true;
			shuntInfo.setVisible(true);
			spurLine = new DepartingTrain();
			// Get number of RollingStock carriages to iterate through
			shuntNumber = shuntIndexInput.getSelectedIndex();
			
			/* Locomotive to Move the Train Around the Marshalling Yard */
			
			try {
				spurLine.addCarriage(new Locomotive(1,"9D"));
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
			
			/*Cycle through train until reaching the shunt carriage then add
			 * the carriage to the spurLine Train and remove from departingTrain
			 */ 
			departingTrain.firstCarriage();
			for (int i=0; i<=shuntNumber-1; i++){
				departingTrain.nextCarriage();
			}
			
			RollingStock carriage = departingTrain.nextCarriage();
			int removeIterator = 0;
			while (carriage != null) {
				try {
					spurLine.addCarriage(carriage);
					trainWeight -= carriage.getGrossWeight();
					carriage = departingTrain.nextCarriage();
					removeIterator++;
				} catch (TrainException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
			
			for (int i = 1; i <= removeIterator; i++){
				try {
					departingTrain.removeCarriage();
				} catch (TrainException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
			
			shuntTrainButton.setEnabled(false);
			shuntTrain.setVisible(false);
			joinTrainButton.setEnabled(true);
			
			updateTrainStatistics();
			trainInfo = redrawTrainImage(departingTrain, trainInfo);
			shuntInfo = redrawTrainImage(spurLine, shuntInfo);
			
			trainInfo.repaint();
			shuntInfo.repaint();
			train.repaint();
		}
		
	}
	
	/**
	 * Sets visibility for the fields required to add a FreightCar 
	 * @author JoshuaWright(n6366066)
	 * @param visible (boolean) 
	 */
	private void setFreightVisible(boolean visible){
		addFreightButton.setVisible(visible);
		goodsTypeInput.setVisible(visible);
		goodsTypeLabel.setVisible(visible);
		grossWeightFreightInput.setVisible(visible);
		grossWeightFreightLabel.setVisible(visible);
	}
	
	/**
	 * Sets visibility for the fields required to add a PassengerCar 
	 * @author JoshuaWright(n6366066)
	 * @param visible (boolean) 
	 */
	private void setPassengerVisible(boolean visible){
		addPassengerButton.setVisible(visible);
		numberOfSeatsInput.setVisible(visible);
		numberOfSeatsLabel.setVisible(visible);
		grossWeightPassengerInput.setVisible(visible);
		grossWeightPassengerLabel.setVisible(visible);
	}
	
	/**
	 * Sets visibility for the fields required when train is cancelled
	 * @author JoshuaWright(n6366066)
	 * @param visible (boolean) 
	 */
	private void driverButtonEnable(boolean enabled) {
		if (enabled) { newTrainButton.setEnabled(false); }
		else { newTrainButton.setEnabled(true); }
		
		resetTrainButton.setEnabled(enabled);
		shuntTrainButton.setEnabled(false);
		joinTrainButton.setEnabled(false);
		addCarriageButton.setEnabled(enabled);
		removeCarriageButton.setEnabled(enabled);
		boardPassengersButton.setEnabled(false);
		alightPassengersButton.setEnabled(false);
	}
	
	/**
	 * @author Robert Dempsey (N5400872)
	 * Sets visibility and enabled state of panes and buttons if a
	 * carriage is added
	 * @param enabled - represents desired state of button
	 */
	private void buttonDisableAddCarriage(boolean enabled) {
		if (!isShunted) {
			shuntTrainButton.setEnabled(enabled);
			addCarriageButton.setEnabled(enabled);
			boardPassengersButton.setEnabled(enabled);
		}
			removeCarriageButton.setEnabled(enabled);
	}
	
	/**
	 * This method is responsible for updating the train information 
	 * labels onScreen to give information to the driver and conductor
	 * respectively about the train weight, power, passengers on board,
	 * seats and number of people left behind
	 * @author Josh Wright (n6366066)
	 */
	private void updateTrainStatistics() {
		trainWeightLabel.setText("Train Weight: " + trainWeight + "(t)");
		trainPowerLabel.setText("Pulling Power: " + trainPower +"(t)");
		if (departingTrain.trainCanMove()) {
			trainMoveLabel.setText("Enough Power?: Yes");
			trainMoveLabel.setForeground(Color.green);
			trainWeightLabel.setForeground(Color.black);
			trainPowerLabel.setForeground(Color.black);
		} else {
			trainMoveLabel.setText("Enough Power?: No");
			trainMoveLabel.setForeground(Color.red);
			trainWeightLabel.setForeground(Color.red);
			trainPowerLabel.setForeground(Color.red);
		}
		trainCapacityLabel.setText("Train Capacity: " + departingTrain.numberOnBoard() + "/" + departingTrain.numberOfSeats());
		trainLeftBehindLabel.setText("Passengers Left Behind: " + leftBehind);
		if (leftBehind > 0) {
			trainLeftBehindLabel.setForeground(Color.red);
		} else {
			trainLeftBehindLabel.setForeground(Color.black);
		}
	}
	
	/**
	 * Drops and Redraws Train Images in Info Panel (trainInfo or shuntInfo)
	 * @author Joshua Wright (n6366066)
	 * @param carriage
	 * @param carriageNo
	 * @return (JPanel) refreshed panel
	 */
	private JPanel redrawTrainImage(DepartingTrain train, JPanel panel) {
		RollingStock carriageAdd = train.firstCarriage();
		boolean passengersOnBoard = (train.numberOnBoard() > 0);
		File imgFile = null;
		String carriageLabel = null;
		BufferedImage image = null;
		Integer carriageNo = 1;
		
		panel.removeAll();
		
		// disable any shunting operations while passengers are on board
		if (passengersOnBoard) {			
			shuntTrainButton.setEnabled(false);
			addCarriageButton.setEnabled(false);
		} else {
			addCarriageButton.setEnabled(true);
		}
		
		if (isShunted) {
			boardPassengers.setVisible(false);
			boardPassengersButton.setEnabled(false);
			shuntTrainButton.setEnabled(false);
		} else {
			boardPassengersButton.setEnabled(true);
		}
		
		while (carriageAdd != null) {		
			if (carriageAdd.getClass() == PassengerCar.class){
				imgFile = new File("rsc/passenger.jpg");
				carriageLabel = ((PassengerCar)carriageAdd).toString();
			} else if (carriageAdd.getClass() == FreightCar.class){
				imgFile = new File("rsc/freight.jpg");
				carriageLabel = ((FreightCar)carriageAdd).toString();
			} else if (carriageAdd.getClass() == Locomotive.class){
				Locomotive loco = (Locomotive) carriageAdd;
				carriageLabel = loco.toString();
				if (carriageLabel.contains("D")) {
					imgFile = new File("rsc/diesel.jpg");
				} else if (carriageLabel.contains("E")) {
					imgFile = new File("rsc/electric.jpg");
				} else if (carriageLabel.contains("S")) {
					imgFile = new File("rsc/steam.jpg");
				}
			}
			
			try {                
				image = ImageIO.read(imgFile);
		    } catch (IOException ex) {
		        JOptionPane.showMessageDialog(null,"Image File Not Found: " + imgFile); 
		    }
			
			JLabel imageLabel = new JLabel(new ImageIcon(image));
			JLabel infoLabelBottom = new JLabel(carriageLabel);
			JLabel infoLabelTop = new JLabel("Carriage No: " + carriageNo);
			SpringLayout layout = new SpringLayout();
			
			JPanel carriagePanel = new JPanel();
			carriagePanel.setLayout(layout);
			carriagePanel.add(infoLabelTop);
			carriagePanel.add(imageLabel);
			carriagePanel.add(infoLabelBottom);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, imageLabel, 0, SpringLayout.HORIZONTAL_CENTER, carriagePanel);
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, imageLabel, 0, SpringLayout.VERTICAL_CENTER, carriagePanel);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, infoLabelTop, 0, SpringLayout.HORIZONTAL_CENTER, carriagePanel);
			layout.putConstraint(SpringLayout.SOUTH, infoLabelTop, 0, SpringLayout.NORTH, imageLabel);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, infoLabelBottom, 0, SpringLayout.HORIZONTAL_CENTER, carriagePanel);
			layout.putConstraint(SpringLayout.NORTH, infoLabelBottom, 0, SpringLayout.SOUTH, imageLabel);
			carriagePanel.setBackground(Color.WHITE);
			panel.add(carriagePanel);
			
			carriageNo++;
			carriageAdd = train.nextCarriage();
		}
		return panel;
	}
	
	/**
	 * Remove passengers from train
	 * @param (Integer) alightPassengers - the number of passengers wanting to board the train
	 * @throws TrainException if passengers boarding is negative
	 */
	public void alight(DepartingTrain train, Integer alightPassengers)
            throws TrainException {	
		
		final Integer noPassengers = 0;
		
		// Skip locomotive
		train.firstCarriage(); 
		
		try {
			RollingStock carriage = train.nextCarriage();
			while (alightPassengers > noPassengers) {
				if (carriage.getClass() == PassengerCar.class) {
					PassengerCar passengerCarriage = (PassengerCar) carriage;
					if (alightPassengers >= passengerCarriage.numberOnBoard() && 
						alightPassengers <= train.numberOnBoard()) {
						Integer alight = passengerCarriage.numberOnBoard();
						alightPassengers -= alight;
						passengerCarriage.alight(alight);
					} else {
						passengerCarriage.alight(alightPassengers);
						alightPassengers = noPassengers;
					}
				}
				carriage = train.nextCarriage();
			}
		} catch (TrainException trainException) {
			JOptionPane.showMessageDialog(null, trainException.getMessage());
		}
		
		redrawTrainImage(train, trainInfo);
	}
}
