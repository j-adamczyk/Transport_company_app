package app.model;

import app.command.CommandRegistry;
import app.command.CurrentTransactionSaveCommand;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    private CurrentTransaction currentTransaction;

    private Map<ObjectId, LocalDateTime> transports; // map transport _id -> date of departure
    // currentTransaction can be edited ONLY if no transport carried a part of it
    private boolean editable;

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

        this.currentTransaction = new CurrentTransaction(this, cargo);
        CurrentTransactionSaveCommand command = new CurrentTransactionSaveCommand(this.currentTransaction);
        CommandRegistry.getInstance().executeCommand(command);

        this.transports = new HashMap<>();
        this.editable = true;
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

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public CurrentTransaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(CurrentTransaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Map<ObjectId, LocalDateTime> getTransports() {
        return transports;
    }

    public void setTransports(Map<ObjectId, LocalDateTime> transports) {
        this.transports = transports;
    }

    public void addTransport(Transport transport) {
        this.transports.put(transport.get_id(), transport.getDepartureDate());
        this.editable = false;
    }

    public void removeTransport(Transport transport) {
        this.transports.remove(transport.get_id());
        if (this.transports.size() == 0)
            this.editable = true;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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
                ", transports=" + transports.toString() +
                ", editable=" + editable +
                '}';
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
