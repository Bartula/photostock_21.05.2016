package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.commons.math.Fraction;

import java.util.Currency;

/**
 * Created by bartosz.paszkowski on 12.03.2016.
 */
public class Money {

    private static final Fraction HUNDRED = new Fraction(100,1);
    private static final Fraction ONE_THOUSANDTH = new Fraction(1,1000);
    private static final Fraction ONE_HUNDREDTH = new Fraction(1,100);

    private Fraction value;// TODO zmienic na Fraction
    private final Currency currency; // TODO zamienic na użycie klasy Currency z biblioteki Javy

    public Money(Double cash, String currency) {
        if (cash<0)
            throw new IllegalArgumentException("Amount can not be less than zero");
        String[] arr = String.valueOf(cash).split("\\.");
        int whole = Integer.parseInt(arr[0]); // część całkowita kwoty
        int decimal = decimals(arr[1]); // centy, grosze itp
        int nominator = whole*100 + decimal;
        int denominator = 100;

        Fraction value = new Fraction(nominator,denominator);
        this.value = value;
        this.currency = Currency.getInstance(String.valueOf(currency));
    }
    public Money(Double cash, Currency currency) {
        if (cash<0)
            throw new IllegalArgumentException("Amount can not be less than zero");
        String[] arr = String.valueOf(cash).split("\\.");
        int whole = Integer.parseInt(arr[0]); // część całkowita kwoty
        int decimal = decimals(arr[1]); // centy, grosze itp
        int nominator = whole*100 + decimal;
        int denominator = 100;

        Fraction value = new Fraction(nominator,denominator);
        this.value = value;
        this.currency = currency;
    }

    public Money(int integerValue, int cents, String currency){
        String str = String.valueOf(cents);
        int decimal = decimals(str);
        //double lengthOfCents = str.length();
        //int arg = (int)Math.pow(10,lengthOfCents);
        int nominator = integerValue*100 + decimal;
        int denominator = 100;
        Fraction value = new Fraction(nominator,denominator);
        this.value = value;
        this.currency = Currency.getInstance(String.valueOf(currency));
    }

    public Money(int integerValue, int cents, Currency currency){
        String str = String.valueOf(cents);
        int decimal = decimals(str);
        //double lengthOfCents = str.length();
        //int arg = (int)Math.pow(10,lengthOfCents);
        int nominator = integerValue*100 + decimal;
        int denominator = 100;
        Fraction value = new Fraction(nominator,denominator);
        this.value = value;
        this.currency = currency;
    }

    public Money(Fraction value, String currency) {
        this.value = value;
        this.currency = Currency.getInstance(String.valueOf(currency));
    }

    public Money(Fraction value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money add (Money amount){
        if (!currency.equals(amount.currency))
            throw new IllegalArgumentException("Can not add if different currency");
        return new Money(value.add(amount.value), currency);
    }

    public Money substract (Money amount){
        if (!currency.equals(amount.currency))
            throw new IllegalArgumentException("Can not subtract if different currency");
        return new Money(value.subtract(amount.value),currency);
    }

    public Money multiple (int ratio){
        int multi = value.getNominator()*ratio;
        int denom = value.getDenominator();
        Fraction frac = new Fraction(multi,denom);
        return new Money(frac,currency);
    }

    public Money multiple (double ratio){
        int ratio2 = (int) ratio;
        if (ratio % ratio2 == 0){
            return (multiple(ratio2));
        }else{
            String[] arr = String.valueOf(ratio).split("\\.");
            int whole = Integer.parseInt(arr[0]); // część całkowita kwoty
            int decimal = Integer.parseInt(arr[1]); // centy, grosze itp
            int nomin = whole*(10^arr[1].length()) + decimal;
            int denomin = 10^arr[1].length();
            Fraction frac = new Fraction(value.getNominator()*nomin, value.getDenominator()*denomin);
            return new Money(frac,currency);
        }
    }

  @Override
    public boolean equals(Object mon2){
        /*if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!value.equals(money.value)) return false;
        return currency.equals(money.currency);*/
       if (this == mon2) return true;
       if (mon2 == null || getClass() != mon2.getClass()) return false;

       Money money2 = (Money) mon2;

        if (this.currency.equals(money2.currency)){
            if (value.lt(HUNDRED) || money2.value.lt(HUNDRED) ){
                boolean delta = value.subtract(money2.value).le(ONE_THOUSANDTH); // UWAGA !! nie uwzględnia znaku poodjeciu może być ujemne
                return delta;
            }else{
                boolean delta = value.subtract(money2.value).le(ONE_HUNDREDTH);
                return delta;
            }
        }
        return false;
    }
    @Override
    public int hashCode (){
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return value + " " + currency;
    }

    /**
     *
     * @param val
     * @return true if this is grater or equals than val
     */
    public boolean ge(Money val){
        return this.value.ge(val.value);
    }
    /**
     *
     * @param val
     * @return true if this is less or equals than val
     */
    public boolean le (Money val){
        return this.value.le(val.value);
    }

    /**
     *
     * @param val
     * @return true if this is less than val
     */
    public boolean lt (Money val){
        return this.value.lt(val.value);
    }

    /**
     *
     * @param val
     * @return true if this is grater than val
     */
    public boolean gt(Money val){
        return this.value.gt(val.value);
    }

    public Money getZero(){
        Fraction frac = new Fraction(0,1);
        return new Money(frac, currency);
    }
    public Fraction getValue(){
        return value;
    }
    public Currency getCurrency(){
        return currency;
    }

    public int cents() { //TODO zmienić na Money ???
        return getValue().getNominator();
    }

    private int decimals (String decimal){
        if (decimal.length() == 1) {
            int dec = Integer.parseInt(decimal)*10;
            return dec;
        }
        if (decimal.length() == 2){
            return Integer.parseInt(decimal);
        }
        return 1;
    }
}


