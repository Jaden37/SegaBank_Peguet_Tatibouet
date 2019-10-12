package bo;

public class Menu  {
    public Menu() {}

    public static void getMenu(){
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
    }

}
