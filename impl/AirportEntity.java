package impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The abstract AirportEntity class serves as a backbone for
 * other entity clases that share common properties and
 * accessor and/or mutator methods, such as Passenger,
 * Ticket, Plane, etc..
 */
public abstract class AirportEntity {
    private String id;

    // Static set to track all used IDs across all entities
    private static final Set<String> usedIds = new HashSet<>();

    // constructor
    public AirportEntity(String id) {
        if (usedIds.contains(id)) {
            System.out.println();
        }
        this.id = id;
        usedIds.add(id);
    }

    public String getId() {
        return id;
    }

    // using generics
    public static <T extends AirportEntity> T getById(List<T> entities, String id) {
        for (T entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }
}