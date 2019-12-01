package model;

import org.bson.types.ObjectId;

import java.util.Objects;
import java.util.UUID;

public class Cargo {
    private ObjectId _id;
    private String name;
    private Double volume;
    private Double weight;

    public Cargo(){}

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
        if (this == o) return true;
        if (!(o instanceof Cargo)) return false;
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
}
