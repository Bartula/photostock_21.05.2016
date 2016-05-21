package pl.com.bottega.commons.math.probility;

/**
 * Created by bartosz.paszkowski on 16.04.2016.
 */
public class ProbabilityTestConsoleApp {
    public static void main(String[] args) {

        shouldCreateFractionRepresentation();
        canNotCreateFractionRepresentationIfValueGTOne();

        shouldCreatePercentageRepresentation();
        canNotCreatePercentageRepresentationIfValueGTHundred();

        shouldCalculateIfDifferentRepresentation();
        shouldEqualsZeroIfOneIsZero();
    }

    private static void shouldEqualsZeroIfOneIsZero() {
    }

    private static void shouldCalculateIfDifferentRepresentation() {
        Probability bothEvents = Probability.fromFraction(0.5).and(Probability.fromPercentage(50)).and(Probability.fromFraction(1));
        System.out.println("wynik: " + bothEvents);
    }

    private static void canNotCreatePercentageRepresentationIfValueGTHundred() {
        try {
            Probability pr = Probability.fromPercentage(190.5);
            throw new RuntimeException("powinien byc wyjatek");
        }
        catch(IllegalArgumentException ex){

        }
        //spodziewamy sie wyjatku
    }

    private static void shouldCreatePercentageRepresentation() {
        Probability pr = Probability.fromPercentage(90.5);
    }

    private static void canNotCreateFractionRepresentationIfValueGTOne() {
        try {
            Probability pr = Probability.fromFraction(1.5);
            throw new RuntimeException("powinien byc wyjatek");
        }
        catch(IllegalArgumentException ex){

        }
        //powinien być wyjątek
    }

    private static void shouldCreateFractionRepresentation() {
        Probability pr = Probability.fromFraction(0.5);
    }

}
