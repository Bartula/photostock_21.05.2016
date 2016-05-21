package pl.com.bottega.randomText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bartosz.paszkowski on 06.04.2016.
 */
public class RandomText {

    public static void Shuffle(String text){
        String[] parts = text.split(" ");       //dzielimy wyrazy
        List<String> parts2 = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
            for (int a=0; a< parts.length; a++) {
                parts2.add(parts[a]);
            }
            for (String str : parts2){      //dla każdego wyrazu
                builder.append(recursiveSymbol(str));
                builder.append(" ");
            }
        System.out.println(builder.toString()); //wyswietlenie zmienionego zdania
    }

    private static int getRandomNumberInRange (int min, int max){      //metoda generujaca liczby z przedzialu
        if (min > max)
            throw new IllegalArgumentException("max must be grater than min");
        Random rand = new Random();
        int randomNumber = rand.nextInt((max - min) + 1) + min;
        return randomNumber;
    }

    private static String recursiveSymbol(String str){
        if (str.contains(",")||str.contains(".")){
            if (str.length()>4){            // spelnione dla wyrazow dluzszych od 4
            char[] symbol = str.toCharArray();  // tablica znakow{ //jezeli zawiera
                for (int i = 1; i < str.length() - 2; i++) { // pętla na mozliwa ilosc zmian
                    int a = getRandomNumberInRange(1, str.length() - 3); //generowanie pierwszej zmiennej
                    int b = getRandomNumberInRange(1, str.length() - 3); // generowanie drugiel zmiennej
                    if (a == b || symbol[a] == symbol[b]) {    // warunek na sprawdzenie tych samych liczb lub tych samych znakow
                        return recursiveSymbol(str);
                    }
                    str = String.valueOf(cup(symbol,a,b));
                }
            }
        }
        else {
            if (str.length()>3){
                char[] symbol = str.toCharArray();
                for (int i = 1; i < str.length() - 2; i++) { // pętla na mozliwa ilosc zmian
                    int a = getRandomNumberInRange(1, str.length() - 2); //generowanie pierwszej zmiennej
                    int b = getRandomNumberInRange(1, str.length() - 2); // generowanie drugiel zmiennej
                    if (a == b || symbol[a] == symbol[b]) {    // warunek na sprawdzenie tych samych liczb lub tych samych znakow
                        return recursiveSymbol(str);
                    }
                    str = String.valueOf(cup(symbol,a,b));
                }
            }
        }
        return  str;
    }


    private static String cup(char[] symbol, int a, int b){
        char cup = symbol[a];       //podmiana liter w wyrazie
        symbol[a] = symbol[b];
        symbol[b] = cup;
        return String.valueOf(symbol);
    }

}





