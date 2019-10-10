package dal;

import bo.CoEpargne;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CoEpargneDAO implements ICompteDAO<Integer, CoEpargne> {
    private static final String INSERT_CoEpargne_QUERY = "INSERT INTO compte (solde, tauxInteret, type, idAgence) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CoEpargne_QUERY = "UPDATE compte SET solde = ?, tauxInteret = ?, idAgence = ? WHERE compte.idCompte = ?";
    private static final String SELECT_ALL_CoEpargne_QUERY = "SELECT * FROM compte WHERE type = 'E'";
    private static final String FIND_By_Id_CoEpargne_QUERY = "SELECT * FROM compte WHERE idCompte = ?";
    private static final String DELETE_CoEpargne_QUERY = "DELETE FROM compte WHERE idCompte = ?";


    @Override
    public void create(CoEpargne object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_CoEpargne_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            //Préparation de la requête
            ps.setDouble(   1, object.getSolde());
            ps.setFloat(2, object.getTauxInteret());
            ps.setString(3, "E");
            ps.setInt(4, object.getIdAgence());
            //Envoi de la requête
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CoEpargne object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CoEpargne_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            //Préparation de la requête
            ps.setDouble(   1, object.getSolde());
            ps.setFloat(2, object.getTauxInteret());
            ps.setInt(3, object.getIdAgence());
            ps.setInt(4, object.getIdCompte());
            //Envoi de la requête
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList findAll(CoEpargne object) {
        try(Connection connection = PersistenceManager.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_CoEpargne_QUERY))
        {
            ArrayList<CoEpargne> CompteEpargne = new ArrayList<>();
            while (rs.next()){
                CompteEpargne.add(new CoEpargne(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence"),  rs.getFloat("tauxInteret")));
            }
            return CompteEpargne;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CoEpargne findById(Integer integer) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_By_Id_CoEpargne_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
            CoEpargne ce = null;
            while (rs.next()){
                ce = new CoEpargne(rs.getInt("idCompte"), rs.getDouble("solde"), rs.getInt("idAgence"), rs.getFloat("tauxInteret"));
            }
            return ce;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Integer integer) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CoEpargne_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
