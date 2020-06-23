//Name: Zhang Hang
//ID# 111359459
//Section: CSE214-03

package extraH1;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String name;
	private int orderNumber;
	private String phone;
	private ArrayList<String> stuff;

	public Order(int initOrderNumber, String initName, String initPhone) {
		this.name = initName;
		this.orderNumber = initOrderNumber;
		this.phone = initPhone;
		stuff = new ArrayList<>();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<String> getStuff() {
		return stuff;
	}

	public void setStuff(ArrayList<String> stuff) {
		this.stuff = stuff;
	}

	public String toString() {
		String it = "";
		String stf = "";
		for (int i = 0; i < this.getStuff().size(); ++i) {
			stf += this.getStuff().get(i) + " ";
		}

		it += "Order Number is:" + this.getOrderNumber() + "\n " + "Name is: " + this.getName() + "\n" + "Phone is: "
				+ this.getPhone() + "Items are:" + stf + "\n";
		return it;
	}

}
