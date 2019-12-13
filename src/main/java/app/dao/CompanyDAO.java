package app.dao;

import app.model.Company;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CompanyDAO extends GenericDAO<Company> {
    String collName = "companies";

    // basic CRUD operations

    @Override
    public void delete(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .deleteOne(eq("_id", id));
    }

    @Override
    public Company find(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .find(
                        eq("_id", id),
                        Company.class)
                .first();
    }

    @Override
    public void save(Company toSave) {
        DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .insertOne(toSave);
    }

    @Override
    public void update(ObjectId id, Company toUpdate) {
        DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .replaceOne(
                        eq("_id", id),
                        toUpdate);
    }

    // other methods

    public List<Company> findAllCompanies() {
        return DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .find()
                .into(new ArrayList<>());
    }

    public List<Company> findByName(String name) {
        return DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .find(eq("name", name))
                .into(new ArrayList<>());
    }

    public List<Company> findCompaniesFromCity(String cityName) {
        return DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .find(eq("address.city", cityName))
                .into(new ArrayList<>());
    }

    public List<Company> findCompaniesFromCountry(String countryName) {
        return DbConnector
                .getDB()
                .getCollection(collName, Company.class)
                .find(eq("address.country", countryName))
                .into(new ArrayList<>());
    }
}
