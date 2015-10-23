import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.PrintWriter;
import java.io.IOException;

/*
 * ayyyy lmao
 */

public class GenerateData
{
	static class Vehicle
	{
		// Table 1: Vehicles
		String VIN;
		String make;
		String model;
		String type;
		int year;
		String color;
		String used;
		int miles;
		int KBBValue;
		int rentalPrice;
		int MPG;
		String SellerID;

		// Table 2: Options
		String leatherSeats;
		String FourWheelDrive;
		String fogLights;
		String DVDPlayer;
		String SurroundSound;

		public Vehicle(String make, String model, String type)
		{
			this.VIN = generateVIN();
			this.make = make;
			this.model = model;
			this.type = type;
			this.year = generateYear();
			this.color = generateColor();
			this.used = generateUsed();
			this.miles = generateMiles();
			this.KBBValue = generateKBB();
			this.rentalPrice = generateRentalPrice();
			this.MPG = generateMPG();
			this.SellerID = generateSellerID();

			this.leatherSeats = generateLeatherSeats();
			this.FourWheelDrive = generateFourWheelDrive();
			this.fogLights = generateFogLights();
			this.DVDPlayer = generateDVD();
			this.SurroundSound = generateSurroundSound();
		}

		private String generateVIN()
		{
			String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random r = new Random();
			String ret = "";

			for (int i=0; i<17; i++)
			{
				char cee = alphabet.charAt(r.nextInt(alphabet.length()));
				ret = ret + cee;
			}

			if (uniqueVINs.contains(ret))
				return generateVIN();

			uniqueVINs.add(ret);

			return ret;
		}

		private int generateYear()
		{
			return ThreadLocalRandom.current().nextInt(1990, 2017);
		}

		private String generateColor()
		{
			String[] colors = {"Blue", "Black", "Red", "White", "Gray", "Green"};
			Random r = new Random();
			return colors[r.nextInt(colors.length)];
		}

		private String generateUsed()
		{
			Random r = new Random();

			if (this.year >= 2016)
				return "New";

			if (this.year < 2012)
				return "Used";

			int usedFactor = r.nextInt(10);
			if (usedFactor > 6)
				return "New";
			return "Used";
		}

		private int generateMiles()
		{
			if (this.used.equals("New"))
			return ThreadLocalRandom.current().nextInt(0, 30);
			return ThreadLocalRandom.current().nextInt(3000, 150000);
		}

		private int generateKBB()
		{
			int l = 0, h = 0;
			if      (this.make.equals("Acura"))
				{ l = 30000; h = 60000; }
			else if (this.make.equals("Audi"))
				{ l = 35000; h = 100000; }
			else if (this.make.equals("BMW"))
				{ l = 40000; h = 120000; }
			else if (this.make.equals("Buick"))
				{ l = 20000; h = 40000; }
			else if (this.make.equals("Cadillac"))
				{ l = 30000; h = 60000; }
			else if (this.make.equals("Chevrolet"))
				{ l = 20000; h = 40000; }
			else if (this.make.equals("Chrysler"))
				{ l = 15000; h = 30000; }
			else if (this.make.equals("Dodge"))
				{ l = 20000; h = 35000; }
			else if (this.make.equals("Ford"))
				{ l = 15000; h = 40000; }
			else if (this.make.equals("GM"))
				{ l = 15000; h = 40000; }
			else if (this.make.equals("Honda"))
				{ l = 20000; h = 40000; }
			else if (this.make.equals("Hyundai"))
				{ l = 20000; h = 45000; }
			else if (this.make.equals("Infiniti"))
				{ l = 35000; h = 70000; }
			else if (this.make.equals("Jeep"))
				{ l = 20000; h = 45000; }
			else if (this.make.equals("Kia"))
				{ l = 20000; h = 50000; }
			else if (this.make.equals("Lexus"))
				{ l = 40000; h = 80000; }
			else if (this.make.equals("Mazda"))
				{ l = 20000; h = 40000; }
			else if (this.make.equals("Mercedes"))
				{ l = 50000; h = 110000; }
			else if (this.make.equals("Mini"))
				{ l = 35000; h = 70000; }
			else if (this.make.equals("Mitsubishi"))
				{ l = 15000; h = 40000; }
			else if (this.make.equals("Nissan"))
				{ l = 20000; h = 40000; }
			else if (this.make.equals("Porsche"))
				{ l = 50000; h = 150000; }
			else if (this.make.equals("Subaru"))
				{ l = 20000; h = 50000; }
			else if (this.make.equals("Tesla"))
				{ l = 70000; h = 150000; }
			else if (this.make.equals("Toyota"))
				{ l = 20000; h = 50000; }
			else if (this.make.equals("Volkswagen"))
				{ l = 25000; h = 60000; }
			else if (this.make.equals("Volvo"))
				{ l = 20000; h = 50000; }

			if (h == 0)
				System.out.println("ERROR: Car make not found in generateKBB()");

			if (this.used.equals("Used"))
			{
				double yearsFactor = 1 - ((2017 - this.year) / 30.000);
				yearsFactor = 1 - ((1 - yearsFactor) / 1.1);

				double milesFactor = (this.miles - 100000) / 100000.00;

				if (milesFactor < 0)
					milesFactor=Math.abs(milesFactor)+1;

				if (milesFactor > 1)
					milesFactor = 1 + ((milesFactor - 1) / 5);

				if (milesFactor < 1)
					milesFactor = 1 - ((1 - milesFactor) / 3);

				double usedFactor = yearsFactor * milesFactor;

				l = (int)(usedFactor * l);
				h = (int)(usedFactor * h);
			}
			return ThreadLocalRandom.current().nextInt(l, h);
		}

		private int generateRentalPrice()
		{
			return ThreadLocalRandom.current().nextInt(30, 200);
		}

		private int generateMPG()
		{
			int l = 0, h = 0;
			if      (this.type.equals("Convertible"))
				{ l = 10; h = 30; }
			else if (this.type.equals("Coupe"))
				{ l = 20; h = 45; }
			else if (this.type.equals("Electric"))
				{ l = 90; h = 200; }
			else if (this.type.equals("Hatchback"))
				{ l = 30; h = 60; }
			else if (this.type.equals("Hybrid"))
				{ l = 30; h = 60; }
			else if (this.type.equals("Luxury"))
				{ l = 15; h = 40; }
			else if (this.type.equals("Sedan"))
				{ l = 20; h = 45; }
			else if (this.type.equals("Sports"))
				{ l = 10; h = 25; }
			else if (this.type.equals("SUV"))
				{ l = 10; h = 30; }
			else if (this.type.equals("Truck"))
				{ l = 07; h = 20; }
			else if (this.type.equals("Van"))
				{ l = 20; h = 30; }

			if (h == 0)
				System.out.println("ERROR: Car type not found in generateMPG()");

			double yearsFactor = 1 - ((2017 - this.year) / 30.000);
			yearsFactor = 1 - ((1 - yearsFactor) / 2);
			l = (int)(yearsFactor * l);
			return ThreadLocalRandom.current().nextInt(l, h);
		}

		private String generateSellerID()
		{
			String id = null;

			if (this.type.equals("New"))
				id = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 1100));
			else id = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 1500));

			if (id == null)
				System.out.println("ERROR: Some shit went wrong in generateSellerID()");

            if (!(SellerIDs.contains(id)))
                SellerIDs.add(id);

			return id;
		}

		private String generateLeatherSeats()
		{
			if (this.type.equals("Luxury"))
				return "Yes";

			Random r = new Random();
			return yesNo[r.nextInt(yesNo.length)];
		}

		private String generateFourWheelDrive()
		{
			if (this.type.equals("Truck") || this.type.equals("SUV"))
				return "Yes";

			Random r = new Random();
			return yesNo[r.nextInt(yesNo.length)];
		}

		private String generateFogLights()
		{
			Random r = new Random();
			return yesNo[r.nextInt(yesNo.length)];
		}

		private String generateDVD()
		{
			Random r = new Random();
			return yesNo[r.nextInt(yesNo.length)];
		}

		private String generateSurroundSound()
		{
			Random r = new Random();
			return yesNo[r.nextInt(yesNo.length)];
		}
	}

	private static void populateVehicles()
	{
		for (int i=0; i<vehicles.length; i++)
		{
			Random r = new Random();
			MMTPacket currMMT = mmtPackets.get(r.nextInt(mmtPackets.size()));
			vehicles[i] = new Vehicle(currMMT.make, currMMT.model, currMMT.type);
		}
	}

	static class Seller
	{
		String sellerID;
		String sellerType;
		String neighborhood;
		String street;
		String city;
		String state;
		String ZIP;
		double latitude;
		double longitude;

		public Seller(String id)
		{
			this.sellerID = id;
			this.sellerType = generateType();
			this.neighborhood = generateNeighborhood();
			this.street = generateStreet();
			this.city = generateCity();
			this.state = generateState();
			this.ZIP = generateZIP();
			this.latitude = generateLatitude();
			this.longitude = generateLongitude();
		}

		private String generateType()
		{
			int a = ThreadLocalRandom.current().nextInt(0, 2);
			if (a == 1)
				return "Dealership";
			return "Private";
		}

		private String generateNeighborhood()
		{
			return null;
		}

		private String generateStreet()
		{
			return null;
		}

		private String generateCity()
		{
			return null;
		}

		private String generateState()
		{
			return null;
		}

		private String generateZIP()
		{
			return null;
		}

		private double generateLatitude()
		{
			double low = 40.7142700;
			double high = 40.730644;
			return ThreadLocalRandom.current().nextDouble(low, high);
		}

		private double generateLongitude()
		{
			double low = 73.9525929;
			double high = 74.0030869;
			return -ThreadLocalRandom.current().nextDouble(low, high);
		}
	}

	private static void populateSellers()
	{
		for (String id : SellerIDs)
		{
			Seller s = new Seller(id);
			sellers.add(s);
		}
	}

	static class MMTPacket
	{
		String make;
		String type;
		String model;

		public MMTPacket(String make, String model, String type)
		{
			this.make = make;
			this.type = type;
			this.model = model;
		}
	}

	private static void addPackets()
	{
		mmtPackets.add(new MMTPacket("Honda", "Accord", "Coupe"));
		mmtPackets.add(new MMTPacket("Honda", "Accord", "Sedan"));
		mmtPackets.add(new MMTPacket("Honda", "Civic", "Coupe"));
		mmtPackets.add(new MMTPacket("Honda", "Civic", "Sedan"));
		mmtPackets.add(new MMTPacket("Toyota", "Camry", "Coupe"));
		mmtPackets.add(new MMTPacket("Toyota", "Camry", "Sedan"));
		mmtPackets.add(new MMTPacket("Toyota", "Corolla", "Coupe"));
		mmtPackets.add(new MMTPacket("Toyota", "Corolla", "Sedan"));
		mmtPackets.add(new MMTPacket("Nissan", "Altima", "Coupe"));
		mmtPackets.add(new MMTPacket("Nissan", "Altima", "Sedan"));
		mmtPackets.add(new MMTPacket("Nissan", "Maxima", "Coupe"));
		mmtPackets.add(new MMTPacket("Nissan", "Maxima", "Sedan"));
		mmtPackets.add(new MMTPacket("Tesla", "Model S", "Electric"));
		mmtPackets.add(new MMTPacket("Volkswagen", "Jetta", "Sedan"));
		mmtPackets.add(new MMTPacket("Volkswagen", "Passat", "Sedan"));
		mmtPackets.add(new MMTPacket("Volkswagen", "GTI", "Hatchback"));
		mmtPackets.add(new MMTPacket("BMW", "335i", "Luxury"));
		mmtPackets.add(new MMTPacket("BMW", "M3", "Sports"));
		mmtPackets.add(new MMTPacket("Ford", "Explorer", "SUV"));
		mmtPackets.add(new MMTPacket("Ford", "F150", "Truck"));
	}

	private static void printVehicles()
	{
		System.out.println("--------------------------------------------------------------Table 1 - Vehicles-------------------------------------------------------------");
		System.out.printf("%3s %17s%12s%12s%12s%12s%12s%12s%12s%12s%12s%12s%12s\n", "#", "VIN", "Make", "Model", "Type", "Year", "Color", "Miles", "Used/New", "SellerID", "KBB", "RentalPrice", "MPG");

		int i=0;
		for (Vehicle v : vehicles)
		{
			i++;
			// "#", "VIN", "Make", "Model", "Type", "Year", "Color", "Miles", "Used", "SellerID", "KBB", "MPG"
			System.out.printf("%03d %17s%12s%12s%12s%12d%12s%12d%12s%12s%12d%12d%12d\n", i, v.VIN, v.make, v.model, v.type, v.year, v.color, v.miles, v.used, v.SellerID, v.KBBValue, v.rentalPrice, v.MPG);
		}
	}

	private static void printVehiclesCSV() throws IOException
	{
		PrintWriter writer = new PrintWriter("vehicles.csv", "UTF-8");

		writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "VIN", "Make", "Model", "Type", "Year", "Color", "Miles", "Used/New", "SellerID", "KBB", "Daily Rental Price", "MPG");

		for (Vehicle v : vehicles)
		{
			// "VIN", "Make", "Model", "Type", "Year", "Color", "Miles", "Used", "SellerID", "KBB", "MPG"
			writer.printf("%s,%s,%s,%s,%d,%s,%d,%s,%s,%d,%d,%d\n", v.VIN, v.make, v.model, v.type, v.year, v.color, v.miles, v.used, v.SellerID, v.KBBValue, v.rentalPrice, v.MPG);
		}

		writer.close();
	}

	private static void printOptions()
	{
		System.out.println("--------------------------------------------------------------Table 2 - Options--------------------------------------------------------------");
		System.out.printf("%3s %17s%12s%12s%12s%12s%12s\n", "#", "VIN", "Leather", "4WD", "Fog Lights", "DVD Player", "Surround");

		int i=0;
		for (Vehicle v : vehicles)
		{
			i++;
			// "#", "VIN", "LeatherSeats", "4WD", "Fog Lights", "DVD Player", "Surround"
			System.out.printf("%03d %17s%12s%12s%12s%12s%12s\n", i, v.VIN, v.leatherSeats, v.FourWheelDrive, v.fogLights, v.DVDPlayer, v.SurroundSound);
		}
	}

	private static void printOptionsCSV() throws IOException
	{
		PrintWriter writer = new PrintWriter("options.csv", "UTF-8");

		writer.printf("%s,%s,%s,%s,%s,%s\n", "VIN", "Leather", "4WD", "Fog Lights", "DVD Player", "Surround");

		for (Vehicle v : vehicles)
		{
			// "VIN", "LeatherSeats", "4WD", "Fog Lights", "DVD Player", "Surround"
			writer.printf("%s,%s,%s,%s,%s,%s\n", v.VIN, v.leatherSeats, v.FourWheelDrive, v.fogLights, v.DVDPlayer, v.SurroundSound);
		}

		writer.close();
	}

	private static void printSellers()
	{
		System.out.println("--------------------------------------------------------------Table 3 - Sellers--------------------------------------------------------------");
		System.out.printf("%3s %17s%12s%12s%12s%12s%12s%12s%12s%12s\n", "#", "SellerID", "Type", "Neighborhood", "Street", "City", "State", "ZIP Code", "Lat", "Long");

		int i=0;
		for (Seller s : sellers)
		{
			i++;
			// "#", "SellerID", "Type", "Neighborhood", "Street", "City", "State", "ZIP Code", "Brand"
			System.out.printf("%03d %17s%12s%12s%12s%12s%12s%12s%12f%12f\n", i, s.sellerID, s.sellerType, s.neighborhood, s.street, s.city, s.state, s.ZIP, s.latitude, s.longitude);
		}
	}

	private static void printSellersCSV() throws IOException
	{
		PrintWriter writer = new PrintWriter("sellers.csv", "UTF-8");

		writer.printf("%s,%s,%s,%s\n", "SellerID", "Type", "Latitude", "Longitude");

		for (Seller s : sellers)
		{
			// "SellerID", "Type", "Latitude", "Longitude"
			writer.printf("%s,%s,%f,%f\n", s.sellerID, s.sellerType, s.latitude, s.longitude);
		}

		writer.close();
	}

	static ArrayList<String> uniqueVINs = new ArrayList<String>();
	static ArrayList<String> SellerIDs = new ArrayList<String>();
	static ArrayList<Seller> sellers = new ArrayList<Seller>();
	static ArrayList<MMTPacket> mmtPackets = new ArrayList<MMTPacket>();
	static Vehicle[] vehicles = new Vehicle[1000];
	static String[] yesNo = {"Yes", "No"};

	public static void main(String[] args)
	{
		addPackets();
		populateVehicles();
		populateSellers();

		printVehicles();
		System.out.println();
		printOptions();
		System.out.println();
		printSellers();

		try
		{
			printVehiclesCSV();
			printOptionsCSV();
			printSellersCSV();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}