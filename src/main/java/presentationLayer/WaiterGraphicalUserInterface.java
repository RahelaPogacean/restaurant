package presentationLayer;


import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import businessLayer.*;
import businessLayer.MenuItem;


	public class WaiterGraphicalUserInterface{
		
		public static JFrame frame, anotherFr;
		public   JButton comanda, bill, back, reset ;
		public JTextField textField_1, textField_2;
		public JScrollPane scrollPane, scroll2;
		private MainFrame mw;
		private WaiterGraphicalUserInterface wt;
		public static JTable tabel;
		public static DefaultTableModel model;
		public JTextArea qq;
		Restaurant restau;
		
		public WaiterGraphicalUserInterface() {
			
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
		
		public static ArrayList<MenuItem> generateTable(JTable table, ArrayList<MenuItem> list) {
			
			DefaultTableModel model = new DefaultTableModel();
			ArrayList<String> column = new ArrayList<String>();
			
			column.add("ID");
			column.add("Name");
			column.add("Price");
			model.setColumnIdentifiers(column.toArray());
		
			int id[]= table.getSelectedRows();
			ArrayList<MenuItem> comp= new ArrayList<MenuItem>();
			
			for(int it : id) {
				System.out.println(it);
				comp.add(list.get(it));
			}
			return comp;
			
		}
		
		public void initComponents() {
			
			frame=new JFrame();
			frame.setSize(new Dimension(1500, 900));
			
			JLabel id = new JLabel("Create order: ");
			id.setBounds(40, 50, 500, 50);
			id.setFont(new Font("Times New Roman", Font.ITALIC, 25));
			
			JLabel n = new JLabel("Id table: ");
			n.setBounds(45, 120, 500, 40);
			n.setFont(new Font("Times New Roman", Font.ITALIC, 25));
			
			textField_1 = new JTextField(20);
			textField_1.setBounds(190, 60, 270, 30);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField(20);
			textField_2.setBounds(190, 125, 270, 30);
			textField_2.setColumns(10);
			
			comanda = new JButton("Create Order");
			comanda.setBackground(Color.LIGHT_GRAY);
			comanda.setForeground(Color.RED);
			comanda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			comanda.setBounds(120, 190, 200, 30);

			bill = new JButton("Generate Bill");
			bill.setBackground(Color.LIGHT_GRAY);
			bill.setForeground(Color.RED);
			bill.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			bill.setBounds(120, 240, 200, 30);

			back = new JButton("Back");
			back.setBackground(Color.LIGHT_GRAY);
			back.setForeground(Color.RED);
			back.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			back.setBounds(120, 290, 200, 30);
			
			reset = new JButton("Reset");
			reset.setBackground(Color.LIGHT_GRAY);
			reset.setForeground(Color.RED);
			reset.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			reset.setBounds(120, 340, 200, 30);
		
			tabel = makeTable(Restaurant.getMenu());
			
			qq=new JTextArea();//orders
			Font font=new Font("Serif", Font.PLAIN, 15);
			qq.setBackground(new Color(220,20,60));
			qq.setFont(font);
			qq.setForeground(Color.WHITE);
			qq.setBounds(30, 450, 430, 350);
			
			scroll2 =  new JScrollPane();
			scroll2.setBounds(30, 450, 430, 350);
			scroll2.setViewportView(qq);
			scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(500, 80, 900, 680);
			tabel.setBounds(70, 70, 700, 680);
			tabel.setVisible(true);
		
			scrollPane.setViewportView(tabel);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBounds(26,105, 300, 315);
			panel.setBackground(new Color(255,228,196));
			
			panel.add(id);
			panel.add(n);
			panel.add(textField_1);
			panel.add(textField_2);
			panel.add(comanda);
			panel.add(bill);
			panel.add(back);
			panel.add(reset);
			panel.add(scrollPane);
			panel.add(scroll2);
			
			
			scrollPane.revalidate();
			 frame.add(panel);	
			 frame.setVisible(true);
			 frame.addWindowListener(new WindowAdapter(){
				 
				 	public void windowClosing(WindowEvent e) {
				 		Restaurant.update();
				 	}
				 });
			 
			 
				comanda.addActionListener(new ActionListener(){
		  			public void actionPerformed(ActionEvent e){
		  				
		  				ArrayList<MenuItem> ll= new ArrayList<MenuItem>();
		  		
		  				ll=generateTable(tabel, Restaurant.getMenu());
		  				int id=Integer.parseInt(textField_1.getText());
		  				int nrTable=Integer.parseInt(textField_2.getText());
		  				LocalDateTime date = LocalDateTime.now();
		  				Order ord= new Order(id, date, nrTable);
		  				System.out.println("produsul comandat: "+ ll.toString());
		  				
		  				restau.createOrder(ord, id, nrTable, ll);
		  				
		  				tabel = makeTable(Restaurant.getMenu());
						scrollPane.setViewportView(tabel);
						scrollPane.setBounds(500, 80, 900, 680);
						tabel.setBounds(70, 70, 700, 680);
						String s = ord.toString();
						String a = " continand produsele "  + Restaurant.printList(ll) + " \na fost inregistrata!\n";
						s+=a;
						qq.setText(s);
						
		  			 }
		          });

			 
			 bill.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent et) {
						
						ArrayList<MenuItem> ll= new ArrayList<MenuItem>();
				  		
		  				ll=generateTable(tabel, Restaurant.getMenu());
		  				int id=Integer.parseInt(textField_1.getText());
		  				int nrTable=Integer.parseInt(textField_2.getText());
		  				LocalDateTime date = LocalDateTime.now();
		  				Order ord= new Order(id, date, nrTable);
		  				
							try {
								qq.setText(Restaurant.generateBill(id));
								
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
		  					//qq.setText("" + Restaurant.computePrice(id));
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
						frame.setVisible(true);
					}
				});
			 
		}
	
	}
	
