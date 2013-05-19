package asgn2GUI;

/**
 * @author Joshua
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;

public class testGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5400452591692641009L;
	/* Declare Objects Here */
	final int DEFAULT_PADDING_POS = 10;
	final int DEFAULT_PADDING_NEG = -10;
	GridLayout baseLayout = new GridLayout(2,0);
	SpringLayout trainLayout = new SpringLayout();
	GridLayout trainInfoLayout = new GridLayout(3,20);
	SpringLayout driverLayout = new SpringLayout();
	SpringLayout conductorLayout = new SpringLayout();
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	SpringLayout shuntTrainLayout = new SpringLayout();
	SpringLayout addCarriageLayout = new SpringLayout();
	
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
		
		final JPanel trainControl = new JPanel();
		trainControl.setLayout(baseLayout);
		trainControl.setSize(screenSize);
		this.add(trainControl);
		
		/* - train (Part of trainControl) ----------------------------------------------------------------------------------------------------------------------------- */
		
		JPanel train = new JPanel();
		train.setBackground(Color.RED);
		train.setLayout(trainLayout);
		trainControl.add(train);
				
		JLabel trainTitle = new JLabel("Train Information");
		trainTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		trainLayout.putConstraint(SpringLayout.NORTH, trainTitle, 20, SpringLayout.NORTH, train);
		trainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trainTitle, 0, SpringLayout.HORIZONTAL_CENTER, train);
		train.add(trainTitle);
		
		/* - trainInfo (Part of train) -------------------------------------------------------------------------------------------------------------------------------- */
		
		final JPanel trainInfo = new JPanel();
		trainInfo.setBackground(Color.cyan);
		trainInfo.setLayout(trainInfoLayout);
		trainLayout.putConstraint(SpringLayout.NORTH, trainInfo, 20, SpringLayout.SOUTH, trainTitle);
		trainLayout.putConstraint(SpringLayout.EAST, trainInfo, DEFAULT_PADDING_NEG, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, trainInfo, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainInfo, DEFAULT_PADDING_POS, SpringLayout.WEST, train);
		train.add(trainInfo);
		
		/* - Users (Part of trainControl) ----------------------------------------------------------------------------------------------------------------------------- */
		
		JPanel users = new JPanel();
		users.setLayout(new GridLayout(0,2));
		trainControl.add(users);
		
		/* - Driver (Part of user) ------------------------------------------------------------------------------------------------------------------------------------ */
		
		JPanel driver = new JPanel();
		driver.setBackground(Color.PINK);
		driver.setLayout(driverLayout);
		users.add(driver);
		
		JLabel driverTitle = new JLabel("Driver Controls");
		driverTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		driverLayout.putConstraint(SpringLayout.NORTH, driverTitle, 20, SpringLayout.NORTH, driver);
		driverLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, driverTitle, 0, SpringLayout.HORIZONTAL_CENTER, driver);
		driver.add(driverTitle);
		
		Dimension driverButtonSize = new Dimension(200,30);
		
		JButton newTrainButton = new JButton("Begin Train");
		newTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, newTrainButton, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, newTrainButton, 10, SpringLayout.WEST, driver);
		driver.add(newTrainButton);
		newTrainButton.addActionListener(this);
		
		
		JButton resetTrainButton = new JButton("Cancel Train");
		resetTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, resetTrainButton, 0, SpringLayout.SOUTH, newTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, resetTrainButton, 10, SpringLayout.WEST, driver);
		driver.add(resetTrainButton);

		
		JButton shuntTrainButton = new JButton("Shunt Train");
		shuntTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, shuntTrainButton, 0, SpringLayout.SOUTH, resetTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, shuntTrainButton, 10, SpringLayout.WEST, driver);
		driver.add(shuntTrainButton);

		
		JButton joinTrainButton = new JButton ("Connect Shunted Train");
		joinTrainButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, joinTrainButton, 0, SpringLayout.SOUTH, shuntTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, joinTrainButton, 10, SpringLayout.WEST, driver);
		driver.add(joinTrainButton);

		
		JButton addCarriageButton = new JButton ("Add New Carriage");
		addCarriageButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, addCarriageButton, 0, SpringLayout.SOUTH, joinTrainButton);
		driverLayout.putConstraint(SpringLayout.WEST, addCarriageButton, 10, SpringLayout.WEST, driver);
		driver.add(addCarriageButton);

		
		JButton removeCarriageButton = new JButton ("Remove Selected Carriage");
		removeCarriageButton.setPreferredSize(driverButtonSize);
		driverLayout.putConstraint(SpringLayout.NORTH, removeCarriageButton, 0, SpringLayout.SOUTH, addCarriageButton);
		driverLayout.putConstraint(SpringLayout.WEST, removeCarriageButton, 10, SpringLayout.WEST, driver);
		driver.add(removeCarriageButton);
		
		final JPanel shuntTrain = new JPanel();
		shuntTrain.setLayout(shuntTrainLayout);
		shuntTrain.setBackground(Color.yellow);
		shuntTrain.setVisible(false);
		driverLayout.putConstraint(SpringLayout.NORTH, shuntTrain, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, shuntTrain, DEFAULT_PADDING_POS, SpringLayout.EAST, newTrainButton);
		driverLayout.putConstraint(SpringLayout.EAST, shuntTrain, DEFAULT_PADDING_NEG, SpringLayout.EAST, driver);
		driverLayout.putConstraint(SpringLayout.SOUTH, shuntTrain, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, driver);
		driver.add(shuntTrain);
		
		final JPanel addCarriage = new JPanel();
		addCarriage.setLayout(addCarriageLayout);
		addCarriage.setBackground(Color.magenta);
		addCarriage.setVisible(false);
		driverLayout.putConstraint(SpringLayout.NORTH, addCarriage, 20, SpringLayout.SOUTH, driverTitle);
		driverLayout.putConstraint(SpringLayout.WEST, addCarriage, DEFAULT_PADDING_POS, SpringLayout.EAST, newTrainButton);
		driverLayout.putConstraint(SpringLayout.EAST, addCarriage, DEFAULT_PADDING_NEG, SpringLayout.EAST, driver);
		driverLayout.putConstraint(SpringLayout.SOUTH, addCarriage, DEFAULT_PADDING_NEG, SpringLayout.SOUTH, driver);
		driver.add(addCarriage);		
		
		/* - Conductor (Part of user) --------------------------------------------------------------------------------------------------------------------------------- */
		
		JPanel conductor = new JPanel();
		conductor.setBackground(Color.GREEN);
		conductor.setLayout(conductorLayout);
		users.add(conductor);
		
		JLabel conductorTitle = new JLabel("Conductor Controls");
		conductorTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		conductorLayout.putConstraint(SpringLayout.NORTH, conductorTitle, 20, SpringLayout.NORTH, conductor);
		conductorLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, conductorTitle, 0, SpringLayout.HORIZONTAL_CENTER, conductor);
		conductor.add(conductorTitle);
		
		Dimension conductorButtonSize = new Dimension(200,30);
		
		JButton boardPassengersButton = new JButton("Board Passengers");
		boardPassengersButton.setPreferredSize(conductorButtonSize);
		conductorLayout.putConstraint(SpringLayout.NORTH, boardPassengersButton, 20, SpringLayout.SOUTH, conductorTitle);
		conductorLayout.putConstraint(SpringLayout.WEST, boardPassengersButton, 10, SpringLayout.WEST, conductor);
		conductor.add(boardPassengersButton);
		
		
		JButton alightPassengersButton = new JButton("Alight Passengers");
		alightPassengersButton.setPreferredSize(conductorButtonSize);
		conductorLayout.putConstraint(SpringLayout.NORTH, alightPassengersButton, 0, SpringLayout.SOUTH, boardPassengersButton);
		conductorLayout.putConstraint(SpringLayout.WEST, alightPassengersButton, 10, SpringLayout.WEST, conductor);
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
		// TODO Auto-generated method stub
		
	}

}
