package presentationLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class ChefGraphicalUserInterface implements Observer {

	private JFrame frame;
	private JPanel panel;
	private JTextPane textPane;
	private MainFrame mw;
	Restaurant restau;

	public ChefGraphicalUserInterface() {

		restau = new Restaurant();
		initComponents();

	}

	public void update(Observable arg0, Object arg1) {

		ArrayList<MenuItem> men = (ArrayList<MenuItem>) arg1;
		textPane = new JTextPane();
		String s = " ";

		for (MenuItem item : Restaurant.getMenu()) {
			if (item instanceof CompositeProduct) {
				s += item.toString() + "\n";
			}
		}
	

		textPane.setText(s);
		panel.add(textPane);
		panel.setBounds(100, 30, 200, 100);
		frame.revalidate();

	}

	public void initComponents() {

		frame = new JFrame();
		frame.setSize(new Dimension(1500, 900));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setBounds(50, 50, 300, 300);
		frame.add(panel);
		frame.setVisible(true);
	}

}
