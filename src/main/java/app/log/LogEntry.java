package app.log;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents single log entry.
 * Should be inserted immediately after creation because of creating timestamp in constructor.
 */
public class LogEntry {
    public ObjectId _id;
    private EntryType entryType;
    private LocalDateTime timestamp;
    private String data;

    // for MongoDB serializer
    public LogEntry() {}

    public LogEntry(EntryType entryType, String data) {
        this.entryType = entryType;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public ObjectId get_id() {
        return _id;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof LogEntry))
            return false;

        LogEntry logEntry = (LogEntry) o;
        return get_id().equals(logEntry.get_id()) &&
                Objects.equals(getEntryType(), logEntry.getEntryType()) &&
                Objects.equals(getTimestamp(), logEntry.getTimestamp()) &&
                Objects.equals(getData(), logEntry.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getEntryType(), getTimestamp(), getData());
    }

    @Override
    public String toString() {
        return this.entryType.name() + " (" + this.timestamp.toString() + "): " + this.data;
    }
}
