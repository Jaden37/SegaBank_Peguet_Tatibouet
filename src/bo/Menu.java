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
        System.out.println("6 - Exporter les opérations d'un compte");
        System.out.println("__ Simple____________________");
        System.out.println("7 - Créer un compte simple");
        System.out.println("8 - Opération sur un compte simple");
        System.out.println("9 - Supprimer un compte simple");
        System.out.println("__ Epargne____________________");
        System.out.println("10 - Créer un compte épargne");
        System.out.println("11 - Opération sur un compte épargne");
        System.out.println("12 - Supprimer un compte épargne");
        System.out.println("__ Payant_____________________");
        System.out.println("13 - Créer un compte payant");
        System.out.println("14 - Opération sur un compte payant");
        System.out.println("15 - Supprimer un compte payant");
        System.out.println("____________________________________________________");
        System.out.println("16 - Quitter");
        System.out.println("____________________________________________________");
        System.out.print("Entrez votre choix : ");
    }

}
