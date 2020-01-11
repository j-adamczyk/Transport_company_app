package google_api;

import app.model.Address;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoogleAPIAddressConverter {
    Address a1 = new Address("Poland", "Cracow", "33-333", "Krakowska 17");
    Address a2 = new Address("-", "-", "-", "-");

    @Test
    public void testRealPlace() {
        String convertedAddress = app.google_api.GoogleAPIAddressConverter.addressToGoogleAPIString(a1);
        String a1ExpectedForm = a1.getStreet() + ", " + a1.getCity() + ", " + a1.getCountry();
        assertEquals(a1ExpectedForm, convertedAddress);
    }

    @Test
    public void testNonexistentPlace() {
        String convertedAddress = app.google_api.GoogleAPIAddressConverter.addressToGoogleAPIString(a2);
        String a1ExpectedForm = a2.getStreet() + ", " + a2.getCity() + ", " + a2.getCountry();
        assertEquals(a1ExpectedForm, convertedAddress);
    }
}
