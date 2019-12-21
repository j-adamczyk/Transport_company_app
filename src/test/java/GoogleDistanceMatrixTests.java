import app.GoogleDistanceMatrix;
import app.model.Address;
import app.model.Duration;
import org.junit.Test;

import static java.lang.Long.max;
import static java.lang.Math.abs;
import static org.junit.Assert.*;

/**
 * Because of Google Matrix API monthly credit those tests should make minimal use of actual requests possible.
 */
public class GoogleDistanceMatrixTests {
    Address a1 = new Address("Poland", "Cracow", "33-333", "Krakowska 17");
    Address a2 = new Address("Germany", "Berlin", "G33-333", "Kastanienallee 19");

    Address a3 = new Address("-", "-", "-", "-");
    Address a4 = new Address("=", "=", "=", "=");


    @Test
    public void testRealPlaces() {
        Duration duration = GoogleDistanceMatrix.getTravelTime(a1, a2);
        assertNotNull(duration);
        assertTrue(duration.getHours() >= 0 || duration.getMinutes() > 0);
    }

    @Test
    public void testNonexistentPlaces() {
        Duration duration = GoogleDistanceMatrix.getTravelTime(a3, a4);
        assertNull(duration);
    }

    @Test
    public void testOneRealAndOneNonexistentPlace() {
        Duration duration = GoogleDistanceMatrix.getTravelTime(a1, a3);
        assertNull(duration);
    }

    /**
     * "Almost" symmetry, since Google Matrix API is NOT symmetric, i. e. distance between city A and B may not be
     * the same as distance between B and A, but it should be similar, e. g. within 10% time tolerance.
     */
    @Test
    public void testAlmostSymmetry() {
        Duration d1 = GoogleDistanceMatrix.getTravelTime(a1, a2);
        Duration d2 = GoogleDistanceMatrix.getTravelTime(a2, a1);

        assertNotNull(d1);
        assertNotNull(d2);
        long d1InSeconds = d1.getHours() * 3600 + d1.getMinutes() * 60;
        long d2InSeconds = d2.getHours() * 3600 + d2.getMinutes() * 60;

        assertTrue(abs(d1InSeconds - d2InSeconds) < 0.1 * max(d1InSeconds, d2InSeconds));
    }
}