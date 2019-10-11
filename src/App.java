import bo.*;
import dal.AgenceDAO;
import dal.CoEpargneDAO;
import dal.CoPayantDAO;
import dal.PersistenceManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        Agence ag = new Agence(2,  "A380", "15 rue du crack boursier");
        Agence ag2 = new Agence(2,  "EP56", "18 route de la fortune");
        AgenceDAO adao = new AgenceDAO();
        ag = adao.create(ag);
        ag = adao.update(ag, ag2);
        System.out.println(ag.toString());
        System.out.println(adao.findAll(ag).toString());
        System.out.println(adao.findById(ag.getIdAgence()).toString());
        //adao.delete(cp.getIdCompte());
    }

    public static void testClasses(){
        CoEpargne ce = new CoEpargne(13, 1000, 10, 10);
        System.out.println(ce.toString());
        ce.calculTauxInteret();
        System.out.println(ce.toString());
        ce.versement(100.30);
        ce.versement(-330);
        ce.retrait(10);
        ce.retrait(10000);
        System.out.println(ce.toString());

        CoPayant cp = new CoPayant(2, 2000, 2);
        cp.versement(100);
        cp.retrait(10);
        System.out.println(cp.toString());

        CoEpargne cep = new CoEpargne(14, 3000, 1000, 30);
        cep.retrait(1200);
        System.out.println(ce.toString());
    }
}
