package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class DepartingTrainGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3220004779529322252L;
	private JPanel trainPane;
	private JPanel conductorPane;
	private JPanel driverPane;
	private JLabel driverLabel;
	private JButton newTrainButton;
	private SpringLayout layout = new SpringLayout();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartingTrainGUI frame = new DepartingTrainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @author Robert Dempsey. Student Number: N5400872
	 * Create the frame.
	 */
	public DepartingTrainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		
		
		trainPane = new JPanel();
		trainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		trainPane.setLayout(new BorderLayout(0, 0));
		trainPane.setBackground(new Color(243, 243, 243));
		trainPane.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 2));
		trainPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		getContentPane().add(trainPane,BorderLayout.NORTH);
		
		conductorPane = new JPanel();
		conductorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		conductorPane.setLayout(layout);
		conductorPane.setBackground(Color.LIGHT_GRAY);
		conductorPane.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		conductorPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		getContentPane().add(conductorPane,BorderLayout.WEST);
		
		
		driverPane = new JPanel();
		driverPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		driverPane.setLayout(layout);
		driverPane.setBackground(Color.LIGHT_GRAY);
		driverPane.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		driverPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		getContentPane().add(driverPane,BorderLayout.EAST);

		driverLabel = new JLabel("Driver");
		driverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		driverLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		conductorPane.add(driverLabel, layout);
		
		newTrainButton = new JButton("New Train");
		conductorPane.add(newTrainButton, layout);
		
		layout.putConstraint(SpringLayout.WEST, driverLabel,
                75,
                SpringLayout.WEST, driverPane);
		layout.putConstraint(SpringLayout.NORTH, driverLabel,
                5,
                SpringLayout.NORTH, driverPane);
		
		layout.putConstraint(SpringLayout.WEST, newTrainButton,
                75,
                SpringLayout.WEST, driverPane);
		layout.putConstraint(SpringLayout.NORTH, newTrainButton,
                300,
                SpringLayout.NORTH, driverPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}

}
