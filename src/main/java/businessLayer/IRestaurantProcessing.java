package businessLayer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface IRestaurantProcessing{

	public void createMenuItem(MenuItem mitem);
	
	public static void createBaseItem(String n, int id, double p);
	public static void createCompositeItem(String n, int id, double p, ArrayList<MenuItem> list);
	public static void deleteMenuItem(int idd);
	public static void editMenuItem(int idd, String newName, double newPrice);
	public void createOrder(Order order, int idd, int nrTable, ArrayList<MenuItem> list);

	public static double computePrice(int idd);
	public static String generateBill(int idd) throws FileNotFoundException, UnsupportedEncodingException;
	
	
}
