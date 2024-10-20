package exceptions;

public class PlaneCurrentlyTaxiing extends Exception {
    public PlaneCurrentlyTaxiing() {
        super("That plane is currectly Taxiing.");
    }
}
