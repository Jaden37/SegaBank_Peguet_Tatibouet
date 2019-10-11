package bo;

import java.sql.SQLOutput;

public class CoPayant extends Compte {
    public CoPayant() {
        super();
    }

    public CoPayant(int idCompte, double solde, int idAgence) {
        super(idCompte, solde, idAgence);
    }

    // Permet de créditer un compte payant
    @Override
    public void versement(double montant) {
        if(montant > 0){
            double commission = (montant * 5 /100);
            solde += (montant - commission);
            System.out.println("Versement effectué");
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
        }
    }

    // Permet de débiter un compte payant
    @Override
    public void retrait(double montant) {
        if(montant > 0 ){
            double commission = (montant * 5 /100);
            if((solde - (montant - commission)) > 0){
                solde -= (montant - commission);
                System.out.println("Retrait effectué");
            } else {
                System.out.println("Solde insuffisant pour ce retrait");
            }
        } else {
            System.out.println("Versement impossible. Entrer un montant positif");
        }
    }
}
