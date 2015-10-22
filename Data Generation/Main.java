import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* TODO
* 10/21 7:42 PM:
* ADD Miles per Gallon dependent on type, ADD KBBValue dependent on year
* ADD additional MMTPacket objects to MMTPackets array
*
* */

public class Main 
{
	static class Vehicle
	{
		// Table 1
        String VIN;
        String make;
        String model;
        String type;
        int year;
        String color;
        String used;
        int miles;
        int KBBValue;
        int MPG;
        String SellerID;

        public Vehicle(String VIN, String make, String model, String type)
        {
            this.VIN = generateVIN();
            this.make = make;
            this.model = model;
            this.type = type;
            this.year = generateYear();
            this.color = generateColor();
            this.used = generateUsed();
            this.miles = generateMiles();
            this.KBBValue = 123456;
            this.MPG = 30;
            this.SellerID = "testID";
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
            
            return ret;
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
        
    }

    private static void printData(Vehicle[] vehicles)
    {
    	System.out.printf("%3s %17s%12s%12s%12s%12s%12s%12s%12s%12s%12s%12s\n", "#", "VIN", "Make", "Model", "Type", "Year", "Color", "Miles", "Used/New", "SellerID", "KBB", "MPG");
    	
    	int i=0;
    	for (Vehicle v : vehicles)
    	{
    		i++;
    		// "VIN", "Make", "Model", "Type", "Year", "Color", "Miles", "Used", "SellerID", "KBB", "MPG"
    		System.out.printf("%03d %17s%12s%12s%12s%12d%12s%12d%12s%12s%12d%12d\n", i, v.VIN, v.make, v.model, v.type, v.year, v.color, v.miles, v.used, v.SellerID, v.KBBValue, v.MPG);
    	}
    }

    static ArrayList<String> uniqueVINs = new ArrayList<String>();
    static ArrayList<MMTPacket> mmtPackets = new ArrayList<MMTPacket>();

    public static void main(String[] args)
    {
        addPackets();

        Vehicle[] vehicles = new Vehicle[999];

        for (int i =0; i<vehicles.length; i++)
        {
            Random r = new Random();
            MMTPacket currMMT = mmtPackets.get(r.nextInt(mmtPackets.size()));
            vehicles[i] = new Vehicle("abctest", currMMT.make, currMMT.model, currMMT.type);
        }
        
        printData(vehicles);
    }
}

//String SellerID = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 1500));