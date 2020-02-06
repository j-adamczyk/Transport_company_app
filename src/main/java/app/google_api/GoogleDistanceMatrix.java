package app.google_api;

import app.model.Address;
import app.model.Duration;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import static app.google_api.GoogleAPIAddressConverter.addressToGoogleAPIString;

import java.io.IOException;

/**
 * Connects to Google Distance Matrix API.
 * It's used for calculating travel time (expectedTime attribute) between start and end points for Transport.
 */
public class GoogleDistanceMatrix {
    private static final String BASE_URI = "https://maps.googleapis.com/maps/api";
    private static final String API_KEY = "";

    private static final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(API_KEY)
            .build();

    public static Duration getTravelTime(Address origin, Address destination) {
        try {
            DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context);
            DistanceMatrix distances = request.origins(addressToGoogleAPIString(origin))
                    .destinations(addressToGoogleAPIString(destination))
                    .mode(TravelMode.DRIVING)
                    .units(Unit.METRIC)
                    .await();

            try {
                long seconds = distances.rows[0].elements[0].duration.inSeconds;
                int hours = (int) (seconds / 3600);
                int minutes = (int) ((seconds - hours * 3600) / 60);
                return new Duration(hours, minutes);
            }
            catch (NullPointerException e) {
                return null;
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
