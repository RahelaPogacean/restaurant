package businessLayer;

import java.sql.Date;
import java.time.LocalDateTime;

public class Order {
	
	private int orderID;
	private LocalDateTime d;
	private int tableNo;
	
	public Order(int orderID, LocalDateTime d, int tableNo) {
		
		this.orderID = orderID;
		this.d = d;
		this.tableNo = tableNo;
	}
	
	public Order() {
		
		
	}
	
	public String toString() {
		String s="";
		s += "Comanda nr  " + orderID + "  ,de la masa " + tableNo + " ,  momentul " + d + "\n";
		return s;
	}

	public int hashCode() {
		return (int) (orderID + tableNo + d.now());
	}
	
	public boolean equals(Object obj) {
		
		
		//compar obiectul obj cu obiectul curent: referinte, apartin aceleiasi clase si fiecare atribut
		
		if(obj == this) {
			return true;
		}
		
		if(obj ==  null)
			return false;
		
		if(obj.getClass()== this.getClass()) {
			Order order = (Order) obj;
			
			if(this.orderID == order.getOrderID() && this.d == order.getD() && this.tableNo == order.getTableNo()) {
				
				return true;
			}
		}	
		return false;
		
		
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public LocalDateTime getD() {
		return d;
	}

	public void setD(LocalDateTime d) {
		this.d = d;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	

}
