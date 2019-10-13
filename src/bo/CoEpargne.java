package bo;

public class CoEpargne extends Compte {
    private float tauxInteret;

    public float getTauxInteret() {
        return tauxInteret;
    }
    public void setTauxInteret(float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public CoEpargne() {
        super();
    }

    public CoEpargne(int idCompte, double solde, int idAgence, float tauxInteret) {
        super(idCompte, solde, idAgence);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public boolean versement(double montant) {
        if(montant > 0){
            solde += montant;
            System.out.println("Versement effectué");
            return true;
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
            return false;
        }
    }

    @Override
    public boolean retrait(double montant) {
        if(montant > 0 ){
            if((solde - montant) > 0){
                solde -= montant;
                System.out.println("Retrait effectué");
                return true;
            } else {
                System.out.println("Solde insuffisant pour ce retrait");
                return false;
            }
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
            return false;
        }
    }

    // Permet de calculer le taux d'intérêt
    public void calculTauxInteret(){
        solde += (solde * this.tauxInteret / 100);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoEpargne{");
        sb.append("idCompte=").append(idCompte);
        sb.append(", solde=").append(solde);
        sb.append(", tauxInteret=").append(tauxInteret);
        sb.append(", idAgence=").append(idAgence);
        sb.append('}');
        return sb.toString();
    }
}
