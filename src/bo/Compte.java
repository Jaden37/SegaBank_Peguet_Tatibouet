package bo;

public abstract class Compte {
    protected int idCompte;
    protected double solde;
    protected int idAgence;
    protected Agence agence;

    public int getIdCompte() {
        return idCompte;
    }
    public double getSolde() {
        return solde;
    }
    public int getIdAgence() {
        return idAgence;
    }
    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }
    public Agence getAgence() {
        return agence;
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Compte() {
    }

    public Compte(int idCompte, double solde, int idAgence) {
        this.idCompte = idCompte;
        this.solde = solde;
        this.idAgence = idAgence;
    }

    abstract boolean versement(double montant);
    abstract boolean retrait(double montant);

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Compte{");
        sb.append("idCompte=").append(idCompte);
        sb.append(", solde=").append(solde);
        sb.append(", idAgence=").append(idAgence);
        sb.append('}');
        return sb.toString();
    }
}
