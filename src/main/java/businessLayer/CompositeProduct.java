package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

	private String name;
	private int id;
	private double price;
	public ArrayList<MenuItem> comp= new ArrayList<MenuItem>();

	
	public CompositeProduct(String name, int id, double price, ArrayList<MenuItem> comp) {
		
		super(name, id, price);
		this.comp = comp;
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

	public ArrayList<MenuItem> getComp() {
		return comp;
	}


	public void setComp(ArrayList<MenuItem> comp) {
		this.comp = comp;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
