package dal;

import bo.CoSimple;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CoSimpleDAO implements ICompteDAO<Integer, CoSimple> {
    private static final String INSERT_CoSimple_QUERY = "INSERT INTO compte (solde, decouvert, type, idAgence) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CoSimple_QUERY = "UPDATE compte SET solde = ?, decouvert = ?, idAgence = ? WHERE compte.idCompte = ?";

    @Override
    public CoSimple create(CoSimple object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_CoSimple_QUERY, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps2 = connection.prepareStatement(INSERT_OPERATION))
        {
            //Préparation de la requête
            ps.setDouble(   1, object.getSolde());
            ps.setDouble(2, object.getDecouvert());
            ps.setString(3, "S");
            ps.setInt(4, object.getIdAgence());
            //Envoi de la requête
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating compte failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setIdCompte(generatedKeys.getInt(1));
                    ps2.setString(1, "Creation du compte simple");
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
    public CoSimple update(CoSimple object_old, CoSimple object_new) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CoSimple_QUERY);
            PreparedStatement ps2 = connection.prepareStatement(INSERT_OPERATION))
        {
            //Préparation de la requête
            ps.setDouble(   1, object_new.getSolde());
            ps.setDouble(2, object_new.getDecouvert());
            ps.setInt(3, object_new.getIdAgence());
            ps.setInt(4, object_new.getIdCompte());
            //Envoi de la requête
            try{
                System.out.println(object_new.getSolde() - object_new.getSolde());
                ps.executeUpdate();
                if (object_old.getSolde() > object_new.getSolde()){
                    ps2.setString(1, "Retrait du compte simple");
                    ps2.setDouble(2, object_new.getSolde() - object_old.getSolde());
                }else {
                    ps2.setString(1, "Versement du compte simple");
                    ps2.setDouble(2,  object_new.getSolde() - object_old.getSolde());
                }
                java.util.Date date = new Date();
                ps2.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
                ps2.setInt(4, object_new.getIdCompte());
                ps2.executeUpdate();
                System.out.println("Update compte successful");
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
    public ArrayList<CoSimple> findAll() {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY))
        {
            ps.setString(1, "S");
            ResultSet rs = ps.executeQuery();
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
            PreparedStatement ps = connection.prepareStatement(FIND_By_Id_QUERY))
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
            PreparedStatement ps = connection.prepareStatement(DELETE_By_Id_QUERY);
            PreparedStatement ps2 = connection.prepareStatement(DELETE_ALL_OPERATION_QUERY))
        {
            ps.setInt(1, integer);
            ps2.setInt(1, integer);

            //Ont supprime toutes les opérations avant de supprimer le compte
            ps2.executeQuery();
            ps.executeQuery();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
