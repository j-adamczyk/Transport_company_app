package model;

import java.util.Objects;

public class Duration {
    int minutes;
    int hours;

    public Duration(int hours, int minutes) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public Duration(){};

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
