package app.log;

/**
 * Defines what type of CRUD operation is the log entry with given type.
 * Only C, U, D operations are logged.
 */
public enum EntryType {
    CREATE, UPDATE, DELETE
}
