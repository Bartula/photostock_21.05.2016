package pl.com.bottega.randomText;

import java.util.Scanner;

/**
 * Created by bartosz.paszkowski on 06.04.2016.
 */
public class RandomTextConsoleApplication {
    public static void main(String[] args) {

        String str = ("Nie, ma, znaczenia kolejnosc. liter skladajacych sie na dane slowo");
        System.out.println(str);
        RandomText.Shuffle(str);

       /* Scanner enteredText = new Scanner(System.in);
        System.out.println("Enter your text: ");
        String text = new String(enteredText.nextLine());
        RandomText.Splitter(text);
*/


    }
}
