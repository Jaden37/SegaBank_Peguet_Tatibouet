package dal;
import bo.Agence;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AgenceDAO implements IAgenceDAO<Integer, Agence> {
    
    @Override
    public Agence create(Agence object) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_AGENCE_QUERY, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, object.getCode());
                ps.setString(2, object.getAdresse());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating agence failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setIdAgence(generatedKeys.getInt(1));
                    return object;
                }
                else {
                    throw new SQLException("Creating agence failed, no ID obtained.");
                }
            }

        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Agence update(Agence object_old, Agence object_new) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_AGENCE_QUERY, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, object_new.getCode());
            ps.setString(2, object_new.getAdresse());
            ps.setInt(3, object_new.getIdAgence());
            try{
                ps.executeUpdate();
                System.out.println("Update agence successfull");
                return object_new;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Update agence failed");
                return object_old;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Agence> findAll(Agence object) {
        try(Connection connection = PersistenceManager.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_AGENCE_QUERY))
        {
            ArrayList<Agence> Aag = new ArrayList<>();
            while (rs.next()){
                Aag.add(new Agence(rs.getInt("idAgence"), rs.getString("code"), rs.getString("adresse")));
            }
            return Aag;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Agence findById(Integer integer) {
        try(Connection connection = PersistenceManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_By_Id_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, integer);
            ResultSet rs = ps.executeQuery();
            Agence ag = null;
            while (rs.next()){
                ag = new Agence(rs.getInt("idAgence"), rs.getString("code"), rs.getString("adresse"));
            }
            return ag;
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
