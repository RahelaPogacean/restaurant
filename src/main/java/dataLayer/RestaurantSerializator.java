package dataLayer;
import businessLayer.*;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RestaurantSerializator {
	
	public static void serialize(ArrayList<MenuItem> e) {
		try {
			
	         FileOutputStream fileOut = new FileOutputStream("menus.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public static ArrayList<MenuItem> deSerialize() {
		try {
			
	         FileInputStream fileIn = new FileInputStream("menus.txt");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ArrayList<MenuItem> e = (ArrayList<MenuItem>) in.readObject();
	         in.close();
	         fileIn.close();
	         return e;
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	        
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	        
	      }
		return null;
		
	}
	}

