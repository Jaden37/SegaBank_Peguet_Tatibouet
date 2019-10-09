import bo.*;

public class App {
    public static void main(String[] args) {
        CoEpargne ce = new CoEpargne(1, 1000.00, 1, 10);
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

        CoSimple cs = new CoSimple(3, 1000, 3, 500);
        cs.retrait(1200);
        System.out.println(cs.toString());
    }
}
