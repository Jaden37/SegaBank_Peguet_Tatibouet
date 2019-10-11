package dal;

import bo.CoSimple;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CoSimpleDAO implements ICompteDAO<Integer, CoSimple> {
    private static final String INSERT_CoSimple_QUERY = "INSERT INTO compte (solde, decouvert, type, idAgence) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CoSimple_QUERY = "UPDATE compte SET solde = ?, decouvert = ?, idAgence = ? WHERE compte.idCompte = ?";
    private static final String SELECT_ALL_CoSimple_QUERY = "SELECT * FROM compte WHERE type = 'S'";

    @Override
    public CoSimple create(CoSimple object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_CoSimple_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            //Préparation de la requête
            ps.setDouble(   1, object.getSolde());
            ps.setDouble(2, object.getDecouvert());
            ps.setString(3, "S");
            ps.setInt(4, object.getIdAgence());
            //Envoi de la requête
            ResultSet rs = ps.executeQuery();
            CoSimple New_CoSimple = new CoSimple();
            while (rs.next()){
                New_CoSimple = new CoSimple(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence"), rs.getDouble("decouvert"));
            }
            return New_CoSimple;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CoSimple update(CoSimple object_old, CoSimple object_new) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CoSimple_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            //Préparation de la requête
            ps.setDouble(   1, object_new.getSolde());
            ps.setDouble(2, object_new.getDecouvert());
            ps.setInt(3, object_new.getIdAgence());
            ps.setInt(4, object_new.getIdCompte());
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
    public ArrayList<CoSimple> findAll(CoSimple object) {
        try(Connection connection = PersistenceManager.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_CoSimple_QUERY))
        {
            ArrayList<CoSimple> ComptesSimple = new ArrayList<>();
            while (rs.next()){
                ComptesSimple.add(new CoSimple(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence"), rs.getDouble("decouvert")));
            }
            return ComptesSimple;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CoSimple findById(Integer integer) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_By_Id_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
            CoSimple cs = null;
            while (rs.next()){
                cs = new CoSimple(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence"), rs.getDouble("decouvert"));
            }
            return cs;
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
