package model;

import java.time.LocalDate;
import java.util.Map;

public class Transaction {
    private Company contractor;
    private Map<String, Integer> cargo;
    private Address from;
    private Address destination;
    private Double money;
    private LocalDate transactionDate;

    public Transaction(Company contractor, Map<String, Integer> cargo, Address from, Address destination, Double money, LocalDate transactionDate) {
        this.contractor = contractor;
        this.cargo = cargo;
        this.from = from;
        this.destination = destination;
        this.money = money;
        this.transactionDate = transactionDate;
    }

    public Company getContractor() {
        return contractor;
    }

    public void setContractor(Company contractor) {
        this.contractor = contractor;
    }

    public Map<String, Integer> getCargo() {
        return cargo;
    }

    public void setCargo(Map<String, Integer> cargo) {
        this.cargo = cargo;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
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
}
