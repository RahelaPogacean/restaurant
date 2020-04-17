package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import businessLayer.*;
import businessLayer.MenuItem;

public class AdministratorGraphicalUserInterface {

	public static JFrame frame, anotherFr;
	private MainFrame mw;
	public JButton createBase, create, delete, editt, back, reset;
	public JTextField textField_1, textField_2, textField_3;
	public JScrollPane scrollPane;
	public JTable tabel;
	public static DefaultTableModel model;
	Restaurant restau;

	public AdministratorGraphicalUserInterface() {

		restau = new Restaurant();
		initComponents();

	}

	public static JTable makeTable(ArrayList<MenuItem> list) {

		ArrayList<String> column = new ArrayList<String>();
		column.add("ID");
		column.add("Name");
		column.add("Price");

		// DefaultTableModel
		model = new DefaultTableModel();
		model.setColumnIdentifiers(column.toArray());

		Object[] v = new Object[3];
		JTable table = new JTable();

		for (MenuItem mm : list) {
			v[0] = mm.getId();
			v[1] = mm.getName();
			v[2] = mm.getPrice();
			model.addRow(v);
		}
		table.setModel(model);

		JTableHeader header = table.getTableHeader();
		TableColumnModel col = header.getColumnModel();
		for (int i = 0; i < column.size(); i++) {
			TableColumn tc = col.getColumn(i);
			tc.setHeaderValue(column.get(i));
		}

		Font font = new Font("Times New Roman", 1, 22);
		table.setFont(font);
		table.setRowHeight(40);

		for (int i = 1; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setMinWidth(150);

		return table;

	}

	public static ArrayList<MenuItem> composite(JTable table, ArrayList<MenuItem> list) {

		DefaultTableModel model = new DefaultTableModel();
		ArrayList<String> column = new ArrayList<String>();

		column.add("ID");
		column.add("Name");
		column.add("Price");
		model.setColumnIdentifiers(column.toArray());

		int id[] = table.getSelectedRows();
		ArrayList<MenuItem> comp = new ArrayList<MenuItem>();

		for (int it : id) {
			System.out.println(it);
			comp.add(list.get(it));
		}
		return comp;

	}

	public void initComponents() {

		frame = new JFrame();
		frame.setSize(new Dimension(1500, 900));

		JLabel id = new JLabel("ID: ");
		id.setBounds(45, 50, 500, 40);
		id.setFont(new Font("Times New Roman", Font.ITALIC, 25));

		JLabel n = new JLabel("NAME: ");
		n.setBounds(45, 120, 500, 40);
		n.setFont(new Font("Times New Roman", Font.ITALIC, 25));

		JLabel e = new JLabel("PRICE: ");
		e.setBounds(45, 190, 500, 40);
		e.setFont(new Font("Times New Roman", Font.ITALIC, 25));

		textField_1 = new JTextField(20);
		textField_1.setBounds(140, 55, 270, 30);
		textField_1.setColumns(10);

		textField_2 = new JTextField(20);
		textField_2.setBounds(140, 125, 270, 30);
		textField_2.setColumns(10);

		textField_3 = new JTextField(20);
		textField_3.setBounds(140, 195, 270, 30);
		textField_3.setColumns(10);

		createBase = new JButton("Create Base Item");
		createBase.setBackground(Color.LIGHT_GRAY);
		createBase.setForeground(Color.RED);
		createBase.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		createBase.setBounds(90, 350, 200, 30);
		
		create = new JButton("Create Composite Item");
		create.setBackground(Color.LIGHT_GRAY);
		create.setForeground(Color.RED);
		create.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		create.setBounds(90, 400, 200, 30);

		delete = new JButton("Delete Item");
		delete.setBackground(Color.LIGHT_GRAY);
		delete.setForeground(Color.RED);
		delete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		delete.setBounds(90, 450, 200, 30);

		editt = new JButton("Edit Item");
		editt.setBackground(Color.LIGHT_GRAY);
		editt.setForeground(Color.RED);
		editt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		editt.setBounds(90, 500, 200, 30);

		back = new JButton("Back");
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.RED);
		back.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		back.setBounds(90, 550, 200, 30);

		reset = new JButton("Reset");
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setForeground(Color.RED);
		reset.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		reset.setBounds(90, 600, 200, 30);

		
		tabel = makeTable(Restaurant.getMenu());

		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 80, 900, 680);
		tabel.setBounds(70, 70, 700, 680);
		tabel.setVisible(true);

		scrollPane.setViewportView(tabel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(26, 105, 300, 315);
		panel.setBackground(new Color(255, 228, 196));

		panel.add(id);
		panel.add(n);
		panel.add(e);
		panel.add(textField_1);
		panel.add(textField_2);
		panel.add(textField_3);
		panel.add(createBase);
		panel.add(create);
		panel.add(delete);
		panel.add(editt);
		panel.add(back);
		panel.add(reset);
		panel.add(scrollPane);

		scrollPane.revalidate();
		frame.add(panel);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				Restaurant.update();
			}
		});
		
		createBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {

				String n = textField_2.getText();
				int id = Integer.parseInt(textField_1.getText());
				double p = Double.parseDouble(textField_3.getText());

				ArrayList<MenuItem> ll = composite(tabel, Restaurant.getMenu());// lista de produse selectate
				MenuItem newProd = new MenuItem(n, id, p);
				
				Restaurant.createBaseItem(n, id, p);
				
				tabel = makeTable(Restaurant.getMenu());
				
				scrollPane.setViewportView(tabel);
				tabel.revalidate();
				scrollPane.setBounds(500, 80, 900, 680);
				tabel.setBounds(70, 70, 700, 680);

			}
		});

		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {

				String n = textField_2.getText();
				int id = Integer.parseInt(textField_1.getText());
				double p = Double.parseDouble(textField_3.getText());

				ArrayList<MenuItem> ll = composite(tabel, Restaurant.getMenu());// lista de produse selectate
				MenuItem newProd = new MenuItem(n, id, p);
				
				Restaurant.createCompositeItem(n, id, p, ll);
				
				System.out.println("comp =");
				Restaurant.printList(ll);
				
				tabel = makeTable(Restaurant.getMenu());
				
				scrollPane.setViewportView(tabel);
				tabel.revalidate();
				scrollPane.setBounds(500, 80, 900, 680);
				tabel.setBounds(70, 70, 700, 680);

			}
		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {

				int id = Integer.parseInt(textField_1.getText());
				Restaurant.deleteMenuItem(id);
				Restaurant.listItems();
				tabel = makeTable(Restaurant.getMenu());
				scrollPane.setViewportView(tabel);

			}
		});

		editt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {

				String n = textField_2.getText();
				int id = Integer.parseInt(textField_1.getText());
				double p = Double.parseDouble(textField_3.getText());

				Restaurant.editMenuItem(id, n, p);
				Restaurant.listItems();
				tabel = makeTable(Restaurant.getMenu());
				scrollPane.setViewportView(tabel);
			}
		});

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent et) {

				try {
					mw.main(null);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				frame.setVisible(false);

			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {

				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");

				frame.setVisible(true);
			}
		});

	}

}
