package pl.com.bottega.commons.math.formatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by bartosz.paszkowski on 02.04.2016.
 */
public class Formatter {

    public enum FormattingLanguage { //wewnątrz klasy mogemiećinne klasy
        PL, EN
    }


    List<Byte> digits = new ArrayList<>();

    /*
     public Formatter (long number){
        while (number !=0){
            long digit> number % 10;
            digits.add((byte)digit);
            number = number / 10 //zmiana kopii parametru konstruktora
        }
     }
     */

    public Formatter (long number){
        recursiveCutter(number);
        Collections.reverse(digits);
    }

    private void recursiveCutter(long number) {
        long digit = number % 10;
        digits.add((byte)digit);
        long newValue = number / 10;
        if (newValue !=0)
            recursiveCutter(newValue);
    }

    /**
     *
     * @param lang
     * @return dla 123 zwraca sto dwadzieścia trzy
     */
    public String formatNumber(String lang){
        return null;
    }

    /**
     *
     * @param lang
     * @return dla 123 zwraca jeden dwa trzy
     */
    public String[] formatDigits(FormattingLanguage lang) {
        String[] result = new String[digits.size()];

        int nr = 0;
        for (Byte digit : digits) {
            result[nr++] = generateDigit2(digit, lang);
        }
        return result;
    }

    private static final String[][] DICTIONARY = {
            {"zero", "jeden", "dwa", "trzy","cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"},
            {"zero","one", "two", "three", "four", "five", "six", "seven","eight", "nine"}
    };


    private String generateDigit2(Byte digit,FormattingLanguage lang) {
        /*byte langN;
        switch (lang){
            case PL:
                langN = 0;
                break;
            case EN:
                langN = 1;
                break;
            default:
                throw new IllegalArgumentException(lang+ "is not supported");
        }*/
        return  DICTIONARY[lang.ordinal()][digit];
    }

    private String generateDigit(Byte digit, String lang) {

        switch (lang){
            case "pl":
                switch (digit){
                    case 0:
                        return "zero";
                    case 1:
                        return "jeden";
                    case 2:
                        return  "dwa";
                    case 3:
                        return "trzy";
                    case 4:
                        return  "cztery";
                    case 5:
                        return "pięć";
                    case 6:
                        return "sześć";
                    case 7:
                        return "siedem";
                    case 8:
                        return "osiem";
                    case 9:
                        return "dziewięć";
                }
                break;
            case "en":
                return "i don't know english";
            default:
                throw new IllegalArgumentException(lang+ "is not supported");
        }

        throw new RuntimeException("coś dziwnego z danymi" +lang+ " "+ digit);
    }

}
