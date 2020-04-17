package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainFrame {

	public static JFrame frame;
	public static JButton ad, wt, ch;

	public static void main(String[] args) throws SQLException {
		MainFrame mw = new MainFrame();

	}

	public MainFrame() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1500, 900));

		JLabel o = new JLabel("Choose an option! ");

		o.setBounds(43, 40, 500, 40);
		o.setFont(new Font("Times New Roman", Font.BOLD, 25));

		ad = new JButton("Administrator");
		ad.setBackground(new Color(255 - 255 - 153));
		ad.setForeground(Color.RED);
		ad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		ad.setBounds(42, 110, 200, 40);

		wt = new JButton("Waiter");
		wt.setBackground(new Color(255 - 255 - 153));
		wt.setForeground(Color.RED);
		wt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		wt.setBounds(42, 170, 200, 40);

		ch = new JButton("Chef");
		ch.setBackground(new Color(255 - 255 - 153));
		ch.setForeground(Color.RED);
		ch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		ch.setBounds(42, 230, 200, 40);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(o);
		panel.add(ad);
		panel.add(wt);
		panel.add(ch);
		panel.setBounds(26, 195, 300, 315);
		panel.setBackground(new Color(255 - 255 - 153));
		ImageIcon icon = new ImageIcon("restauB.jpg");
		JLabel img = new JLabel(icon);
		img.add(panel);
		frame.setContentPane(img);
		frame.setVisible(true);

		ad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
	
				new AdministratorGraphicalUserInterface();

			}
		});

		wt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new WaiterGraphicalUserInterface();

			}
		});

		ch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new ChefGraphicalUserInterface();

			}
		});

	}

}
