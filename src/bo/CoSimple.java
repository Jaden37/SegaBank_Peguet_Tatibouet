package bo;

public class CoSimple extends Compte {
    private double decouvert;

    public double getDecouvert() {
        return decouvert;
    }
    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public CoSimple() {
        super();
    }
    public CoSimple(int idCompte, double solde, int idAgence, double decouvert) {
        super(idCompte, solde, idAgence);
        this.decouvert = decouvert;
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
            if((solde - montant) > (0 - decouvert)){
                solde -= montant;
                System.out.println("Retrait effectué");
            } else {
                System.out.println("Solde insuffisant pour ce retrait");
            }
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoSimple{");
        sb.append("idCompte=").append(idCompte);
        sb.append(", solde=").append(solde);
        sb.append(", decouvert=").append(decouvert);
        sb.append(", idAgence=").append(idAgence);
        sb.append('}');
        return sb.toString();
    }
}
