package app.model;

import javafx.beans.property.ObjectProperty;
import org.bson.types.ObjectId;

import java.util.Objects;

/**
 * Single "unit" of cargo of given type.
 * Every transaction defines unique cargo types (though they can be identical between transactions).
 * Requires that it can be uniquely identified by name string in a given transaction.
 *
 * Once created, the attributes SHOULD NOT be changed. Fields cannot be declared final, since MongoDB serializer
 * requires an empty constructor, though technically they should be final.
 */
public class Cargo {
    public ObjectId _id;
    private String name;
    private Double volume;
    private Double weight;

    // for MongoDB serializer
    public Cargo() {}

    public Cargo(String name, Double volume, Double weight) {
        this._id = new ObjectId();
        this.name = name;
        this.volume = volume;
        this.weight = weight;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Cargo cargo = (Cargo) o;
        return get_id().equals(cargo.get_id()) &&
                Objects.equals(getName(), cargo.getName()) &&
                Objects.equals(getVolume(), cargo.getVolume()) &&
                Objects.equals(getWeight(), cargo.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getName(), getVolume(), getWeight());
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                ", weight=" + weight +
                '}';
    }
}
