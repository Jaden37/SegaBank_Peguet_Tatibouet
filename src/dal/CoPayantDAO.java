package dal;
import bo.CoPayant;

import javax.naming.NamingEnumeration;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CoPayantDAO implements ICompteDAO<Integer, CoPayant>{
    private static final String INSERT_CoPayant_QUERY = "INSERT INTO compte (solde, type, idAgence) VALUES (?, ?, ?)";
    private static final String UPDATE_CoPayant_QUERY = "UPDATE compte SET solde = ?, idAgence = ? WHERE compte.idCompte = ?";
    private static final String SELECT_ALL_CoPayant_QUERY = "SELECT * FROM compte WHERE type = 'P'";

    @Override
    public CoPayant create(CoPayant object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_CoPayant_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            //Préparation de la requête
            ps.setDouble(   1, object.getSolde());
            ps.setString(2, "P");
            ps.setInt(3, object.getIdAgence());
            //Envoi de la requête
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating compte failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setIdCompte(generatedKeys.getInt(1));
                    return object;
                }
                else {
                    throw new SQLException("Creating compte failed, no ID obtained.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CoPayant update(CoPayant object_old, CoPayant object_new) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CoPayant_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            //Préparation de la requête
            ps.setDouble(   1, object_new.getSolde());
            ps.setInt(2, object_new.getIdAgence());
            ps.setInt(3, object_new.getIdCompte());
            //Envoi de la requête
            try{
                ps.executeUpdate();
                System.out.println("Update compte successfull");
                return object_new;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Update compte failed");
                return object_old;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CoPayant> findAll(CoPayant object) {
        try(Connection connection = PersistenceManager.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_CoPayant_QUERY))
        {
            ArrayList<CoPayant> ComptePayant = new ArrayList<>();
            while (rs.next()){
                ComptePayant.add(new CoPayant(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence")));
            }
            return ComptePayant;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CoPayant findById(Integer integer) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_By_Id_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
            CoPayant cp = null;
            while (rs.next()){
                cp = new CoPayant(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence"));
            }
            return cp;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Integer integer) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_By_Id_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
