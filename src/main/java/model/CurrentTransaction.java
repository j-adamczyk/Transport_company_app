package model;

import java.util.Map;

public class CurrentTransaction {
    private Transaction transaction;
    private Map<String, Integer> cargoLeft;

    public CurrentTransaction(Transaction transaction, Map<String, Integer> cargoLeft) {
        this.transaction = transaction;
        this.cargoLeft = cargoLeft;
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
}
