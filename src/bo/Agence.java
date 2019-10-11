package bo;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Agence {
    private int idAgence;
    private String code;
    private String adresse;
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
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public ArrayList<Compte> getComptes() {
        return comptes;
    }
    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    public Agence(int idAgence, String code, String adresse) {
        this.idAgence = idAgence;
        this.code = code;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Agence{");
        sb.append("idAgence=").append(idAgence);
        sb.append(", code='").append(code).append('\'');
        sb.append(", adresse='").append(adresse).append('\'');
        sb.append(", comptes=").append(comptes);
        sb.append('}');
        return sb.toString();
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
