package assigment;

import java.util.Objects;

public class Computer {

	private String brand;
	private String model;
	private long SN;
	private double price;
	private static int numberOfComputers = 0;

	public Computer() {
		System.out.println("Creating Object with fixed values");
		brand = "Apple";
		model = "M1";
		SN = 1111111111;
		price = 1800.50;
		numberOfComputers++;

	}

	public Computer(String bd, String md, long sn, double pr) {
		System.out.println("Creating Object with parameters values");
		brand = bd;
		model = md;
		SN = sn;
		price = pr;
		numberOfComputers++;
	}

	public Computer(Computer cm) {
		System.out.println("Creating Object with copy constructor");
		brand = cm.brand;
		model = cm.model;
		SN = cm.SN;
		price = cm.price;
		numberOfComputers++;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String br) {
		brand = br;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String md) {
		model = md;
	}

	public long getSN() {
		return SN;
	}

	public void setSN(long sN) {
		SN = sN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double pr) {
		price = pr;
	}

	@Override
	public String toString() {
		return "Computer is brand=" + brand + ", model=" + model + ", SN=" + SN + " and price=" + price;
	}

//	public static String findNumberOfCreatedComputers() {
//		return "There are  " + numberOfComputers + " computers created";
//		
//	}
	public static int findNumberOfCreatedComputers() {
		return numberOfComputers;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(model, other.model)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

}
