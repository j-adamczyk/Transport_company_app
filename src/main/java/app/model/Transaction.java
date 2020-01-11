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

    // map transport _id -> date of departure
    // should really be ObjectId -> LocalDateTime, but BSON serializer does not allow ObjectId as key
    private Map<String, LocalDateTime> transports;

    // Transaction can be edited ONLY if no transport carried a part of it
    private boolean editable;

    // Transaction is done when everything from it's currentTransaction's cargoLeft is taken
    private boolean done;

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

        this.transports = new HashMap<>();
        this.editable = true;
        this.done = false;
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

    public Map<String, LocalDateTime> getTransports() {
        return transports;
    }

    public void setTransports(Map<String, LocalDateTime> transports) {
        this.transports = transports;
    }

    public void addTransport(Transport transport) {
        this.transports.put(transport.get_id().toString(), transport.getDepartureDate());
        this.editable = false;
    }

    public void removeTransport(Transport transport) {
        this.transports.remove(transport.get_id().toString());
        if (this.transports.size() == 0)
            this.editable = true;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
                ", done=" + done +
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
