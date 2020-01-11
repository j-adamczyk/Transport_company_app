package app.model;

import org.bson.types.ObjectId;

import java.util.Map;
import java.util.Objects;

/**
 * Current state of given Transaction.
 * Exists until there is no cargo left to transport, when it can be deleted.
 */
public class CurrentTransaction {
    public ObjectId _id;
    private Transaction transaction;
    private Map<String, Integer> cargoLeft;

    // for MongoDB serializer
    public CurrentTransaction() {}

    public CurrentTransaction(Transaction transaction, Map<String, Integer> cargoLeft) {
        this._id = new ObjectId();
        this.transaction = transaction;
        this.cargoLeft = cargoLeft;
    }

    public ObjectId get_id() {
        return _id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map<String, Integer> getCargoLeft() {
        return cargoLeft;
    }

    public void setCargoLeft(Map<String, Integer> cargoLeft) {
        this.cargoLeft = cargoLeft;
    }

    public void addCargo(Map<String, Integer> cargoUnits) {
        for (Map.Entry<String, Integer> entry: cargoUnits.entrySet()) {
            String cargoName = entry.getKey();
            Integer cargoCount = entry.getValue();

            Integer presentCargoCount = this.cargoLeft.getOrDefault(cargoName, 0);
            this.cargoLeft.put(cargoName, presentCargoCount + cargoCount);
        }
    }

    public void removeCargo(Map<String, Integer> cargoUnits) {
        for (Map.Entry<String, Integer> entry: cargoUnits.entrySet()) {
            String cargoName = entry.getKey();
            Integer cargoCount = entry.getValue();

            Integer presentCargoCount = this.cargoLeft.getOrDefault(cargoName, 0);
            Integer newCargoCount = presentCargoCount - cargoCount;
            if (newCargoCount < 0)
                throw new IllegalArgumentException("Taking more cargo than is available!");

            this.cargoLeft.put(cargoName, newCargoCount);
        }
    }

    @Override
    public String toString() {
        return "CurrentTransaction{" +
                "_id=" + _id +
                ", Transaction_id=" + transaction.get_id() +
                ", cargoLeft=" + cargoLeft.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CurrentTransaction that = (CurrentTransaction) o;
        return get_id().equals(that.get_id()) &&
                Objects.equals(getTransaction(), that.getTransaction()) &&
                Objects.equals(getCargoLeft(), that.getCargoLeft());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getTransaction(), getCargoLeft());
    }
}
