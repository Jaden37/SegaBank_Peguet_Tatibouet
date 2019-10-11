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
    public void versement(double montant) {
        if(montant > 0){
            solde += montant;
            System.out.println("Versement effectué");
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
        }
    }

    @Override
    public void retrait(double montant) {
        if(montant > 0 ){
            if((solde - montant) > 0){
                solde -= montant;
                System.out.println("Retrait effectué");
            } else {
                System.out.println("Solde insuffisant pour ce retrait");
            }
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
        }
    }

    // Permet de calculer le taux d'intérêt
    public void calculTauxInteret(){
        solde += (solde * this.tauxInteret / 100);
    }

}
