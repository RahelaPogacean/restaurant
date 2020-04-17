package businessLayer;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class MenuItem implements Serializable {

	private String name;
	private int id;
	private double price;
	
	public static  ArrayList<MenuItem> mitems;
	
	public MenuItem(String name, int id, double price) {
		
		this.name=name;
		this.id=id;
		this.price=price;
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static ArrayList<MenuItem> getMitems() {
		return mitems;
	}

	public void setCompProd(ArrayList<MenuItem> mitems) {
		this.mitems = mitems;
	}
	
	public String toString() {
		
		String s = "";
		return s + name  + ", id: "	+ id + ", pret:  " + price + ", ";
		}
	
}

