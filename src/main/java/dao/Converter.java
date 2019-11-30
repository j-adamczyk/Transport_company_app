package dao;

import model.*;
import org.bson.Document;

import java.time.format.DateTimeFormatter;

/**
 * Converts objects to their Document representation for use with DAO objects.
 */
public class Converter {
    public static Document toDocument(Address address) {
        Document doc = new Document();
        doc.append("country", address.getCountry());
        doc.append("city", address.getCity());
        doc.append("postalCode", address.getPostalCode());
        doc.append("street", address.getStreet());
        return doc;
    }

    public static Document toDocument(Cargo cargo) {
        Document doc = new Document();
        doc.append("name", cargo.getName());
        doc.append("volume", cargo.getVolume().toString());
        doc.append("weight", cargo.getWeight().toString());
        return doc;
    }

    public static Document toDocument(Company company) {
        Document doc = new Document();
        doc.append("name", company.getName());
        doc.append("address", toDocument(company.getAddress()));
        doc.append("phone", company.getPhone());
        doc.append("mail", company.getMail());
        doc.append("representative", company.getRepresentative());
        return doc;
    }

    public static Document toDocument(CurrentTransaction currTransaction) {
        Document doc = new Document();
        doc.append("transaction", toDocument(currTransaction.getTransaction()));
        Document cargoLeft = new Document();
        cargoLeft.putAll(currTransaction.getCargoLeft());
        doc.append("cargoLeft", cargoLeft);
        return doc;
    }

    public static Document toDocument(Driver driver) {
        Document doc = new Document();
        doc.append("name", driver.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        doc.append("birthDate", formatter.format(driver.getBirthDate()));
        doc.append("hiringDate", formatter.format(driver.getHireDate()));
        doc.append("phone", driver.getPhone());
        doc.append("address", toDocument(driver.getAddress()));
        doc.append("salary", driver.getSalary().toString());
        return doc;
    }

    public static Document toDocument(Transaction transaction) {
        Document doc = new Document();
        doc.append("contractor", toDocument(transaction.getContractor()));
        Document cargo = new Document();
        cargo.putAll(transaction.getCargo());
        doc.append("cargo", cargo);
        doc.append("from", toDocument(transaction.getFrom()));
        doc.append("destination", toDocument(transaction.getDestination()));
        doc.append("money", transaction.getMoney().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        doc.append("transactionDate", formatter.format(transaction.getTransactionDate()));
        return doc;
    }

    public static Document toDocument(Transport transport) {
        Document doc = new Document();
        doc.append("currentTransaction", toDocument(transport.getCurrentTransaction()));
        doc.append("driver", toDocument(transport.getDriver()));
        doc.append("vehicle", toDocument(transport.getVehicle()));
        doc.append("cargoUnits", transport.getCargoUnits().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
        doc.append("departureDate", formatter.format(transport.getDepartureDate()));
        doc.append("expectedTime", transport.getExpectedTime().toString());
        return doc;
    }

    public static Document toDocument(Vehicle vehicle) {
        Document doc = new Document();
        doc.append("model", vehicle.getModel());
        doc.append("registrationNo", vehicle.getRegistrationNo());
        doc.append("manufactureDate", vehicle.getManufactureDate().getYear());
        doc.append("cargoVolume", vehicle.getCargoVolume());
        doc.append("cargoWeight", vehicle.getCargoWeight());
        return doc;
    }
}
