package app.model;

import app.command.CommandRegistry;
import app.command.CurrentTransactionSaveCommand;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/**
 * Invoice/deal with given company.
 * Requires defining Cargo types for given Transaction before creating the Transaction object.
 */
public class Transaction {
    public ObjectId _id;
    private Company contractor;
    private Map<String, Cargo> cargoTypes;  // map Cargo.name -> Cargo
    private Map<String, Integer> cargo;     // map Cargo.name -> Cargo units
    private Address origin;
    private Address destination;
    private Double money;
    private LocalDate transactionDate;

    // for MongoDB serializer
    public Transaction() {}

    public Transaction(Company contractor, Map<String, Cargo> cargoTypes, Map<String, Integer> cargo,
                       Address origin, Address destination, Double money, LocalDate transactionDate) {
        this._id = new ObjectId();
        this.contractor = contractor;
        this.cargoTypes = cargoTypes;
        this.cargo = cargo;
        this.origin = origin;
        this.destination = destination;
        this.money = money;
        this.transactionDate = transactionDate;

        CurrentTransaction currentTransaction = new CurrentTransaction(this, cargo);
        CurrentTransactionSaveCommand command = new CurrentTransactionSaveCommand(currentTransaction);
        CommandRegistry.getInstance().executeCommand(command);
    }

    public ObjectId get_id() {
        return _id;
    }

    public Company getContractor() {
        return contractor;
    }

    public void setContractor(Company contractor) {
        this.contractor = contractor;
    }

    public Map<String, Cargo> getCargoTypes() {
        return cargoTypes;
    }

    public void setCargoTypes(Map<String, Cargo> cargoTypes) {
        this.cargoTypes = cargoTypes;
    }

    public Map<String, Integer> getCargo() {
        return cargo;
    }

    public void setCargo(Map<String, Integer> cargo) {
        this.cargo = cargo;
    }

    public Address getOrigin() {
        return origin;
    }

    public void setOrigin(Address origin) {
        this.origin = origin;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public Double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "_id=" + _id +
                ", contractor=" + contractor +
                ", cargoTypes=" + cargoTypes +
                ", cargo=" + cargo +
                ", origin=" + origin +
                ", destination=" + destination +
                ", money=" + money +
                ", transactionDate=" + transactionDate +
                '}';
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Transaction that = (Transaction) o;
        return Objects.equals(get_id(), that.get_id()) &&
                Objects.equals(getContractor(), that.getContractor()) &&
                Objects.equals(getCargo(), that.getCargo()) &&
                Objects.equals(getOrigin(), that.getOrigin()) &&
                Objects.equals(getDestination(), that.getDestination()) &&
                Objects.equals(getMoney(), that.getMoney()) &&
                Objects.equals(getTransactionDate(), that.getTransactionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getContractor(), getCargo(), getOrigin(), getDestination(), getMoney(), getTransactionDate());
    }
}
