package impl;
public class Passenger extends AirportEntity {
    private String name;
    private String sex;
    private int age;
    private String ticketId;

    public Passenger(String id, String name, String sex, int age, String ticketId) {
        super(id);
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.ticketId = ticketId;
    }

    // accessor methods

    public String getName() {
        return this.name;
    }

    public String getSex() {
        return this.sex;
    }

    public int getAge() {
        return this.age;
    }

    public String getTcketId() {
        return this.ticketId;
    }

}