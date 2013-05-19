package asgn2GUI;

/**
 * @author Joshua
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

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
		train.setBackground(Color.RED);
		train.setLayout(trainLayout);
		trainControl.add(train);
				
		JLabel trainTitle = new JLabel("Train Information");
		trainTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		trainLayout.putConstraint(SpringLayout.NORTH, trainTitle, 20, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trainTitle, 0, SpringLayout.HORIZONTAL_CENTER, train);
		train.add(trainTitle);
		
		/* - trainInfo (Part of train) -------------------------------------------------------------------------------------------------------------------------------- */
		
		trainInfo = new JPanel();
		trainInfo.setBackground(Color.cyan);
		trainInfo.setLayout(trainInfoLayout);
		trainLayout.putConstraint(SpringLayout.NORTH, trainInfo, 20, SpringLayout.SOUTH, trainTitle);
		trainLayout.putConstraint(SpringLayout.EAST, trainInfo, DEFAULT_PADDING_NEG, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, trainInfo, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainInfo, DEFAULT_PADDING_POS, SpringLayout.WEST, train);
		train.add(trainInfo);
		
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

		
		removeCarriageButton = new JButton ("Remove Selected Carriage");
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
		
		
		
		alightPassengersButton = new JButton("Alight Passengers");
		alightPassengersButton.setPreferredSize(conductorButtonSize);
		conductorLayout.putConstraint(SpringLayout.NORTH, alightPassengersButton, 0, SpringLayout.SOUTH, boardPassengersButton);
		conductorLayout.putConstraint(SpringLayout.WEST, alightPassengersButton, 10, SpringLayout.WEST, conductor);
		alightPassengersButton.addActionListener(this);
		alightPassengersButton.setEnabled(false);
		conductor.add(alightPassengersButton);
		
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
			break;
		case "Add New Carriage":
			trainInfo.repaint();
			beginTrain.setVisible(false);
			addCarriage.setVisible(true);
			shuntTrain.setVisible(false);
			
			break;
		case "Remove Selected Carriage":
			beginTrain.setVisible(false);
			addCarriage.setVisible(false);
			shuntTrain.setVisible(false);
			trainInfo.repaint();
			break;
		case "Board Passengers":
			break;
		case "Alight Passengers":
			break;
		case "Add Locomotive":
			Integer grossWeight = Integer.parseInt(grossWeightInput.getText());
			Integer powerClass = powerClassInput.getSelectedIndex() + 1;
			String engineType = engineTypeInput.getSelectedItem().toString().substring(0, 1);
			File locoImgFile = null;
			BufferedImage locoImg = null;
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
				JPanel locomotive = new JPanel();
				try {                
			          locoImg = ImageIO.read(locoImgFile);
			       } catch (IOException ex) {
			          JOptionPane.showMessageDialog(null,"Image File Not Found: " + locoImgFile);
			       }
				JLabel locomotiveLabel = new JLabel(loco.toString());
				JLabel locoImgLabel = new JLabel(new ImageIcon( locoImg ));
				locomotive.add(locoImgLabel);
				locomotive.add(locomotiveLabel);
				trainInfo.add(locomotive);
				this.pack();
				DriverButtonEnable(true);
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
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
				PassengerCar passengerCar = new PassengerCar(grossWeightPassenger, numberOfSeats);
				departingTrain.addCarriage(passengerCar);
				File passengerImgFile = new File("rsc/passenger.jpg");
				BufferedImage passengerImg = null;
				try {                
			          passengerImg = ImageIO.read(passengerImgFile);
			       } catch (IOException ex) {
			          JOptionPane.showMessageDialog(null,"Image File Not Found: " + passengerImgFile);
			       }
				JLabel passengerImgLabel = new JLabel(new ImageIcon( passengerImg ));
				JLabel passengerCarLabel = new JLabel(passengerCar.toString());
				trainInfo.add(passengerImgLabel);
				trainInfo.add(passengerCarLabel);
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
				FreightCar freightCar = new FreightCar(grossWeightFreight, goodsType);
				departingTrain.addCarriage(freightCar);
				File freightImgFile = new File("rsc/freight.jpg");
				BufferedImage freightImg = null;
				try {                
			          freightImg = ImageIO.read(freightImgFile);
			       } catch (IOException ex) {
			          JOptionPane.showMessageDialog(null,"Image File Not Found: " + freightImgFile);
			       }
				JLabel freightImgLabel = new JLabel(new ImageIcon( freightImg ));
				JLabel freightCarLabel = new JLabel(freightCar.toString());
				trainInfo.add(freightImgLabel);
				trainInfo.add(freightCarLabel);
				this.pack();
				trainInfo.repaint();
			} catch (TrainException e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			break;
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
		shuntTrainButton.setEnabled(enabled);
		joinTrainButton.setEnabled(enabled);
		addCarriageButton.setEnabled(enabled);
		removeCarriageButton.setEnabled(enabled);
		boardPassengersButton.setEnabled(enabled);
		alightPassengersButton.setEnabled(enabled);
	}
}
