package app.model;

import app.google_api.GoogleDistanceMatrix;
import org.bson.types.ObjectId;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Transport from point A to point B. Realizes a part of Transaction (or an entire Transaction if cargo is small
 * enough).
 */
public class  Transport {
    public ObjectId _id;
    private CurrentTransaction currentTransaction;
    private Driver driver;
    private Vehicle vehicle;
    private LocalDateTime departureDate;
    private Duration expectedTime;

    private Map<String, Cargo> cargoTypes;  // map Cargo.name -> Cargo
    private Map<String, Integer> cargoUnits;     // map Cargo.name -> Cargo units

    // transport can be edited ONLY if it hasn't departed yet
    private boolean editable;

    // for MongoDB serializer
    public Transport() {}

    public Transport(CurrentTransaction currentTransaction, Driver driver, Vehicle vehicle, LocalDateTime departureDate) {
        this._id = new ObjectId();
        this.currentTransaction = currentTransaction;
        this.driver = driver;
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.editable = true;

        calculateExpectedTime();

        // calculate optimal cargo to transport, fill cargoTypes and cargo fields
        this.cargoTypes = new HashMap<>();
        this.cargoUnits = new HashMap<>();
        calculateCargo();

        this.currentTransaction.getTransaction().addTransport(this);
    }

    private void calculateExpectedTime() {
        Address origin = currentTransaction.getTransaction().getOrigin();
        Address destination = currentTransaction.getTransaction().getDestination();

        this.expectedTime = GoogleDistanceMatrix.getTravelTime(origin, destination);
    }

    // TODO: add more sophisticated cargo choosing method than simple weight
    private void calculateCargo() {
        Map<String, Integer> cargoLeft = currentTransaction.getCargoLeft();

        // filter only those cargo types that are in cargoLeft;
        Map<String, Cargo> possibleCargoTypes = new HashMap<>(currentTransaction.getTransaction().getCargoTypes());
        possibleCargoTypes.keySet().retainAll(cargoLeft.keySet());

        // sort cargo types by weight descending
        possibleCargoTypes =
                possibleCargoTypes
                        .entrySet()
                        .stream()
                        .sorted((e1, e2) -> e2.getValue().getWeight().compareTo(e1.getValue().getWeight()))
                        .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (e1, e2) -> e1,
                                        LinkedHashMap::new));

        double currWeight = 0;
        double maxWeight = vehicle.getCargoWeight();

        double currVolume = 0;
        double maxVolume = vehicle.getCargoVolume();

        for (Map.Entry<String, Cargo> entry : possibleCargoTypes.entrySet()) {
            String cargoName = entry.getKey();
            Cargo cargoType = entry.getValue();

            // calculate how many units we can add with respect to weight and volume capacity of the vehicle
            int addedUnits = 0;
            while (currWeight + cargoType.getWeight() <= maxWeight &&
                   currVolume + cargoType.getVolume() <= maxVolume) {
                addedUnits += 1;
                currWeight += cargoType.getWeight();
                currVolume += cargoType.getVolume();
            }

            if (addedUnits > 0) {
                // actually add units
                int currentUnits = this.cargoUnits.getOrDefault(cargoName, 0);
                currentUnits += addedUnits;
                this.cargoUnits.put(cargoName, currentUnits);

                // add cargo type to transported cargo types
                this.cargoTypes.put(cargoName, cargoType);

                // remove units from those waiting for transport
                Map<String, Integer> newCargoUnits = this.currentTransaction.getCargoLeft();
                newCargoUnits.put(cargoName, newCargoUnits.get(cargoName) - addedUnits);
            }
        }
    }

    public ObjectId get_id() {
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

    public Map<String, Cargo> getCargoTypes() {
        return cargoTypes;
    }

    public void setCargoTypes(Map<String, Cargo> cargoTypes) {
        this.cargoTypes = cargoTypes;
    }

    public Map<String, Integer> getCargoUnits() {
        return cargoUnits;
    }

    public void setCargoUnits(Map<String, Integer> cargoUnits) {
        this.cargoUnits = cargoUnits;
    }

    public boolean isEditable() {
        if (!editable)
            return false;

        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(departureDate)) {
            this.editable = false;
            return false;
        }

        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Transport transport = (Transport) o;
        return get_id().equals(transport.get_id()) &&
                Objects.equals(getCurrentTransaction(), transport.getCurrentTransaction()) &&
                Objects.equals(getDriver(), transport.getDriver()) &&
                Objects.equals(getVehicle(), transport.getVehicle()) &&
                Objects.equals(getCargoUnits(), transport.getCargoUnits()) &&
                Objects.equals(getDepartureDate(), transport.getDepartureDate()) &&
                Objects.equals(getExpectedTime(), transport.getExpectedTime()) &&
                Objects.equals(isEditable(), transport.isEditable());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getCurrentTransaction(), getDriver(),
                getVehicle(), getCargoUnits(), getDepartureDate(), getExpectedTime(),
                isEditable());
    }

    @Override
    public String toString() {
        return "Transport{" +
                "_id=" + _id +
                ", currentTransaction=" + currentTransaction +
                ", driver=" + driver +
                ", vehicle=" + vehicle +
                ", cargoUnits=" + cargoUnits +
                ", departureDate=" + departureDate +
                ", expectedTime=" + expectedTime +
                ", isEditable=" + editable +
                '}';
    }
}
