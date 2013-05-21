package asgn2GUI;

/**
 * @author Joshua
 *
 */

import java.awt.*;
import java.awt.event.*;
//import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;

//import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;
import asgn2Train.DepartingTrain;

public class testGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5400452591692641009L;
	/* Declare Objects Here */
	final int DEFAULT_PADDING_POS = 10;
	final int DEFAULT_PADDING_NEG = -10;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private GridLayout baseLayout = new GridLayout(2,0);
	private SpringLayout trainLayout = new SpringLayout();
	private GridLayout trainInfoLayout = new GridLayout(1,20);
	private SpringLayout driverLayout = new SpringLayout();
	private SpringLayout conductorLayout = new SpringLayout();	
	private SpringLayout shuntTrainLayout = new SpringLayout();
	private SpringLayout addCarriageLayout = new SpringLayout();
	private SpringLayout beginTrainLayout = new SpringLayout();
	private SpringLayout boardPassengersLayout = new SpringLayout();
	private SpringLayout alightPassengersLayout = new SpringLayout();
	
	private JPanel trainControl;
	private JPanel train;
	private JPanel trainInfo;
	private JPanel users;
	private JPanel driver;
	private JPanel shuntTrain;
	private JPanel addCarriage;
	private JPanel conductor;
	private JPanel beginTrain;
	private DepartingTrain departingTrain;
	private JButton newTrainButton;
	private JButton resetTrainButton;
	private JButton shuntTrainButton;
	private JButton joinTrainButton;
	private JButton addCarriageButton;
	private JButton removeCarriageButton;
	private JButton boardPassengersButton;
	private JButton alightPassengersButton;
	private JButton addLocomotiveButton;
	private JTextField grossWeightInput;
	private JComboBox<Integer> powerClassInput;
	private JComboBox<String> engineTypeInput;
	private JComboBox<String> goodsTypeInput;
	private JButton addFreightButton;
	private JTextField grossWeightFreightInput;
	private JRadioButton freightCarSelect;
	private JRadioButton passengerCarSelect;
	private JLabel goodsTypeLabel;
	private JLabel grossWeightFreightLabel;
	private JLabel grossWeightPassengerLabel;
	private JTextField grossWeightPassengerInput;
	private JButton addPassengerButton;
	private JTextField numberOfSeatsInput;
	private JLabel numberOfSeatsLabel;
	private JLabel shuntIndexSelectLabel;
	private JComboBox<Integer> shuntIndexInput;
	private JButton shuntButton;
	private JPanel shuntInfo;
	
	private ArrayList<RollingStock> spurCarriages;
	private ArrayList<Component> spurCarriagePanels;
	private int shuntNumber;
	private JLabel boardPassengersLabel;
	private JPanel boardPassengers;
	private JTextField boardPassengersInput;
	private JPanel alightPassengers;
	private JLabel alightPassengersLabel;
	private JTextField alightPassengersInput;
	private JButton boardNowButton;
	private JButton alightNowButton;
	private Integer trainWeight = 0;
	private Integer trainPower = 0;
	private JLabel trainWeightLabel;
	private JLabel trainPowerLabel;
	private JLabel trainMoveLabel;
	private JLabel trainCapacityLabel;
	private JLabel trainLeftBehindLabel;
	private Integer leftBehind = 0;
	private String currentCarriageString;
	private TrainGraphics currentCarriagePanel;
	private int carriagePanelCount;
	private RollingStock currentCarriage;
	private ArrayList<Component> newPanels;
	
	
	public testGUI(String name) {
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
		shuntInfo.setBackground(Color.YELLOW);
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
		driver.setBackground(Color.PINK);
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
		beginTrain.setBackground(Color.gray);
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
		shuntTrain.setBackground(Color.yellow);
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
		addCarriage.setBackground(Color.magenta);
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
		
		SetFreightVisible(false);
		
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
		
		SetPassengerVisible(false);
		
		/****************************************************************************************************************************************************************/
		
		/* - Conductor (Part of user) --------------------------------------------------------------------------------------------------------------------------------- */
		
		conductor = new JPanel();
		conductor.setBackground(Color.GREEN);
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
		boardPassengers.setBackground(Color.yellow);
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
		alightPassengers.setBackground(Color.pink);
		alightPassengers.setVisible(false);
		conductorLayout.putConstraint(SpringLayout.NORTH, alightPassengers, 20, SpringLayout.SOUTH, conductorTitle);
		conductorLayout.putConstraint(SpringLayout.WEST, alightPassengers, DEFAULT_PADDING_POS, SpringLayout.EAST, boardPassengersButton);
		conductorLayout.putConstraint(SpringLayout.EAST, alightPassengers, DEFAULT_PADDING_NEG, SpringLayout.EAST, conductor);
		conductorLayout.putConstraint(SpringLayout.SOUTH, alightPassengers, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, conductor);
		conductor.add(alightPassengers);
		
		// AlightPassengers Label
		
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
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testGUI frame = new testGUI("Train Controller Test");
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
			departingTrain = new DepartingTrain();
			
			beginTrain.setVisible(true);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			
			driver.repaint();
			
			break;
		case "Cancel Train":
			departingTrain = null;
			
			trainInfo.removeAll();
			trainInfo.repaint();
			DriverButtonEnable(false);
			
			shuntInfo.removeAll();
			spurCarriages = null;
			
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			
			driver.repaint();
			
			break;
		case "Shunt Train":
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(true);
			break;
		case "Connect Shunted Train":
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			try {
				for (int i = 0; i < spurCarriages.size(); i++)  {
					departingTrain.addCarriage(spurCarriages.get(i));
					for (int j = spurCarriagePanels.size() - 1; j >= 0; j--) {
						TrainGraphics currentPanel = (TrainGraphics) spurCarriagePanels.get(j);
						trainInfo.add(currentPanel);
						currentPanel.setIndexLabel(trainInfo.getComponentCount());
						shuntInfo.removeAll();
					}
				} 
					
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
			joinTrainButton.setEnabled(false);
			shuntTrainButton.setEnabled(true);
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
			//beginTrain.setVisible(false);
			//addCarriage.setVisible(false); Should be able to remove a carriage and stay on our current panel
			//shuntTrain.setVisible(false);
			Component[] components = trainInfo.getComponents();
			try {
				trainInfo.remove(components.length - 1);
				departingTrain.removeCarriage();
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			} catch (IndexOutOfBoundsException trainException) {
				JOptionPane.showMessageDialog(null, "There are no carriages to remove.");
			}
			trainInfo.repaint();
			components = trainInfo.getComponents();
			
			/*If all carriages have been removed, set add locomotive pane visible, others to not visible 
			 * and disable all buttons but cancel train */
			if (components.length == 0) {
				beginTrain.setVisible(true);
				addCarriage.setVisible(false);
				shuntTrain.setVisible(false);
				ButtonDisableNoCarriages(false); 				
			}
			break;
		case "Board Passengers":
			boardPassengers.setVisible(true);
			alightPassengers.setVisible(false);
			break;
		case "Board Now":
			currentCarriage = departingTrain.firstCarriage();
			newPanels = new ArrayList<Component>();			
			carriagePanelCount = trainInfo.getComponentCount();
			
			try {
				Integer passengersBoarding = Integer.parseInt(boardPassengersInput.getText());
				leftBehind = departingTrain.board(passengersBoarding);
				trainCapacityLabel.setText("Train Capacity: " + departingTrain.numberOnBoard() + "/" + departingTrain.numberOfSeats());
				trainLeftBehindLabel.setText("Passengers Left Behind: " + leftBehind);
				train.repaint();
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
				
			// update labels after passengers are added
			for (int i = 0; i < carriagePanelCount; i++) {
				currentCarriage = departingTrain.nextCarriage();
				currentCarriagePanel = (TrainGraphics) trainInfo.getComponent(i);
				if (currentCarriage.getClass() == PassengerCar.class) {						
					currentCarriage = ( PassengerCar ) currentCarriage;
					currentCarriageString = currentCarriage.toString();
					currentCarriagePanel.setStringLabel(currentCarriageString);
				}
				newPanels.add(currentCarriagePanel);
			}
			trainInfo.removeAll();
		
			// NEW PANELS (CARRIAGES WITH UPDATED TO STRINGS) NOT BEING ADDED **** (RD)
			for (int i = 0; i < newPanels.size(); i++) {
				trainInfo.add(newPanels.get(i));
			}
			trainInfo.repaint();
			break;
		case "Alight Passengers":
			alightPassengers.setVisible(true);
			boardPassengers.setVisible(false);
			break;
		case "Alight Now":
			currentCarriage = departingTrain.firstCarriage();
			newPanels = new ArrayList<Component>();			
			carriagePanelCount = trainInfo.getComponentCount();
			
			try {
				Integer passengersAlighting = Integer.parseInt(alightPassengersInput.getText());
				departingTrain.alight(passengersAlighting);
				trainCapacityLabel.setText("Train Capacity: " + departingTrain.numberOnBoard() + "/" + departingTrain.numberOfSeats());
				trainLeftBehindLabel.setText("Passengers Left Behind: " + leftBehind);
				train.repaint();
			} catch (TrainException trainException) {
				JOptionPane.showMessageDialog(null, trainException);
			}
				
			// update labels after passengers are added
			for (int i = 0; i < carriagePanelCount; i++) {
				currentCarriage = departingTrain.nextCarriage();
				currentCarriagePanel = (TrainGraphics) trainInfo.getComponent(i);
				if (currentCarriage.getClass() == PassengerCar.class) {						
					currentCarriage = ( PassengerCar ) currentCarriage;
					currentCarriageString = currentCarriage.toString();
					currentCarriagePanel.setStringLabel(currentCarriageString);
				}
				newPanels.add(currentCarriagePanel);
			}
			trainInfo.removeAll();
		
			// NEW PANELS (CARRIAGES WITH UPDATED TO STRINGS) NOT BEING ADDED **** (RD)
			for (int i = 0; i < newPanels.size(); i++) {
				trainInfo.add(newPanels.get(i));
			}
			trainInfo.repaint();
			break;
		case "Add Locomotive":
			Integer grossWeight = Integer.parseInt(grossWeightInput.getText());
			Integer powerClass = powerClassInput.getSelectedIndex() + 1;
			String engineType = engineTypeInput.getSelectedItem().toString().substring(0, 1);
			trainWeight += grossWeight;
			// construct and add locomotive panel
			File locoImgFile = null;
			switch(engineType){
			case "D":
				locoImgFile = new File("rsc/diesel.jpg");
				break;
			case "E":
				locoImgFile = new File("rsc/electric.jpg");
				break;
			case "S":
				locoImgFile = new File("rsc/steam.jpg");
				break;
			}
			String classification = powerClass + engineType;
			try {
				Locomotive loco = new Locomotive(grossWeight, classification);
				departingTrain.addCarriage(loco);
				TrainGraphics locomotive = new TrainGraphics(locoImgFile, 
												trainInfo.getComponentCount() + 1, loco.toString());
				trainInfo.add(locomotive);
				shuntIndexInput.addItem(trainInfo.getComponentCount()); // update shuntIndex options
				trainPower = loco.power();
				this.pack();
				DriverButtonEnable(true);
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			trainWeightLabel = new JLabel("Train Weight: " + trainWeight + "(t)");
			trainWeightLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			trainLayout.putConstraint(SpringLayout.NORTH, trainWeightLabel, 10, SpringLayout.NORTH, train);
			trainLayout.putConstraint(SpringLayout.WEST, trainWeightLabel, 10, SpringLayout.WEST, train);
			train.add(trainWeightLabel);
			
			trainPowerLabel = new JLabel("Pulling Power: " + trainPower +"(t)");
			trainPowerLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			trainLayout.putConstraint(SpringLayout.NORTH, trainPowerLabel, 5, SpringLayout.SOUTH, trainWeightLabel);
			trainLayout.putConstraint(SpringLayout.WEST, trainPowerLabel, 10, SpringLayout.WEST, train);
			train.add(trainPowerLabel);
			
			trainMoveLabel = new JLabel("Enough Power?: " + departingTrain.trainCanMove());
			trainMoveLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			trainLayout.putConstraint(SpringLayout.NORTH, trainMoveLabel, 5, SpringLayout.SOUTH, trainPowerLabel);
			trainLayout.putConstraint(SpringLayout.WEST, trainMoveLabel, 10, SpringLayout.WEST, train);
			train.add(trainMoveLabel);
			
			trainCapacityLabel = new JLabel("Train Capacity: " + departingTrain.numberOnBoard() + "/" + departingTrain.numberOfSeats());
			trainCapacityLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			trainLayout.putConstraint(SpringLayout.NORTH, trainCapacityLabel, 10, SpringLayout.NORTH, train);
			trainLayout.putConstraint(SpringLayout.EAST, trainCapacityLabel, -10, SpringLayout.EAST, train);
			train.add(trainCapacityLabel);
			
			trainLeftBehindLabel = new JLabel("Passengers Left Behind: 0");
			trainLeftBehindLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			trainLayout.putConstraint(SpringLayout.NORTH, trainLeftBehindLabel, 5, SpringLayout.SOUTH, trainCapacityLabel);
			trainLayout.putConstraint(SpringLayout.EAST, trainLeftBehindLabel, -10, SpringLayout.EAST, train);
			train.add(trainLeftBehindLabel);

			break;
		case "Freight Car":
			passengerCarSelect.setSelected(false);
			SetFreightVisible(true);
			SetPassengerVisible(false);
			break;
		case "Passenger Car":
			freightCarSelect.setSelected(false);
			SetFreightVisible(false);
			SetPassengerVisible(true);
			break;
		case "Add Passenger Car":
			Integer grossWeightPassenger = Integer.parseInt(grossWeightPassengerInput.getText());
			Integer numberOfSeats = Integer.parseInt(numberOfSeatsInput.getText());
			try {
				// construct and add carriage panel
				PassengerCar passengerCar = new PassengerCar(grossWeightPassenger, numberOfSeats);
				departingTrain.addCarriage(passengerCar);
				File passengerImgFile = new File("rsc/passenger.jpg");
				TrainGraphics passengerCarriage = new TrainGraphics(passengerImgFile, 
												trainInfo.getComponentCount() + 1, passengerCar.toString());
				trainInfo.add(passengerCarriage);
				shuntIndexInput.addItem(trainInfo.getComponentCount()); // update shuntIndex options
				ButtonDisableAddCarriage(true);
				this.pack();
				trainWeight += passengerCar.getGrossWeight();
				trainWeightLabel.setText("Train Weight: " + trainWeight + "(t)");
				trainPowerLabel.setText("Pulling Power: " + trainPower +"(t)");
				trainMoveLabel.setText("Enough Power?: " + departingTrain.trainCanMove());
				trainCapacityLabel.setText("Train Capacity: " + departingTrain.numberOnBoard() + "/" + departingTrain.numberOfSeats());
				trainLeftBehindLabel.setText("Passengers Left Behind: 0");
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			break;
		case "Add Freight Car":
			Integer grossWeightFreight = Integer.parseInt(grossWeightFreightInput.getText());
			String goodsType = goodsTypeInput.getSelectedItem().toString().substring(0,1);
			
			try {
				FreightCar freightCar = new FreightCar(grossWeightFreight, goodsType);
				departingTrain.addCarriage(freightCar);
				File freightImgFile = new File("rsc/freight.jpg");
				TrainGraphics freightCarriage = new TrainGraphics(freightImgFile, 
						trainInfo.getComponentCount() + 1, freightCar.toString());
				ButtonDisableAddCarriage(true);
				trainInfo.add(freightCarriage);
				shuntIndexInput.addItem(trainInfo.getComponentCount()); // update shuntIndex options
				trainWeight += freightCar.getGrossWeight();
				trainWeightLabel.setText("Train Weight: " + trainWeight + "(t)");
				trainPowerLabel.setText("Pulling Power: " + trainPower +"(t)");
				trainMoveLabel.setText("Enough Power?: " + departingTrain.trainCanMove());
				trainCapacityLabel.setText("Train Capacity: " + departingTrain.numberOnBoard() + "/" + departingTrain.numberOfSeats());
				trainLeftBehindLabel.setText("Passengers Left Behind: 0");
				this.pack();
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			break;
		case "Shunt Train Now":	
			shuntInfo.setVisible(true);
			spurCarriages = new ArrayList<RollingStock>();
			spurCarriagePanels = new ArrayList<Component>();
			// Get number of RollingStock carriages to iterate through
			shuntNumber = shuntIndexInput.getSelectedIndex();
			int carriagesToRemove = 0;
			
			// ACTUAL TRAIN: Remove carriages from train and add to spur track
			for (int i = 0; i <= shuntNumber; i++) {
				departingTrain.nextCarriage();
			}
			
			for (int i = shuntNumber + 1; i < trainInfo.getComponentCount(); i++) {
				spurCarriages.add(departingTrain.nextCarriage());
				carriagesToRemove++;
			}
			
			for (int i = 0; i < carriagesToRemove; i++) {
				try {
					departingTrain.removeCarriage();
				} catch (TrainException trainException) {
					JOptionPane.showMessageDialog(null, trainException);
				}
			}
			shuntTrainButton.setEnabled(false);
			joinTrainButton.setEnabled(true);
			
			// VISUAL REPRESENTATION OF TRAIN: Remove from train and add to spur track
			for (int i = trainInfo.getComponentCount() - 1; i > shuntNumber; i-- ) {
				spurCarriagePanels.add(trainInfo.getComponent(i));
				trainInfo.remove(i);
			}
			
			for (int i = spurCarriagePanels.size() - 1; i >= 0; i--) {
				shuntInfo.add(spurCarriagePanels.get(i));
			}
			
			shuntInfo.repaint();
			trainInfo.repaint();
		}
		
	}
	
	/**
	 * Sets visibility for the fields required to add a FreightCar 
	 * @author JoshuaWright(n6366066)
	 * @param visible (boolean) are these items visible?
	 */
	private void SetFreightVisible(boolean visible){
		addFreightButton.setVisible(visible);
		goodsTypeInput.setVisible(visible);
		goodsTypeLabel.setVisible(visible);
		grossWeightFreightInput.setVisible(visible);
		grossWeightFreightLabel.setVisible(visible);
	}
	
	/**
	 * Sets visibility for the fields required to add a PassengerCar 
	 * @author JoshuaWright(n6366066)
	 * @param visible (boolean) are these items visible?
	 */
	private void SetPassengerVisible(boolean visible){
		addPassengerButton.setVisible(visible);
		numberOfSeatsInput.setVisible(visible);
		numberOfSeatsLabel.setVisible(visible);
		grossWeightPassengerInput.setVisible(visible);
		grossWeightPassengerLabel.setVisible(visible);
	}
	
	private void DriverButtonEnable(boolean enabled){
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
	 * Sets visibility and enabled state of panes and buttons if all
	 * carriages have been removed but train has not been cancelled
	 * @param enabled - represents desired state of button
	 */
	private void ButtonDisableNoCarriages(boolean enabled) {
		shuntTrainButton.setEnabled(enabled);
		joinTrainButton.setEnabled(enabled);
		addCarriageButton.setEnabled(enabled);
		removeCarriageButton.setEnabled(enabled);
		boardPassengersButton.setEnabled(enabled);
		alightPassengersButton.setEnabled(enabled);
	}
	
	private void ButtonDisableAddCarriage(boolean enabled) {
		shuntTrainButton.setEnabled(enabled);
		addCarriageButton.setEnabled(enabled);
		removeCarriageButton.setEnabled(enabled);
		boardPassengersButton.setEnabled(enabled);
	}
}
