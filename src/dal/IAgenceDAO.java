package dal;
import java.util.ArrayList;

public interface IAgenceDAO<ID, T> {
     static final String INSERT_AGENCE_QUERY = "INSERT INTO agence (code, adresse) VALUES (?, ?)";
     static final String UPDATE_AGENCE_QUERY = "UPDATE agence SET code = ?, adresse = ? WHERE idAgence = ?";
     static final String SELECT_ALL_AGENCE_QUERY = "SELECT * FROM agence";
     static final String FIND_By_Id_QUERY = "SELECT * FROM agence WHERE idAgence = ?";
     static final String DELETE_By_Id_QUERY = "DELETE FROM agence WHERE idAgence = ?";
    
    public T create(T object);
    public T update(T object_old, T object_new);
    public ArrayList<T> findAll();
    public T findById(ID id);
    public void delete(ID id);
}
