package pl.com.bottega.commons.math;

import static pl.com.bottega.commons.math.Fraction.ONE;
import static pl.com.bottega.commons.math.Fraction.ZERO;

/**
 * Created by bartosz.paszkowski on 19.03.2016.
 */
public class FractionTestConsoleApp {
    public static void main(String[] args) {
        Fraction f1;                // deklaracje referencji, które mogę użyć dalej
        Fraction f2;

        try{
            f1 = new Fraction(8, 5);       // użycie referencji
            f2 = new Fraction(2);
        }
        catch (IllegalArgumentException ex){
            System.out.println("Złe dane wejściowe " + ex.getMessage());
            return;
        }


       // Fraction one = new Fraction (1,1);
       // Fraction zero = new Fraction(0,1);
       // Fraction onePrim = new Fraction(1,1);

        Fraction sum = ONE.add(f1);
        Fraction sum2 = ZERO.add(f1);
        // Fraction sum = f1.add(f2).add(Fraction.ONE).add(Fraction.ZERO); // wzorzec projektowy: Value Object (immutability)

        System.out.println(sum.toString());
    }
}
