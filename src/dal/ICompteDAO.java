package dal;

import java.util.ArrayList;

public interface ICompteDAO<ID, T> {
    static final String FIND_By_Id_QUERY = "SELECT * FROM compte WHERE idCompte = ?";
    static final String DELETE_By_Id_QUERY = "DELETE FROM compte WHERE idCompte = ?";
    static final String INSERT_OPERATION = "INSERT INTO operations (nom, montant, dateHeure, idCompte) VALUES (?, ?, ?, ?)";
    static final String SELECT_ALL_QUERY = "SELECT * FROM compte WHERE type = ?";
    static final String DELETE_ALL_OPERATION_QUERY = "DELETE FROM operations where idCompte = ?";

    public T create(T object);
    public T update(T object_old, T object_new);
    public ArrayList<T> findAll();
    public T findById(ID id);
    public void delete(ID id);
}
