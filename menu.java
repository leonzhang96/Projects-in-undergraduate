//Name: Zhang Hang
//ID# 111359459
//Section: CSE214-03

package extraH1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class menu {

	static List<Order> processing = new ArrayList<>();

	static List<Order> shipped = new ArrayList<>();

	static Map<String, ArrayList<String>> storage = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] menu = { "O) Order", "E) Edit order", "P) Print order", "R) Remove oder", "S) Ship order", "Q) Quit" };

		readFile(menu, sc);

	}

	private static void readFile(String[] menu, Scanner sc) {
		try {
			System.out.println("Please enter the name of the .txt file: ");
			String fileAddress = sc.nextLine();
			File file = new File(fileAddress);
			String content = FileUtils.readFileToString(file, "UTF-8");
			buildMenuWithItem(content);
			System.out.println("Welcome, here is Menu\n");
			printMenu();
			process(menu, sc);

		} catch (Exception ex) {
			System.out.println("Incorrect File! Please try again.");
			readFile(menu, sc);
		}
	}

	private static void process(String[] menu, Scanner sc) {
		boolean continued = false;
		while (true) {
			System.out.println("What is next?");

			for (int i = 0; i < menu.length; i++) {
				System.out.println(menu[i]);
			}
			System.out.println("Enter you selection: ");
			String select = sc.nextLine().toUpperCase();
			int orderNumber = 0;

			switch (select) {
			case "O":

				orderNumber = processing.size();
				System.out.println("Enter name: ");
				String name = sc.nextLine();
				System.out.println("Enter phone number: ");
				String phone = sc.nextLine();
				Order order = new Order(orderNumber++, name, phone);
				selectItemFromCategory(order);
				processing.add(order);

				break;
			case "E":
				System.out.println("Enter Name:");
				String nameOfOrder = sc.nextLine();
				Order x = null;
				if (processing.size() > 0) {
					for (Order tm : processing) {
						if (tm.getName().equals(nameOfOrder)) {
							x = tm;
							System.out.println("You want add or remove?");
							String Tempchoice = sc.nextLine();
							String choice = Tempchoice.substring(0, 1).toUpperCase()
									+ Tempchoice.substring(1).toLowerCase();

							switch (choice) {
							case "Add":
								// select catagory and list from each catagory
								selectItemFromCategory(x);

								break;
							case "Remove":
								removeItemToOrder(x, sc);
								break;
							default:
								System.out.println("Select again!");
								break;
							}
						}
					}
				} else {
					System.out.println("There is no order to edit!");
				}

				break;
			case "P":
				printOrder();
				break;
			case "R":
				System.out.println("Enter Name:");
				String removeName = sc.nextLine();
				// Order removeOrder = null ;
				for (Order tm : processing) {
					if (tm.getName().equals(removeName)) {

						processing.remove(tm);
						break;
						// removeItemToOrder(removeOrder,sc);
					}
				}

				break;
			case "S":
				System.out.println("Enter Order number to ship:");
				String shipName = sc.nextLine();
				Order shipOrder = null;
				for (Order tmShip : processing) {
					if (tmShip.getName().equals(shipName)) {
						shipOrder = tmShip;
						removeItemToOrder(shipOrder, sc);
						shipped.add(shipOrder);
					}
				}

				break;
			case "Q":// Final option: quit the programm
				System.out.println("Sorry to see you go. Have a good day!");
				System.exit(0);
			}

		}

	}

	private static void printOrder() {
		if (processing.size() > 0) {
			System.out.println("Order in processing list: ");
			for (int i = 0; i < processing.size(); i++) {
				System.out.println("name in order is " + processing.get(i).getName());
				printEachItemFromMenu(processing.get(i).getStuff());
			}
		} else {
			System.out.println("There is no order in processing list");
		}
		if (shipped.size() > 0) {
			System.out.println("Order in shipped list: ");
			for (int i = 0; i < shipped.size(); i++) {
				System.out.println(shipped.get(i).getName());
				printEachItemFromMenu(shipped.get(i).getStuff());
			}
		} else {
			System.out.println("There is no order in shipped list");
		}

	}

	private static void removeItemToOrder(Order order, Scanner onsc) {
		System.out.println("Which item you want to remove?");
		String removeItem = onsc.nextLine();
		if (order.getStuff().contains(removeItem)) {
			order.getStuff().remove(removeItem);
		} else {
			System.out.println("Don't have this item");
		}

	}

	private static void selectItemFromCategory(Order order) {
		Scanner in = new Scanner(System.in);
		System.out.println("Which Category you want to go?");
		String category = in.nextLine();
		String tmp = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();

		// if it contain that key print value
		switch (tmp) {
		case "Meat":
			ArrayList<String> iMeat = storage.get(tmp);
			printEachItemFromMenu(iMeat);
			addItemToOrder(order, iMeat, in);
			break;
		case "Drink":
			ArrayList<String> iDrink = storage.get(tmp);
			printEachItemFromMenu(iDrink);
			addItemToOrder(order, iDrink, in);

			break;
		case "Fruit":
			ArrayList<String> iFruit = storage.get(tmp);
			printEachItemFromMenu(iFruit);
			addItemToOrder(order, iFruit, in);

			break;

		case "Vegatable":
			ArrayList<String> iVegatable = storage.get(tmp);
			printEachItemFromMenu(iVegatable);
			addItemToOrder(order, iVegatable, in);

			break;
		default:
			System.out.println("Do not have this, will coming soon!");
			break;
		}

	}

	private static void addItemToOrder(Order order, ArrayList<String> items, Scanner in) {
		System.out.println("Enter item you want?");
		String itemtmp = in.nextLine();
		String item = itemtmp.substring(0, 1).toUpperCase() + itemtmp.substring(1).toLowerCase();

		if (items.contains(item)) {
			order.getStuff().add(item);
			System.out.println(item + " is successly add to order!");
		} else {
			System.out.println("There is no such item");
			addItemToOrder(order, items, in);
		}

	}

	private static void printEachItemFromMenu(ArrayList<String> it) {
		System.out.println("List contain: ");
		for (int i = 0; i < it.size(); i++) {
			System.out.println(it.get(i));
		}

	}

	private static void buildMenuWithItem(String fileContext) {
		String[] outFromFile = fileContext.split("\\r?\\n");

		for (int i = 0; i < outFromFile.length; i++) {
			String[] list = outFromFile[i].split(" ");
			String CatName = list[0];
			ArrayList<String> it = new ArrayList<>();
			for (int x = 1; x < list.length; x++) {
				it.add(list[x]);
			}
			storage.put(CatName, it);
		}
	}

	private static void printMenu() {
		for (String name : storage.keySet()) {
			String key = name.toString();
			ArrayList<String> value = storage.get(name);
			System.out.println("Catagory is: " + key);
			printEachItemFromMenu(value);
			System.out.println("\n");
		}
	}

}
