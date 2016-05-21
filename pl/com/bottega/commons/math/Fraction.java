package pl.com.bottega.commons.math;

import java.awt.datatransfer.SystemFlavorMap;

/**
 * Created by bartosz.paszkowski on 19.03.2016.
 */
public class Fraction {
    // stała, pole statyczne (pole klasy a nie obiektów tej klasy)
    public static final Fraction ONE = new Fraction(1,1);
    public static final Fraction ZERO = new Fraction(0,1);

    private final int nominator; 
    private final int denominator;

    /**                                     // tworzenie dokumentacji -> /** enter
     * Klasa modeluje ulamek
     *
     * @param nominator licznik ułamka
     * @param denominator mianownik ułamka
     *
     * @throws IllegalArgumentException gdy mianownik jest równy 0
     */

    public Fraction(int nominator, int denominator) throws IllegalArgumentException { // <- wyjątek RUNTIME EX
        this.nominator = nominator;
        this.denominator = denominator;
        if (denominator == 0)                       // obsługa mianownika = 0 WYJĄTEK
            throw new IllegalArgumentException("Denominator can not be zero");

    
    }

    public Fraction(int nominator) {
        //this(nominator,10); //nie chcemy walidować(sprawdzać poprawność) liczby 10
        this.nominator = nominator;
        this.denominator = 10;
    }

    /**
     *
     * @param literal ułamek w reprezentacji licznik/mianownik, np: 3/4
     */
    public Fraction(String literal) throws IllegalArgumentException{
        String [] parts = literal.split("/");
        if (parts.length != 2)
            throw new IllegalArgumentException("It is not fraction");
        //String part1 = parts [0];
        //String part2 = parts [1];

        try {
            this.nominator = Integer.parseInt(parts[0]);
            this.denominator = Integer.parseInt(parts[1]);
            if (this.denominator == 0)
                throw new IllegalArgumentException("Zero in denominator");
        }
        catch(NumberFormatException ex){        //nie musimy tego przepakowywać możemy rzucić NumberFormatException
            throw new IllegalArgumentException("It is not fraction", ex);
        }
    }


    public Fraction add(Fraction addend) {
        if (this.denominator == addend.denominator){
           int nominatorSum = this.nominator + addend.nominator;
            return new Fraction(nominatorSum, this.denominator);
        }
        else{
           int thisNominator = (this.nominator*addend.denominator) + (this.denominator*addend.nominator) ;
           int commonDenominator = this.denominator * addend.denominator;
           return new Fraction(thisNominator, commonDenominator);
        }
    }
    public Fraction subtract(Fraction value){
        if (this.denominator == value.denominator){
            int nominatorSubtract = this.nominator - value.nominator;
            return new Fraction(nominatorSubtract, this.denominator);
        }else{
            int thisNominator = (this.nominator*value.denominator) - (this.denominator*value.nominator) ;
            int commonDenominator = this.denominator * value.denominator;
            return new Fraction(thisNominator, commonDenominator);
        }
    }

    public String toString(){
        String result = spaceLine() + nom()+ "\n" + wholeNum()+ dashLine()+ "\n"+ spaceLine() + denom();
        return result;
    }

    private  String nom(){
        if (nominator > denominator) {
            int nom = this.nominator % this.denominator;
            String nom2 = String.valueOf(nom);
            return nom2;
        }
        return null;
    }
    private String wholeNum(){
        if (nominator > denominator) {
            int wholeNum = this.nominator / this.denominator;
            String wholeNum2 = String.valueOf(wholeNum);
            return wholeNum2;
        }
        return null;
    }
    private String denom(){
        if (nominator > denominator){
            String denom = String.valueOf(this.denominator);
            return denom;
        }
        return null;
    }
    private String spaceLine() {
        int sc = String.valueOf(wholeNum()).length();
        String str = "";
        StringBuilder builder = new StringBuilder(str);
        if (nominator > denominator) {
            for (int i = 0; i <= sc ; i++){
                builder.append(" ");
            }
        }
        return builder.toString();
    }
    private String dashLine() {
        String th = String.valueOf(this.denominator);
        int t = th.length();
        String str = "";
        StringBuilder builder = new StringBuilder(str);
        if (nominator > denominator){
            for (int i = 0; i <= t+1 ; i++) {
                builder.append("-");
            }
            //builder.append("\n");

            return builder.toString();
        }
        else {
            return null;
        }
    }

    public Fraction reverseV2()throws IllegalStateException {
        if (this.nominator == 0)
            throw new IllegalStateException("can not reverse zero");
        return new Fraction(this.denominator, this.nominator);
    }


    public Fraction reverse() throws IllegalStateException{
        //if (this.nominator == 0)   to juz nie jest potrzebne, ponieważ jest już dodany wyjatek na 0 w mianowniku
          //  return null;
        try{
        return new Fraction(this.denominator, this.nominator);
        }
        catch(IllegalArgumentException ex){             // wyjątek na sprawdzenie stanu
            throw new IllegalStateException("Zero can not be reserved", ex);// przepakowanie wyjątku Argument->State
        }
    }

    public boolean ge(Fraction value){
        if (denominator == value.denominator) {
            if (nominator >= value.nominator)
                 return true;
        }else{
            int nom1 = nominator*value.denominator;
            int nom2 = value.nominator*denominator;
            if (nom1 >= nom2)
                return true;
        }
        return false;
    }
    public boolean le(Fraction value){
        if (denominator == value.denominator) {
            if (nominator <= value.nominator)
                return true;
        }else{
            int nom1 = nominator*value.denominator;
            int nom2 = value.nominator*denominator;
            if (nom1 <= nom2)
                return true;
        }
        return false;
    }
    public boolean lt(Fraction value){
        if (denominator == value.denominator) {
            if (nominator < value.nominator)
                return true;
        }else{
            int nom1 = nominator*value.denominator;
            int nom2 = value.nominator*denominator;
            if (nom1 < nom2)
                return true;
        }
        return false;
    }
    public boolean gt(Fraction value){
        if (denominator == value.denominator) {
            if (nominator > value.nominator)
                return true;
        }else{
            int nom1 = nominator*value.denominator;
            int nom2 = value.nominator*denominator;
            if (nom1 > nom2)
                return true;
        }
        return false;
    }

    public int getNominator() {
        return nominator;
    }

    public int getDenominator() {
        return denominator;
    }
}
