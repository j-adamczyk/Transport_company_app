package app.model;

import java.util.Objects;

/**
 * Custom simple Duration implementation. Used instead of Java Duration class because MongoDB has trouble
 * serializing that class.
 */
public class Duration {
    int minutes;
    int hours;

    // for MongoDB serializer
    public Duration() {}

    public Duration(int hours, int minutes) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duration)) return false;
        Duration duration = (Duration) o;
        return getMinutes() == duration.getMinutes() &&
                getHours() == duration.getHours();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMinutes(), getHours());
    }
}
