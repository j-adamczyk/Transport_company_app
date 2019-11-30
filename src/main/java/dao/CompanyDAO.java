package dao;

import model.Company;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class CompanyDAO extends GenericDAO<Company> {
    String collName = "companies";

    // basic CRUD operations

    @Override
    public void delete(UUID id) {
        DbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Company find(UUID id) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        Company.class)
                .first();
    }

    @Override
    public void save(Company toSave) {
        Document doc = Converter.toDocument(toSave);
        DbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Company toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }

    // other methods

    public List<Company> findAllCompanies() {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(Company.class)
                .into(new ArrayList<>());
    }

    public List<Company> findByName(String name) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("name", name),
                        Company.class)
                .into(new ArrayList<>());
    }

    public List<Company> findCompaniesFromCity(String cityName) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("city", cityName),
                        Company.class
                )
                .into(new ArrayList<>());
    }

    public List<Company> findCompaniesFromCountry(String countryName) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("country", countryName),
                        Company.class
                )
                .into(new ArrayList<>());
    }
}
