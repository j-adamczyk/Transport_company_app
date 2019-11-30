package dao;

public abstract class GenericDAO<Object> {
    public abstract void save(Object toSave);
    public abstract void update(Object toUpdate);
    public abstract void delete(Object toDelete);
}
