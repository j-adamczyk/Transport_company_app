package dao;

import java.util.UUID;

public abstract class GenericDAO<Object> {
    public abstract void delete(UUID id);
    public abstract Object find(UUID id);
    public abstract void save(Object toSave);
    public abstract void update(UUID id, Object toUpdate);
}
