package asgn2GUI;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * @author RD
 * Constructs a JPanel with carriage image, carriage description label and index label
 */
public class TrainGraphics extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6492318735479543468L;
	private BufferedImage image;
	private JLabel imageLabel;
	private JLabel indexLabel;
	private JLabel stringLabel;

	public TrainGraphics(File imageFile, int index, String label) {
		super();
		try {                
			this.image = ImageIO.read(imageFile);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(null,"Image File Not Found: " + imageFile); 
	    }
		imageLabel = new JLabel(new ImageIcon( image ));
		indexLabel = new JLabel("Carriage: " + String.valueOf(index));
		stringLabel = new JLabel(label);
		initPanel();
	}
	
	public void setStringLabel(String labelText) {
		this.stringLabel.setText(labelText);
	}
	
	public void setIndexLabel(int indexNumber) {
		this.indexLabel.setText("Carriage: " + String.valueOf(indexNumber));
	}
	
	public void initPanel() {
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		indexLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		stringLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(128, 140)); // currently does nothing with this layout
		this.add(indexLabel);
		this.add(imageLabel);
		this.add(stringLabel);
	}
	
}
