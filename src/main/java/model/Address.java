package model;

public class Address {
    private String country;
    private String city;
    private String postalCode;
    private String street;

    public Address(String country, String city, String postalCode, String street) {
        this.setCountry(country);
        this.setCity(city);
        this.setPostalCode(postalCode);
        this.setStreet(street);
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
