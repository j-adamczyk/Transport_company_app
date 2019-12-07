package model;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Vehicle used by company to transport cargo.
 */
public class Vehicle {
    public ObjectId _id;
    private String model;
    private String registrationNo;
    private LocalDate manufactureDate;
    private Double cargoVolume;
    private Double cargoWeight;

    // for MongoDB serializer
    public Vehicle() {}

    public Vehicle(String model, String registrationNo, LocalDate manufactureDate, Double cargoVolume, Double cargoWeight) {
        this._id = new ObjectId();
        this.model = model;
        this.registrationNo = registrationNo;
        this.manufactureDate = manufactureDate;
        this.cargoVolume = cargoVolume;
        this.cargoWeight = cargoWeight;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(Double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public Double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return get_id().equals(vehicle.get_id()) &&
                Objects.equals(getModel(), vehicle.getModel()) &&
                Objects.equals(getRegistrationNo(), vehicle.getRegistrationNo()) &&
                Objects.equals(getManufactureDate(), vehicle.getManufactureDate()) &&
                Objects.equals(getCargoVolume(), vehicle.getCargoVolume()) &&
                Objects.equals(getCargoWeight(), vehicle.getCargoWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getModel(), getRegistrationNo(), getManufactureDate(), getCargoVolume(), getCargoWeight());
    }
}
