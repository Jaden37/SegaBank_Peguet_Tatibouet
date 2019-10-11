package bo;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Agence {
    private int idAgence;
    private String code;
    private String addresse;
    private ArrayList<Compte> comptes;

    public int getIdAgence() {
        return idAgence;
    }
    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getAddresse() {
        return addresse;
    }
    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }
    public ArrayList<Compte> getComptes() {
        return comptes;
    }
    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    public Agence(int idAgence, String code, String addresse) {
        this.idAgence = idAgence;
        this.code = code;
        this.addresse = addresse;
    }

    public void ListerComptes(ArrayList<CoPayant> comptes) throws IOException {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./resources/comptes.csv"), "UTF-8"));
            for (Compte compte : comptes)
            {
                String CSV_SEPARATOR = ",";
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(compte.getIdCompte());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(compte.getSolde());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(compte.getIdAgence());
                oneLine.append(";");
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}

    }

}
