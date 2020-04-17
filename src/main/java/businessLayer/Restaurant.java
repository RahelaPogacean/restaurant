package businessLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import dataLayer.RestaurantSerializator;

public class Restaurant extends Observable implements IRestaurantProcessing {

	private MenuItem menuItem;
	private Order order;
	private static HashMap<Order, ArrayList<MenuItem>> map;// masa, comanda
	private static ArrayList<MenuItem> menu;
	private static ArrayList<Order> ods;

	public Restaurant() {

		populate();
		map = new HashMap<Order, ArrayList<MenuItem>>();
		ods = new ArrayList<Order>();

		menu = RestaurantSerializator.deSerialize();

		if (menu == null) {
			menu = new ArrayList<MenuItem>();
		}

	}

	public void populate() {

		menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("apa", 1, 2));
		menu.add(new MenuItem("paine", 2, 3));
		menu.add(new MenuItem("lamai", 3, 4));
	}

	// operatii admin

	/**
	 * @pre mitem != null
	 */
	public void createMenuItem(MenuItem mitem) {

		menu.add(mitem);
	}

	/**
	 * @pre n != null && id>0 && p>0
	 */
	public static void createBaseItem(String n, int id, double p) {
		assert n != null && id > 0 && p > 0 : "You can't create this item!";

		menu.add(new MenuItem(n, id, p));
	}

	/**
	 * @pre n != null && id>0 && p>0
	 * @pre list.size()>2
	 */

	public static void createCompositeItem(String n, int id, double p, ArrayList<MenuItem> list) {

		assert n != null && id > 0 && p > 0 : "You can't create this item!";
		assert list.size() > 2 : "You should select at least 2 items for a composite product!";
		System.out.println(list);
		menu.add(new MenuItem(n, id, p));
	}

	public static MenuItem searchId(int idd) {// caut produsul cu id-ul d

		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getId() == idd) {
				return menu.get(i);
			}
		}
		return null;
	}

	/**
	 * @pre idd>0
	 * @pre searchId(idd) != null
	 * @post true
	 */

	public static void deleteMenuItem(int idd) {
		assert idd > 0 : "Choose an appropriate id!";
		assert searchId(idd) != null : "This item doesn't exist!";

		MenuItem product = searchId(idd);// accesez produsul cu id-ul dorit
		menu.remove(product);

	}

	/**
	 * @pre searchId(idd) != null
	 * @pre newName != null && newPrice > 0
	 */

	public static void editMenuItem(int idd, String newName, double newPrice) {
		assert searchId(idd) != null : "You can't update an inexistent product!";
		assert newName != null && newPrice > 0 : "Choose valid names and values for editing!";

		MenuItem product = Restaurant.searchId(idd);// accesez produsul cu id-ul dorit
		product.setName(newName);
		product.setPrice(newPrice);

	}

	public static void listItems() {
		for (int i = 0; i < menu.size(); i++) {
			System.out.println(menu.get(i));
		}
	}

	public static String printList(ArrayList<MenuItem> m) {
		String s = " ";
		for (int i = 0; i < m.size(); i++) {
			s += " " + m.get(i);
		}
		return s;
	}

	// operatii waiter

	/**
	 * @pre order != null && idd>0 ** nrTable > 0
	 * @pre list.size()>0
	 */

	public void obs() {
		setChanged();
		System.out.println("Change status with setChanged :" + hasChanged());
		notifyObservers(menu);
	}

	public void createOrder(Order order, int idd, int nrTable, ArrayList<MenuItem> list) {
		assert order != null && idd > 0 && nrTable > 0 : "Invalid order!";
		assert list.size() > 0 : "You have to select products!";

		order = new Order();
		order.setOrderID(idd);
		order.setTableNo(nrTable);

		/*for (MenuItem m : menu) {
			if (m instanceof CompositeProduct) {
				obs();
			}
		}*/

		ods.add(order);
		map.put(order, list);// adaug in HashMap
	}

	/**
	 * @pre idd != 0
	 * @pre ods.contains(orderr)
	 * @post @nochange
	 */

	public static double computePrice(int idd) {
		double totalSum = 0;
		Order orderr = new Order();
		assert idd != 0 : "Invalid orderId!";
		assert ods.contains(orderr) : "It doesn't exist any order with this id!";

		for (int j = 0; j < ods.size(); j++) {

			if (ods.get(j).getOrderID() == idd) {// am gasit orderul cu id-ul dorit
				orderr = ods.get(j);
				for (MenuItem item : map.get(orderr)) {
					totalSum += item.getPrice();
				}
			}
		}

		return totalSum;
	}

	/**
	 * @pre idd != 0
	 * @pre ods.contains(orderr)
	 * @post @nochange
	 */

	public static String generateBill(int idd) throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter writer = new PrintWriter("bill.txt");

		double totalSum = 0;
		Order orderr = new Order();
		assert idd != 0 : "Invalid orderId!";
		assert ods.contains(orderr) : "It doesn't exist any bill with this id!";
		String bll = " ";
		bll += "Comanda  nr " + idd;

		for (int j = 0; j < ods.size(); j++) {

			if (ods.get(j).getOrderID() == idd) {// am gasit orderul cu id-ul dorit
				orderr = ods.get(j);
				for (MenuItem item : map.get(orderr)) {
					totalSum = computePrice(idd);
				}
			}
		}

		LocalDateTime d = LocalDateTime.now();
		bll += " , data/ora " + d + " \npretul total de " + totalSum + " ";

		writer.println(bll);
		writer.close();

		return bll;
	}

	public static void update() {
		RestaurantSerializator.serialize(menu);
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public HashMap<Order, ArrayList<MenuItem>> getMap() {
		return map;
	}

	public void setMap(HashMap<Order, ArrayList<MenuItem>> map) {
		this.map = map;
	}

	public static ArrayList<MenuItem> getMenu() {
		return menu;
	}

	public static void setMenu(ArrayList<MenuItem> menu) {
		Restaurant.menu = menu;
	}

}
