package app.google_api;

import app.model.Address;

/**
 * Converter to represent Address object as string in format
 * usable by Google Distance Matrix API
 */
public class GoogleAPIAddressConverter {
    /**
     * Converts Address to string usable by Google Matrix API
     * @return String in format "street, city, country"
     */
    public static String addressToGoogleAPIString(Address address) {
        return address.getStreet() + ", " +
                address.getCity() +  ", " +
                address.getCountry();
    }
}
