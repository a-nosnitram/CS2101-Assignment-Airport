package impl;
/**
 * The abstract AirportEntity class serves as a backbone for
 * other entity clases that share common properties and
 * accessor and/or mutator methods, such as Passenger,
 * Ticket, Plane, etc..
 */
public abstract class AirportEntity {
    private String id;

    // constructor
    public AirportEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}