package asgn2GUI;

/**
 * @author Joshua
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestGUI {
	/* Declare Objects Here */
	GridLayout baseLayout = new GridLayout(2,0);
	SpringLayout trainLayout = new SpringLayout();
	GridLayout trainInfoLayout = new GridLayout(3,20);
	SpringLayout driverLayout = new SpringLayout();
	SpringLayout conductorLayout = new SpringLayout();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
/*	public TestGUI(String name) {
		super(name);
		setResizable(false);
	}*/
	
	/**
	 * @wbp.parser.entryPoint
	 */
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
		
		JPanel trainInfo = new JPanel();
		trainInfo.setBackground(Color.cyan);
		trainInfo.setLayout(trainInfoLayout);
		trainLayout.putConstraint(SpringLayout.NORTH, trainInfo, 20, SpringLayout.SOUTH, trainTitle);
		trainLayout.putConstraint(SpringLayout.EAST, trainInfo, -5, SpringLayout.EAST, train);
		trainLayout.putConstraint(SpringLayout.SOUTH, trainInfo, -5, SpringLayout.SOUTH, train);
		trainLayout.putConstraint(SpringLayout.WEST, trainInfo, 5, SpringLayout.WEST, train);
		train.add(trainInfo);
		
		/* - Users (Part of trainControl) ----------------------------------------------------------------------------------------------------------------------------- */
		
		JPanel users = new JPanel();
		users.setLayout(new GridLayout(0,2));
		trainControl.add(users);
		
		/* - Driver (Part of user) ------------------------------------------------------------------------------------------------------------------------------------ */
		
		JPanel driver = new JPanel();
		driver.setBackground(Color.PINK);
		driver.setLayout(driverLayout);
		
		JLabel driverTitle = new JLabel("Driver Controls");
		driverTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		driverLayout.putConstraint(SpringLayout.NORTH, driverTitle, 20, SpringLayout.NORTH, driver);
		driverLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, driverTitle, 0, SpringLayout.HORIZONTAL_CENTER, driver);
		driver.add(driverTitle);
		
		users.add(driver);
		
		/* - Conductor (Part of user) --------------------------------------------------------------------------------------------------------------------------------- */
		
		JPanel conductor = new JPanel();
		conductor.setBackground(Color.GREEN);
		conductor.setLayout(conductorLayout);
		
		JLabel conductorTitle = new JLabel("Conductor Controls");
		conductorTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		conductorLayout.putConstraint(SpringLayout.NORTH, conductorTitle, 20, SpringLayout.NORTH, conductor);
		conductorLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, conductorTitle, 0, SpringLayout.HORIZONTAL_CENTER, conductor);
		conductor.add(conductorTitle);
		
		users.add(conductor);
	}
	
}
