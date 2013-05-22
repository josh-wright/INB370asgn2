package asgn2GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

import asgn2Exceptions.*;
import asgn2RollingStock.*;
import asgn2Train.*;

/**
 * 
 * @author Joshua Wright (n6366066)
 * @author Robert Dempsey (n5400872)
 */
public class DepartingTrainGUImkII extends JFrame implements ActionListener {
	
	/* CONSTANTS ************************************************ */
	private static final long serialVersionUID = 4687087442125954015L;
	private static final Font TITLE_MAIN_FONT = new Font("Verdana", Font.BOLD, 20);
	private static final Font TITLE_SUB_FONT = new Font("Verdana", Font.BOLD, 14);
	private static final Font INFO_FONT = new Font("Verdana", Font.BOLD, 12);
	private static final Dimension BUTTON_SIZE = new Dimension(200,30);
	/* ********************************************************** */

	/* Required Global Buttons ********************************** */
	
	/* ********************************************************** */
	
	/* Required Global Panels *********************************** */
	private JPanel beginTrain;
	private JPanel addCarriage;
	private JPanel shuntControl;
	/* ********************************************************** */
	
	/* ********************************************************** *
	 * ********************************************************** *
	 * ********************************************************** */
	
	/**
	 * Main Program Function Call
	 * @author Joshua Wright (n6366066)
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DepartingTrainGUImkII frame = new DepartingTrainGUImkII(
											 "Train Controls Interface"
											  );
					frame.BuildInterface(frame.getContentPane());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(getScreenSize());
					frame.pack();
					frame.setVisible(true);
					frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initiate new Departing Train Window
	 * @author Joshua Wright (n6366066)
	 * @param name (String) name of the new window
	 */
	private DepartingTrainGUImkII(String name) {
		super(name);
		setResizable(true);
	}
	
	/**
	 * Gets the current maximum screen resolution of
	 * machine running the application
	 * @author Joshua Wright (n6366066)
	 * @return (Dimension) current screen resolution
	 */
	static private Dimension getScreenSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	private void BuildInterface(final Container trainControl){
		trainControl.setLayout(new GridLayout(2,0));
		trainControl.add(BuildTrain());
		trainControl.add(BuildControls());
	}
	
	/**
	 * Builds Train Panel which contains TrainInfo and
	 * SpurInfo as well as live train information
	 * @author Joshua Wright (n6366066)
	 * @return
	 */
	private JPanel BuildTrain(){
		JPanel train = new JPanel();
		SpringLayout trainLayout = new SpringLayout();
		train.setLayout(trainLayout);
		
		JLabel trainTitle = new JLabel("Train Information");
		trainTitle.setFont(TITLE_MAIN_FONT);
		trainLayout.putConstraint(SpringLayout.NORTH, trainTitle, 20, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trainTitle, 0, SpringLayout.HORIZONTAL_CENTER, train);
		train.add(trainTitle);
		
		JLabel trainWeightLabel = new JLabel("Train Weight: No Train Started");
		trainWeightLabel.setFont(INFO_FONT);
		trainLayout.putConstraint(SpringLayout.NORTH, trainWeightLabel, 10, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainWeightLabel, 10, SpringLayout.WEST, train);
		train.add(trainWeightLabel);
		
		JLabel trainPowerLabel = new JLabel("Pulling Power: No Train Started");
		trainPowerLabel.setFont(INFO_FONT);
		trainLayout.putConstraint(SpringLayout.NORTH, trainPowerLabel, 5, SpringLayout.SOUTH, trainWeightLabel);
		trainLayout.putConstraint(SpringLayout.WEST, trainPowerLabel, 10, SpringLayout.WEST, train);
		train.add(trainPowerLabel);
		
		JLabel trainMoveLabel = new JLabel("Enough Power?: No Train Started");
		trainMoveLabel.setFont(INFO_FONT);
		trainLayout.putConstraint(SpringLayout.NORTH, trainMoveLabel, 5, SpringLayout.SOUTH, trainPowerLabel);
		trainLayout.putConstraint(SpringLayout.WEST, trainMoveLabel, 10, SpringLayout.WEST, train);
		train.add(trainMoveLabel);
		
		JLabel trainCapacityLabel = new JLabel("Train Capacity: No Train Started");
		trainCapacityLabel.setFont(INFO_FONT);
		trainLayout.putConstraint(SpringLayout.NORTH, trainCapacityLabel, 10, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.EAST, trainCapacityLabel, -10, SpringLayout.EAST, train);
		train.add(trainCapacityLabel);
		
		JLabel trainLeftBehindLabel = new JLabel("Passengers Left Behind: No Train Started");
		trainLeftBehindLabel.setFont(INFO_FONT);
		trainLayout.putConstraint(SpringLayout.NORTH, trainLeftBehindLabel, 5, SpringLayout.SOUTH, trainCapacityLabel);
		trainLayout.putConstraint(SpringLayout.EAST, trainLeftBehindLabel, -10, SpringLayout.EAST, train);
		train.add(trainLeftBehindLabel);
		
		JPanel trainInfo = BuildTrainInfo();
		train.add(trainInfo);
		trainLayout.putConstraint(SpringLayout.NORTH, trainInfo, 10, SpringLayout.SOUTH, trainMoveLabel);
		trainLayout.putConstraint(SpringLayout.EAST, trainInfo, -10, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, trainInfo, -250, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainInfo, 10, SpringLayout.WEST, train);
		
		JPanel spurInfo = BuildSpurInfo();
		train.add(spurInfo);
		trainLayout.putConstraint(SpringLayout.NORTH, spurInfo, 10, SpringLayout.SOUTH, trainInfo);
		trainLayout.putConstraint(SpringLayout.EAST, spurInfo, -10, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, spurInfo, -10, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, spurInfo, 10, SpringLayout.WEST, train);
		
		return train;
	}
	
	/**
	 * Builds TrainInfo panel
	 * @author Joshua Wright (n6366066)
	 * @return (JPanel) completed TrainInfo Panel
	 */
	private JPanel BuildTrainInfo() {
		JPanel trainInfo = new JPanel();
		trainInfo.setBackground(Color.white);
		trainInfo.setLayout(new GridLayout(1,20));
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		trainInfo.setBorder(loweredbevel);
		
		return trainInfo;
	}
	
	/**
	 * Builds SpurInfo panel
	 * @author Joshua Wright (n6366066)
	 * @return (JPanel) completed SpurInfo Panel
	 */
	private JPanel BuildSpurInfo() {
		JPanel spurInfo = new JPanel();
		spurInfo.setBackground(Color.white);
		spurInfo.setLayout(new GridLayout(1,20));
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		spurInfo.setBorder(loweredbevel);
		
		return spurInfo;
	}
	
	private JPanel BuildControls() {
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(0,2));
		
		controls.add(BuildDriverControls());
		controls.add(BuildConductorControls());
		return controls;
	}
	
	/**
	 * Constructs the Driver Control Panel
	 * @author Joshua Wright (n6366066)
	 * @return (JPanel) constructed Driver Panel
	 */
	private JPanel BuildDriverControls(){
		JPanel driver = new JPanel();
		SpringLayout layout = new SpringLayout();
		
		driver.setLayout(layout);
				
		JLabel driverTitle = new JLabel("Driver Controls");
		driverTitle.setFont(TITLE_MAIN_FONT);
		layout.putConstraint(SpringLayout.NORTH, driverTitle, 20, SpringLayout.NORTH, driver);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, driverTitle, 0, SpringLayout.HORIZONTAL_CENTER, driver);
		driver.add(driverTitle);
		
		Component[] targetButton = {null, driver};
		
		JButton newTrainButton = new JButton("Begin Train");
		newTrainButton.setPreferredSize(BUTTON_SIZE);
		targetButton[0] = driverTitle;
		layout = PositionDriverButtons(layout, 20, 10, newTrainButton, targetButton);
		newTrainButton.addActionListener(this);
		newTrainButton.setEnabled(true);
		driver.add(newTrainButton);
		
		JButton resetTrainButton = new JButton("Cancel Train");
		resetTrainButton.setPreferredSize(BUTTON_SIZE);
		targetButton[0] = newTrainButton;
		layout = PositionDriverButtons(layout, 0, 10, resetTrainButton, targetButton);
		resetTrainButton.addActionListener(this);
		resetTrainButton.setEnabled(false);
		driver.add(resetTrainButton);
		
		JButton shuntTrainButton = new JButton("Shunt Train");
		shuntTrainButton.setPreferredSize(BUTTON_SIZE);
		layout.putConstraint(SpringLayout.NORTH, shuntTrainButton, 0, SpringLayout.SOUTH, resetTrainButton);
		layout.putConstraint(SpringLayout.WEST, shuntTrainButton, 10, SpringLayout.WEST, driver);
		targetButton[0] = resetTrainButton;
		layout = PositionDriverButtons(layout, 0, 10, shuntTrainButton, targetButton);
		shuntTrainButton.addActionListener(this);
		shuntTrainButton.setEnabled(false);
		driver.add(shuntTrainButton);
		
		JButton joinTrainButton = new JButton ("Connect Shunted Train");
		joinTrainButton.setPreferredSize(BUTTON_SIZE);
		targetButton[0] = shuntTrainButton;
		layout = PositionDriverButtons(layout, 0, 10, joinTrainButton, targetButton);
		joinTrainButton.addActionListener(this);
		joinTrainButton.setEnabled(false);
		driver.add(joinTrainButton);
		
		JButton addCarriageButton = new JButton ("Add New Carriage");
		addCarriageButton.setPreferredSize(BUTTON_SIZE);
		targetButton[0] = joinTrainButton;
		layout = PositionDriverButtons(layout, 0, 10, addCarriageButton, targetButton);
		addCarriageButton.addActionListener(this);
		addCarriageButton.setEnabled(false);
		driver.add(addCarriageButton);
		
		JButton removeCarriageButton = new JButton ("Remove Last Carriage");
		removeCarriageButton.setPreferredSize(BUTTON_SIZE);
		targetButton[0] = addCarriageButton;
		layout = PositionDriverButtons(layout, 0, 10, removeCarriageButton, targetButton);
		removeCarriageButton.addActionListener(this);
		removeCarriageButton.setEnabled(false);
		driver.add(removeCarriageButton);
		
		JButton trainDepart = new JButton ("Depart");
		trainDepart.setPreferredSize(BUTTON_SIZE);
		targetButton[0] = removeCarriageButton;
		layout = PositionDriverButtons(layout, 0, 10, trainDepart, targetButton);
		trainDepart.addActionListener(this);
		trainDepart.setEnabled(false);
		driver.add(trainDepart);
		
		Component[] targetPanel = {driverTitle, newTrainButton, driver};
		
		beginTrain = BuildAddLocomotiveControl(); 
		driver.add(beginTrain);
		layout = PositionDriverPanels(layout, beginTrain, targetPanel);
		
		JPanel panel = BuildAddCarriageControl(); 
		driver.add(panel);
		layout = PositionDriverPanels(layout, panel, targetPanel);
		
		panel = BuildShuntControl(); 
		driver.add(panel);
		layout = PositionDriverPanels(layout, panel, targetPanel);
		
		return driver;
	}
	
	private JPanel BuildAddLocomotiveControl() {
		beginTrain = new JPanel();
		SpringLayout layout = new SpringLayout();
		beginTrain.setBackground(Color.red);
		beginTrain.setLayout(layout);
		beginTrain.setVisible(false);
		
		return beginTrain;
	}
	
	private JPanel BuildAddCarriageControl() {
		addCarriage = new JPanel();
		SpringLayout layout = new SpringLayout();
		addCarriage.setLayout(layout);
		addCarriage.setVisible(false);
		
		return addCarriage;
	}
	
	private JPanel BuildShuntControl() {
		shuntControl = new JPanel();
		SpringLayout layout = new SpringLayout();
		shuntControl.setLayout(layout);
		shuntControl.setVisible(false);
		
		return shuntControl;
	}
	
	private SpringLayout PositionDriverPanels(SpringLayout layout, JPanel panel, Component[] targets){
		final Integer NORTH = 20, 
					  EAST = -10, 
					  SOUTH = -10, 
					  WEST = 10;
		
		layout.putConstraint(SpringLayout.NORTH, 
							 panel, 
							 NORTH, 
							 SpringLayout.SOUTH, 
							 targets[0]);
		layout.putConstraint(SpringLayout.WEST, 
							 panel, WEST, 
							 SpringLayout.EAST, 
							 targets[1]);
		layout.putConstraint(SpringLayout.EAST, 
				             panel, EAST, 
				             SpringLayout.EAST, 
				             targets[2]);
		layout.putConstraint(SpringLayout.SOUTH, 
				             panel, 
				             SOUTH, 
				             SpringLayout.SOUTH, 
				             targets[2]);
		return layout;
	}
	
	/**
	 * Puts Location constraints into provided SpringLayout
	 * for the specified button and padding
	 * @author Joshua Wright (n6366066)
	 * @param layout (SpringLayout) layout to alter
	 * @param padNorth (int) North Padding
	 * @param padWest (int) WestPadding
	 * @param button (JButton) Button to Place within Form
	 * @param targets (Components[]) Array of Components for NORTH & WEST
	 * @return (SpringLayout) updated SpringLayout
	 */
	private SpringLayout PositionDriverButtons(SpringLayout layout, Integer padNorth, Integer padWest, JButton button, Component[] targets){
		layout.putConstraint(SpringLayout.NORTH, button, padNorth, SpringLayout.SOUTH, targets[0]);
		layout.putConstraint(SpringLayout.WEST, button, padWest, SpringLayout.WEST, targets[1]);
		return layout;
	}
	
	/**
	 * Constructs the Conductor Control Panel
	 * @author Joshua Wright (n6366066)
	 * @return (JPanel) constructed Conductor Panel
	 */
	private JPanel BuildConductorControls(){
		JPanel conductor = new JPanel();
		return conductor;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		switch(ae.getActionCommand()){
		case "Begin Train": {
			BeginTrainButton();
			break;
		}			
		default:
			JOptionPane.showMessageDialog(null, "Button Not Set");
			break;
		}
	}
	
	private void BeginTrainButton(){
		beginTrain.setEnabled(true);
	}
			
}