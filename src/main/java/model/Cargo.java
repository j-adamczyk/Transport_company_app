package model;

import java.util.UUID;

public class Cargo {
    private UUID _id;
    private String name;
    private Double volume;
    private Double weight;

    public Cargo(String name, Double volume, Double weight) {
        this._id = UUID.randomUUID();
        this.name = name;
        this.volume = volume;
        this.weight = weight;
    }

    public UUID get_id() {
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
}
