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
        doc.append("country", address.country);
        doc.append("city", address.city);
        doc.append("postalCode", address.postalCode);
        doc.append("street", address.street);
        return doc;
    }

    public static Document toDocument(Cargo cargo) {
        Document doc = new Document();
        doc.append("name", cargo.name);
        doc.append("volume", cargo.volume.toString());
        doc.append("weight", cargo.weight.toString());
        return doc;
    }

    public static Document toDocument(Company company) {
        Document doc = new Document();
        doc.append("name", company.name);
        doc.append("address", toDocument(company.address));
        doc.append("phone", company.phone);
        doc.append("mail", company.mail);
        doc.append("representative", company.representative);
        return doc;
    }

    public static Document toDocument(CurrentTransaction currTransaction) {
        Document doc = new Document();
        doc.append("transaction", toDocument(currTransaction.transaction));
        doc.append("cargoLeft", new Document(currTransaction.cargoLeft));
        return doc;
    }

    public static Document toDocument(Driver driver) {
        Document doc = new Document();
        doc.append("name", driver.name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        doc.append("birthDate", formatter.format(driver.birthDate));
        doc.append("hiringDate", formatter.format(driver.hiringDate));
        doc.append("phone", driver.phone);
        doc.append("address", toDocument(driver.address));
        doc.append("salary", salary.toString());
        return doc;
    }

    public static Document toDocument(Transaction transaction) {
        Document doc = new Document();
        doc.append("contractor", toDocument(transaction.contractor));
        doc.append("cargo", new Document(transaction.cargo));
        doc.append("from", toDocument(transaction.from));
        doc.append("destination", toDocument(transaction.destination));
        doc.append("money", transaction.money.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        doc.append("transactionDate", formatter.format(transaction.transactionDate));
        return doc;
    }

    public static Document toDocument(Transport transport) {
        Document doc = new Document();
        doc.append("currentTransaction", toDocument(transport.currentTransaction));
        doc.append("driver", toDocument(transport.driver));
        doc.append("vehicle", toDocument(transport.vehicle));
        doc.append("cargoUnits", transport.cargoUnits.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
        doc.append("departureDate", formatter.format(transport.departureDate));
        doc.append("expectedTime", transport.expectedTime.toString());
        return doc;
    }

    public static Document toDocument(Vehicle vehicle) {
        Document doc = new Document();
        doc.append("model", vehicle.model);
        doc.append("registrationNo", vehicle.registrationNo);
        doc.append("manufactureDate", vehicle.manufactureDate.getYear());
        doc.append("cargoVolume", vehicle.cargoVolume);
        doc.append("cargoWeight", vehicle.cargoWeight);
        return doc;
    }
}
