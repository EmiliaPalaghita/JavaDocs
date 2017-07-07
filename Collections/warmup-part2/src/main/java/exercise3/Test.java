package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emilia.Palaghita on 07-Jul-17.
 */
public class Test {
    public static void main(String[] args) {
        Map<ClasaA, BigDecimal> mapA = new HashMap<ClasaA, BigDecimal>();
        Map<ClasaB, BigDecimal> mapB = new HashMap<ClasaB, BigDecimal>();
        Map<ClasaC, BigDecimal> mapC = new HashMap<ClasaC, BigDecimal>();
        Map<ClasaD, BigDecimal> mapD = new HashMap<ClasaD, BigDecimal>();
        ClasaA a1 = new ClasaA("Gigel", "Popescu");
        ClasaA a2 = new ClasaA("Marcel", "Popescu");
        ClasaA a3 = new ClasaA("Marcel", "Pavel");

        mapA.put(a1, new BigDecimal(20));
        mapA.put(a2, new BigDecimal(18));
        mapA.put(a3, new BigDecimal(25));

        System.out.println(mapA.toString());

        System.out.println("-----------------------------");

        ClasaB b1 = new ClasaB("Gigel", "Popescu");
        ClasaB b2 = new ClasaB("Marcel", "Popescu");
        ClasaB b3 = new ClasaB("Marcel", "Pavel");

        mapB.put(b1, new BigDecimal(20));
        mapB.put(b2, new BigDecimal(18));
        mapB.put(b3, new BigDecimal(25));

        System.out.println(mapB.toString());

        System.out.println("-----------------------------");

        ClasaC c1 = new ClasaC("Gigel", "Popescu");
        ClasaC c2 = new ClasaC("Marcel", "Popescu");
        ClasaC c3 = new ClasaC("Marcel", "Pavel");

        mapC.put(c1, new BigDecimal(20));
        mapC.put(c2, new BigDecimal(18));
        mapC.put(c3, new BigDecimal(25));

        System.out.println(mapC.toString());

        System.out.println("-----------------------------");

        ClasaD d1 = new ClasaD("Gigel", "Popescu");
        ClasaD d2 = new ClasaD("Marcel", "Popescu");
        ClasaD d3 = new ClasaD("Marcel", "Pavel");

        mapD.put(d1, new BigDecimal(20));
        mapD.put(d2, new BigDecimal(18));
        mapD.put(d3, new BigDecimal(25));

        System.out.println(mapD.toString());

        System.out.println("-----------------------------");
    }
}
