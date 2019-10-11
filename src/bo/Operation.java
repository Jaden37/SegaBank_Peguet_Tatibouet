package bo;

import java.sql.Timestamp;

public class Operation {
    private int idOperation;
    private String nom;
    private double montant;
    private Timestamp dateHeure;
    private int idCompte;
    private Compte compte;

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Operation(int idOperation, String nom, double montant, Timestamp dateHeure, int idCompte) {
        this.idOperation = idOperation;
        this.nom = nom;
        this.montant = montant;
        this.dateHeure = dateHeure;
        this.idCompte = idCompte;
    }
}
