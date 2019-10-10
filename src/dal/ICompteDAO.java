package dal;

import java.util.ArrayList;

public interface ICompteDAO<ID, T> {
    public void create(T object);
    public void update(T object);
    public ArrayList<T> findAll(T object);
    public T findById(ID id);
    public void delete(ID id);
}
