package dal;

import bo.CoEpargne;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CoEpargneDAO implements ICompteDAO<Integer, CoEpargne> {
    private static final String INSERT_CoEpargne_QUERY = "INSERT INTO compte (solde, tauxInteret, type, idAgence) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CoEpargne_QUERY = "UPDATE compte SET solde = ?, tauxInteret = ?, idAgence = ? WHERE compte.idCompte = ?";
    private static final String SELECT_ALL_CoEpargne_QUERY = "SELECT * FROM compte WHERE type = 'E'";

    @Override
    public CoEpargne create(CoEpargne object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_CoEpargne_QUERY, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps2 = connection.prepareStatement(INSERT_OPERATION))
        {
            //Préparation de la requête
            ps.setDouble(   1, object.getSolde());
            ps.setFloat(2, object.getTauxInteret());
            ps.setString(3, "E");
            ps.setInt(4, object.getIdAgence());
            //Envoi de la requête
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating compte failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setIdCompte(generatedKeys.getInt(1));
                    ps2.setString(1, "Creation du compte epargne");
                    ps2.setDouble(2, object.getSolde());
                    java.util.Date date = new Date();
                    ps2.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
                    ps2.setInt(4, object.getIdCompte());
                    ps2.executeUpdate();
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
    public CoEpargne update(CoEpargne object_old, CoEpargne object_new) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CoEpargne_QUERY);
            PreparedStatement ps2 = connection.prepareStatement(INSERT_OPERATION))
        {
            //Préparation de la requête
            ps.setDouble(   1, object_new.getSolde());
            ps.setFloat(2, object_new.getTauxInteret());
            ps.setInt(3, object_new.getIdAgence());
            ps.setInt(4, object_new.getIdCompte());
            //Envoi de la requête
            try{
                System.out.println(object_new.getSolde() - object_new.getSolde());
                ps.executeUpdate();
                if (object_old.getSolde() > object_new.getSolde()){
                    ps2.setString(1, "Retrait du compte epargne");
                    ps2.setDouble(2, object_new.getSolde() - object_old.getSolde());
                }else {
                    ps2.setString(1, "Versement du compte epargne");
                    ps2.setDouble(2,  object_new.getSolde() - object_old.getSolde());
                }
                java.util.Date date = new Date();
                ps2.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
                ps2.setInt(4, object_new.getIdCompte());
                ps2.executeUpdate();
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
            PreparedStatement ps = connection.prepareStatement(FIND_By_Id_QUERY))
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
            PreparedStatement ps = connection.prepareStatement(DELETE_By_Id_QUERY))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
