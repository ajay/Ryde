import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* TODO
* 10/21 7:42 PM:
* ADD Miles per Gallon dependent on type, ADD KBBValue dependent on year
* ADD additional MMTPacket objects to MMTPackets array
*
* */


public class Main {

    static class Vehicle{

        String VIN;
        String make;
        String model;
        String type;
        int year;
        String color;
        int miles;
        boolean used;
        int KBBValue;
        int MPG;
        String SellerID;


        public Vehicle( String make, String model, String type, int year, String color, int miles, boolean used, String sellerID) {
            this.VIN = generateVIN();
            this.make = make;
            this.model = model;
            this.type = type;
            this.year = year;
            this.color = color;
            this.miles = miles;
            this.used = used;
            this.KBBValue = KBBValue;

            this.MPG = MPG;
            SellerID = sellerID;
        }
    }

    static class MMTPacket{
        String make;
        String type;
        String model;

        public MMTPacket(String make, String model, String type) {
            this.make = make;
            this.type = type;
            this.model = model;
        }
    }

    static ArrayList<String> uniqueVINs;
    static MMTPacket mmtPackets [];

    static String[] colors = {"Blue", "Black", "Red", "White", "Gray", "Green"};

    private static String generateVIN(){

        Random r = new Random();
        String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
        String ret = "";
        for (int i =0; i< 17; i++){
            char cee = alphabet.charAt(r.nextInt(alphabet.length()));
            ret = ret + cee;
        }

        if (uniqueVINs.contains(ret))
            return generateVIN();

        return ret;
    }


    public static void main(String[] args) {

        uniqueVINs = new ArrayList<String>();
        mmtPackets = new MMTPacket[2];

        mmtPackets[0] = new MMTPacket("Honda", "Civic", "Sedan");
        mmtPackets[1] = new MMTPacket("Toyota", "Camry", "Sedan");

        Vehicle [] vehicles = new Vehicle[1000];

        for (int i =0; i<1000; i++){

            Random r = new Random();
            MMTPacket currMMT = mmtPackets[r.nextInt(2)];
            boolean used = r.nextBoolean();
            int year = ThreadLocalRandom.current().nextInt(1990, 2016);
            String SellerID = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 1500));
            int miles = 22;
            if (used)
                miles = ThreadLocalRandom.current().nextInt(3000, 150000);
            vehicles[i] = new Vehicle(currMMT.make, currMMT.model, currMMT.type, year, colors[r.nextInt(6)], miles, used, SellerID);


        }






    }
}
