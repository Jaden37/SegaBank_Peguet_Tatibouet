package bo;

import java.util.ArrayList;

public class Agence {
    private int idAgence;
    private int code;
    private String addresse;
    private ArrayList<Compte> comptes;

    public int getIdAgence() {
        return idAgence;
    }
    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
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

    public Agence(int idAgence, int code, String addresse) {
        this.idAgence = idAgence;
        this.code = code;
        this.addresse = addresse;
    }

}
