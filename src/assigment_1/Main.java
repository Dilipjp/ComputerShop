// Assignment: computerstore
// © DILIP
// Written by: DILIP (2393565)
package assigment_1;

import java.util.Scanner;

public class Main {

	private static final String PASSWORD = "password";
	private static final int MAX_PASSWORD_TRIES = 3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Creating the Computer object

//		Computer c1 = new Computer();
//		Computer c2 = new Computer("HP", "2040", 1111111112, 1040.90);
//		Computer c3 = new Computer(c1);
//		
//		System.out.println(c1);
//		System.out.println(c2);
//		System.out.println(c3);
//		
//		// get number of computers
//		System.out.println(Computer.findNumberOfCreatedComputers());

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Your Computer Store!");

		System.out.print("Enter the maximum number of computers your store can contain: ");

		int maxComputers;

		while (true) {

			// Check if the input is a valid integer
			if (scanner.hasNextInt()) {
				maxComputers = scanner.nextInt();

				// Check if the input is positive
				if (maxComputers > 0) {
					break;
				} else {
					System.out.print("Invalid input. Please enter a valid number:");
				}
			} else {
				System.out.print("Invalid input. Please enter a valid number:");
				// Clear the invalid input from the scanner
				scanner.next();
			}
		}

		System.out.println(maxComputers);

//		scanner.close();

		Computer[] inventory = new Computer[maxComputers];

		int choice;
		do {
			displayMainMenu();
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (authenticateUser(scanner)) {
					addComputers(inventory, scanner);
				} else {
					System.out.println("Incorrect password. Redirecting to the main menu.");
				}
				break;
			case 2:
				if (authenticateUser(scanner)) {
					changeComputerInfo(inventory, scanner);
				} else {
					System.out.println("Incorrect password. Redirecting to the main menu.");
				}
				break;
			case 3:
				displayComputersByBrand(inventory, scanner);
				break;
			case 4:
				displayComputersUnderPrice(inventory, scanner);
				break;
			case 5:
				System.out.println("Exiting the program. Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		} while (choice != 5);

		scanner.close();
	}

	public static boolean authenticateUser(Scanner scanner) {
		int attempts = 0;

		while (attempts < MAX_PASSWORD_TRIES) {
			System.out.print("Enter the password: ");
			String inputPassword = scanner.nextLine();

			if (inputPassword.equals(PASSWORD)) {
				return true;
			} else {
				attempts++;
				System.out
						.println("Incorrect password. You have " + (MAX_PASSWORD_TRIES - attempts) + " attempts left.");
			}
		}

		return false;
	}

	public static void displayMainMenu() {
		System.out.println("\nMain Menu");
		System.out.println("1. Enter new Computers(Password required)");
		System.out.println("2. Change information of a computer(Password required)");
		System.out.println("3. Display all computers by a specific brand");
		System.out.println("4. Display all computers under certain price");
		System.out.println("5. Quit");
		System.out.print("Please enter your choice: ");
	}

	public static void addComputers(Computer[] inventory, Scanner scanner) {
		System.out.print("How many computers do you want to enter? ");
		int numComputersToAdd = scanner.nextInt();
		scanner.nextLine();

		int remainingSpace = inventory.length - Computer.findNumberOfCreatedComputers();

		if (numComputersToAdd <= remainingSpace) {
			for (int i = 0; i < numComputersToAdd; i++) {
				System.out.println("Enter details for Computer #" + (i + 1) + ":");
				System.out.print("Brand: ");
				String brand = scanner.nextLine();
				System.out.print("Model: ");
				String model = scanner.nextLine();
				System.out.print("Serial Number: ");
				long serialNumber = scanner.nextLong();
				System.out.print("Price: ");
				double price = scanner.nextDouble();
				scanner.nextLine();

				Computer computer = new Computer(brand, model, serialNumber, price);

				int nextAvailableIndex = Computer.findNumberOfCreatedComputers();
				inventory[nextAvailableIndex - 1] = computer;

			}
			System.out.println("Computers added successfully.");
		} else {
			System.out.println("There's only space for " + remainingSpace + " computers. Cannot add "
					+ numComputersToAdd + " computers.");
		}
	}

	public static void changeComputerInfo(Computer[] inventory, Scanner scanner) {

		System.out.print("Enter the computer number you wish to update: ");
		int computerNumber = scanner.nextInt();
		scanner.nextLine();

		if (computerNumber < 0 || computerNumber >= inventory.length || inventory[computerNumber] == null) {
			System.out.println("No computer found at index " + computerNumber);
			System.out.print("Do you wish to enter another computer (yes/no)? ");
			String continueOption = scanner.nextLine();
			if (continueOption.equalsIgnoreCase("yes")) {
				changeComputerInfo(inventory, scanner);
			} else {
				System.out.println("Redirecting to the main menu.");
			}
			return;
		}

		Computer computer = inventory[computerNumber];

		System.out.println("\nCurrent information of Computer #" + computerNumber + ":");
		System.out.println("Brand: " + computer.getBrand());
		System.out.println("Computer Model: " + computer.getModel());
		System.out.println("SN: " + computer.getSN());
		System.out.println("Computer price: " + computer.getPrice());

		int choice;
		do {
			displayChangeMenu();
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter the new brand: ");
				String newBrand = scanner.nextLine();
				computer.setBrand(newBrand);
				break;
			case 2:
				System.out.print("Enter the new model: ");
				String newModel = scanner.nextLine();
				computer.setModel(newModel);
				break;
			case 3:
				System.out.print("Enter the new SN: ");
				long newSerialNumber = scanner.nextLong();
				computer.setSN(newSerialNumber);
				break;
			case 4:
				System.out.print("Enter the new price: ");
				double newPrice = scanner.nextDouble();
				computer.setPrice(newPrice);
				break;
			case 5:
				System.out.println("Redirecting to the main menu.");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}

			if (choice >= 1 && choice <= 4) {
				System.out.println("\nUpdated information of Computer #" + computerNumber + ":");
				System.out.println("Brand: " + computer.getBrand());
				System.out.println("Model: " + computer.getModel());
				System.out.println("SN: " + computer.getSN());
				System.out.println("Price: " + computer.getPrice());
			}
		} while (choice != 5);

	}

	public static void displayChangeMenu() {
		System.out.println("\nWhat information would you like to change?");
		System.out.println("1. Brand");
		System.out.println("2. Model");
		System.out.println("3. SN");
		System.out.println("4. Price");
		System.out.println("5. Quit");
		System.out.print("Enter your choice: ");
	}

	public static void displayComputersByBrand(Computer[] inventory, Scanner scanner) {
		System.out.print("Enter the brand name: ");
		String brandToSearch = scanner.nextLine();

		Computer[] computersByBrand = findComputersByBrand(inventory, brandToSearch);

		if (computersByBrand.length == 0) {
			System.out.println("No computers found with the brand: " + brandToSearch);
		} else {
			System.out.println("\n" + computersByBrand.length + " Computers found with brand " + brandToSearch + ":");
			for (int i = 0; i < computersByBrand.length; i++) {
				Computer computer = computersByBrand[i];
				System.out.println("Computer #" + i);
				System.out.println("Brand: " + computer.getBrand());
				System.out.println("Model: " + computer.getModel());
				System.out.println("SN: " + computer.getSN());
				System.out.println("Price: " + computer.getPrice());
				System.out.println();
			}
		}
	}

	public static Computer[] findComputersByBrand(Computer[] inventory, String brand) {
		int count = 0;
		for (Computer computer : inventory) {
			if (computer != null && computer.getBrand().equalsIgnoreCase(brand)) {
				count++;
			}
		}

		Computer[] computersByBrand = new Computer[count];
		int index = 0;
		for (Computer computer : inventory) {
			if (computer != null && computer.getBrand().equalsIgnoreCase(brand)) {
				computersByBrand[index++] = computer;
			}
		}

		return computersByBrand;
	}

	public static void displayComputersUnderPrice(Computer[] inventory, Scanner scanner) {
		System.out.print("Enter the maximum price: ");
		double maxPrice = scanner.nextDouble();

		Computer[] cheaperComputers = findCheaperThan(inventory, maxPrice);

		if (cheaperComputers.length == 0) {
			System.out.println("No computers found under the price: " + maxPrice);
		} else {
			System.out.println("\n" + cheaperComputers.length + " Computers found under the price " + maxPrice + ":");
			for (int i = 0; i < cheaperComputers.length; i++) {
				Computer computer = cheaperComputers[i];
				System.out.println("Computer #" + i);
				System.out.println("Brand: " + computer.getBrand());
				System.out.println("Model: " + computer.getModel());
				System.out.println("SN: " + computer.getSN());
				System.out.println("Price: " + computer.getPrice());
				System.out.println();
			}
		}
	}

	public static Computer[] findCheaperThan(Computer[] inventory, double maxPrice) {
		int count = 0;
		for (Computer computer : inventory) {
			if (computer != null && computer.getPrice() < maxPrice) {
				count++;
			}
		}

		Computer[] cheaperComputers = new Computer[count];
		int index = 0;
		for (Computer computer : inventory) {
			if (computer != null && computer.getPrice() < maxPrice) {
				cheaperComputers[index++] = computer;
			}
		}

		return cheaperComputers;
	}

}
