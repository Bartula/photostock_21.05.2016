package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 09.04.2016.
 */
public class MoneyTestConsoleApp {
    public static void main(String[] args) {
        shouldConstruct();

        shouldEqualsIfSameCurrency();
        canNotEqualsIfDifferentCurrency();

        //shouldAddHugeNumbers(); TODO z tym sobie nie radzi
        shouldAddSmallNumbers();
        canNotAddIfDifferentCurrency();

        shouldMultipleByTinyRatio();
        shouldMultipleByLargeRatio();

    }

    private static void shouldConstruct() {
        new Money(10.0, "PLN");
        new Money(10, 50, "PLN");
    }

    private static void shouldEqualsIfSameCurrency() {
        Money m1 = new Money(10.5, "PLN");
        Money m2 = new Money(10, 50, "PLN");

        boolean eq = m1.equals(m2);
        if (!eq)
            System.out.println("cos nie tak z porównaniem");
    }

    private static void canNotEqualsIfDifferentCurrency() {
        Money m1 = new Money(10.5, "PLN");
        Money m2 = new Money(10, 50, "PLN");

        boolean eq = m1.equals(m2);
        if (!eq)
            System.out.println("cos nie tak z porównaniem");
    }

    private static void shouldAddHugeNumbers() {
        Money m1 = new Money(100_000_000d, "PLN");
        Money m2 = new Money(300_000_000d, "PLN");

        Money expectedSum = new Money(400_000_000d,"PLN");

        Money sum = m1.add(m2);

        boolean eq = expectedSum.equals(sum);
        if (!eq)
            System.out.println("Coś nie tak z dodawaniem");
    }

    private static void shouldAddSmallNumbers() {
        Money m1 = new Money(10.5, "PLN");
        Money m2 = new Money(10, 50, "PLN");
        m1.add(m2);
    }

    private static void canNotAddIfDifferentCurrency() {

    }

    private static void shouldMultipleByTinyRatio() {
    }

    private static void shouldMultipleByLargeRatio() {

    }


}
