package exceptions;

public class ThatSeatIsTaken extends Exception{
    public ThatSeatIsTaken() {
        super("That seat is already taken by another passenger");
    }
}
