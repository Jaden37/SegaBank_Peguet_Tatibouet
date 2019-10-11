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
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner( System.in );

    public static void main(String[] args) {
        int codeResponse = 0;
        boolean first = true;
        do {
            if (!first) {
                System.out.println("Menu introuvable, veuillez retaper un code valide");
            }
            System.out.println("____________________________________________________");
            System.out.println("__________________MENU SEGA BANK____________________");
            System.out.println("____________________________________________________");
            System.out.println("__________________Actions AGENCE____________________");
            System.out.println("1 - Créer une agence");
            System.out.println("2 - Modifier une agence");
            System.out.println("3 - Supprimer une agence");
            System.out.println("__________________ACTIONS COMPTES____________________");
            System.out.println("4 - Lister les comptes");
            System.out.println("5 - Rechercher un compte");
            System.out.println("__ Simple____________________");
            System.out.println("6 - Créer un compte simple");
            System.out.println("7 - Modifier un compte simple");
            System.out.println("8 - Supprimer un compte simple");
            System.out.println("__ Epargne____________________");
            System.out.println("9 - Créer un compte épargne");
            System.out.println("10 - Modifier un compte épargne");
            System.out.println("11 - Supprimer un compte épargne");
            System.out.println("__ Payant_____________________");
            System.out.println("12 - Créer un compte payant");
            System.out.println("13 - Modifier un compte payant");
            System.out.println("14 - Supprimer un compte payant");
            System.out.println("____________________________________________________");
            System.out.println("15 - Quitter");
            System.out.print("Entrez votre choix : ");
            try {
                codeResponse = sc.nextInt();
            } catch (InputMismatchException e) {
                codeResponse = -1;
            } finally {
                sc.nextLine();
            }
            first = false;

        } while (codeResponse < 1 || codeResponse > 15);
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
