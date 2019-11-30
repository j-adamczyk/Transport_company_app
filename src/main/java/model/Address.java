package model;

import java.util.UUID;

public class Address {
    private UUID _id;
    private String country;
    private String city;
    private String postalCode;
    private String street;

    public Address(String country, String city, String postalCode, String street) {
        this._id = UUID.randomUUID();
        this.setCountry(country);
        this.setCity(city);
        this.setPostalCode(postalCode);
        this.setStreet(street);
    }

    public UUID get_id() {
        return _id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
