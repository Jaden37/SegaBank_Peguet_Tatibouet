import bo.*;
import dal.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private AgenceDAO adao = new AgenceDAO();
    private CoSimpleDAO csdao = new CoSimpleDAO();
    private CoEpargneDAO cedao = new CoEpargneDAO();
    private CoPayantDAO cpdao = new CoPayantDAO();
    private Agence agence_en_cours = new Agence();

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        int codeResponse = 0;
        boolean first = true;
        do {
            if (!first) {
                System.out.println("Menu introuvable, veuillez retaper un code valide");
            }
            Menu.getMenu();
            try {
                codeResponse = sc.nextInt();
            } catch (InputMismatchException e) {
                codeResponse = -1;
            } finally {
                sc.nextLine();
            }
            first = false;

        } while (codeResponse < 1 || codeResponse > 15);

        switch ( codeResponse ) {
            case 1:
                System.out.println("Choix 1");
                choix1();
                break;
            case 2:
                System.out.println("Choix 2");
                break;
            case 3:
                System.out.println("Choix 3");
                break;
            case 4:
                System.out.println("Choix 4");
                break;
            case 5:
                System.out.println("Choix 5");
                break;
            case 6:
                System.out.println("Choix 6");
                break;
            case 7:
                System.out.println("Choix 7");
                break;
            case 8:
                System.out.println("Choix 8");
                break;
            case 9:
                System.out.println("Choix 9");
                break;
            case 10:
                System.out.println("Choix 10");
                break;
            case 11:
                System.out.println("Choix 11");
                break;
            case 12:
                System.out.println("Choix 12");
                break;
            case 13:
                System.out.println("Choix 13");
                break;
            case 14:
                System.out.println("Choix 14");
                break;
            case 15:
                System.out.println("Choix 15");
                break;
        }
    }

    public static void testBDD() throws IOException {
        Agence ag = new Agence(2,  "A380", "15 rue du crack boursier");
        Agence ag2 = new Agence(2,  "EP56", "18 route de la fortune");
        AgenceDAO adao = new AgenceDAO();
        //ag = adao.create(ag);
        //ag = adao.update(ag, ag2);
        //System.out.println(ag.toString());
        //System.out.println(adao.findAll(ag).toString());
        //System.out.println(adao.findById(ag.getIdAgence()).toString());
        //adao.delete(cp.getIdCompte());

        CoPayant cp = new CoPayant(2, 8000, 1);
        CoPayantDAO cpdao = new CoPayantDAO();
        cp = cpdao.create(cp);
        CoPayant CoPTempo = new CoPayant(cp.getIdCompte(), cp.getSolde(), cp.getIdAgence());
        CoPTempo.versement(1500);
        cp = cpdao.update(cp, CoPTempo);
        OperationDAO odao = new OperationDAO();
        odao.listerOperations(cp.getIdCompte());
        //System.out.println(cp.toString());
        //System.out.println(cpdao.findAll(cp).toString());
        System.out.println(cpdao.findById(28).toString());
        //cpdao.delete(cp.getIdCompte());
        Agence agence = new Agence(1, "Nantes", "2 rue des madelaines");
        agence.ListerComptes(cpdao.findAll(cp));
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

    public static void choix1(){
        //oublie pas de rajouter des sout pour le bien de l'utilisateur :-)

        //rentrer le nom de l'agence, le code l'adresse

        //Agence newAgence = new Agence(les infos saisis par l'utilisateur)

            //l'agence en cours permettra de mettre l'id agence dans la futur création des comptes
            //faire donc un test lors de la création d'un compte que agence en cours soit bien défini
            //permettre à l'utilisateur via une fonction de changer l'agence en cours

        //agence_en_cours = adao.create(newAgence);
    }

}
