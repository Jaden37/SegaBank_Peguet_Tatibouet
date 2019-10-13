package dal;

import bo.CoPayant;
import bo.Compte;
import bo.Operation;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OperationDAO {
    static final String SELECT_ALL_OPERATION_By_Id = "SELECT * from OPERATIONS WHERE idCompte = ?";

    public boolean listerOperations(int idCompte){
        ArrayList<Operation> operations = new ArrayList<>();
        try(Connection connection = PersistenceManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_ALL_OPERATION_By_Id))
        {
            ps.setInt(1, idCompte);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                operations.add(new Operation(rs.getInt("idOperation"), rs.getString("nom"),rs.getDouble("montant"), rs.getTimestamp("dateHeure"),rs.getInt("idCompte")));
            }

            if(operations.isEmpty()){

            }
            else {
                try
                {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
                    LocalDateTime now = LocalDateTime.now();
                    String name = "./resources/operation_"+ dtf.format(now) + ".csv";
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));
                    String CSV_SEPARATOR = ";";

                    bw.write("idOperation; Nom; Montant; Date Heure");
                    bw.newLine();
                    for (Operation operation : operations)
                    {
                        StringBuffer oneLine = new StringBuffer();
                        oneLine.append(operation.getIdOperation());
                        oneLine.append(CSV_SEPARATOR);
                        oneLine.append(operation.getNom());
                        oneLine.append(CSV_SEPARATOR);
                        oneLine.append(operation.getMontant());
                        oneLine.append(CSV_SEPARATOR);
                        oneLine.append(operation.getDateHeure());
                        bw.write(oneLine.toString());
                        bw.newLine();
                    }
                    bw.flush();
                    bw.close();
                    System.out.println("Fichier disponible dans le répertoire");
                    System.out.println(name);
                    return true;
                }
                catch (UnsupportedEncodingException e) {}
                catch (FileNotFoundException e){}
                catch (IOException e){}
                return false;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
