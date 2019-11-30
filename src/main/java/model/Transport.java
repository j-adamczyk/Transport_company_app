package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transport {
    private UUID _id;
    private CurrentTransaction currentTransaction;
    private Driver driver;
    private Vehicle vehicle;
    private Integer cargoUnits;
    private LocalDateTime departureDate;
    private Duration expectedTime;

    public Transport(CurrentTransaction currentTransaction, Driver driver, Vehicle vehicle, Integer cargoUnits, LocalDateTime departureDate, Duration expectedTime) {
        this._id = UUID.randomUUID();
        this.currentTransaction = currentTransaction;
        this.driver = driver;
        this.vehicle = vehicle;
        this.cargoUnits = cargoUnits;
        this.departureDate = departureDate;
        this.expectedTime = expectedTime;
    }

    public UUID get_id() {
        return _id;
    }

    public CurrentTransaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(CurrentTransaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getCargoUnits() {
        return cargoUnits;
    }

    public void setCargoUnits(Integer cargoUnits) {
        this.cargoUnits = cargoUnits;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public Duration getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Duration expectedTime) {
        this.expectedTime = expectedTime;
    }
}
