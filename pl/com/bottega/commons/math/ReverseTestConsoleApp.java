package pl.com.bottega.commons.math;

/**
 * Created by bartosz.paszkowski on 19.03.2016.
 */
public class ReverseTestConsoleApp {
    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        Fraction f = new Fraction(a, b);


        try{
            Fraction fReversed = f.reverse();
            System.out.println("reversed " +fReversed.toString());
        }
        catch (IllegalStateException ex) {
            System.out.println("Nie mogę odwrócić bo " + ex.getMessage());
            return;
        }
    }
}
