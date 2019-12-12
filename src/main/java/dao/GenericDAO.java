package dao;

import org.bson.types.ObjectId;

/**
 * Parent class of all DAO classes. Provides interface with basic CRUD operations.
 *
 * @param <Object> class specific to DAO
 */
public abstract class GenericDAO<Object> {
    /**
     * Implementation of "Delete" CRUD operation.
     *
     * @param id _id of object/document (they're the same)
     */
    public abstract void delete(ObjectId id);

    /**
     * Implementation of "Read" CRUD operation.
     *
     * @param id _id of object/document (they're the same)
     * @return object of class specific to DAO
     */
    public abstract Object find(ObjectId id);

    /**
     * Implementation of "Create" CRUD operation.
     *
     * @param toSave object of class specific to DAO
     */
    public abstract void save(Object toSave);

    /**
     * Implementation of "Update" CRUD operation.
     *
     * @param id _id of object/document (they're the same)
     * @param toUpdate object of class specific to DAO
     */
    public abstract void update(ObjectId id, Object toUpdate);
}
