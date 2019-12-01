package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Vehicle {
    private UUID _id;
    private String model;
    private String registrationNo;
    private LocalDate manufactureDate;
    private Double cargoVolume;
    private Double cargoWeight;

    public Vehicle(String model, String registrationNo, LocalDate manufactureDate, Double cargoVolume, Double cargoWeight) {
        this._id = UUID.randomUUID();
        this.model = model;
        this.registrationNo = registrationNo;
        this.manufactureDate = manufactureDate;
        this.cargoVolume = cargoVolume;
        this.cargoWeight = cargoWeight;
    }

    public UUID get_id() {
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
