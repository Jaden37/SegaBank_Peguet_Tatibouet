package bo;

import dal.IAgenceDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu  {
    public Menu() {}

    public void getMenu(){
        Scanner sc = new Scanner( System.in );
        int codeResponse = 0;
        boolean first = true;
        do {
            if (!first) {
                System.out.println("Menu introuvable, veuillez retaper un code valide");
            }
            System.out.println("=====================================================");
            System.out.println("                    MENU SEGA BANK                        ");
            System.out.println("=====================================================");
            System.out.println("__________________Actions AGENCE____________________");
            System.out.println("1 - Créer une agence");
            System.out.println("2 - Modifier une agence");
            System.out.println("3 - Supprimer une agence");
            System.out.println("__________________ACTIONS COMPTES____________________");
            System.out.println("4 - Lister les comptes");
            System.out.println("5 - Rechercher un compte");
            System.out.println("__ Simple____________________");
            System.out.println("6 - Créer un compte simple");
            System.out.println("7 - Opération sur un compte simple");
            System.out.println("8 - Supprimer un compte simple");
            System.out.println("__ Epargne____________________");
            System.out.println("9 - Créer un compte épargne");
            System.out.println("10 - Opération sur un compte épargne");
            System.out.println("11 - Supprimer un compte épargne");
            System.out.println("__ Payant_____________________");
            System.out.println("12 - Créer un compte payant");
            System.out.println("13 - Opération sur un compte payant");
            System.out.println("14 - Supprimer un compte payant");
            System.out.println("____________________________________________________");
            System.out.println("15 - Quitter");
            System.out.println("____________________________________________________");
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

        switch ( codeResponse ) {
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
        }
    }

}
