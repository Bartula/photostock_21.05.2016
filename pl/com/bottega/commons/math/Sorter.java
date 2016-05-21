package pl.com.bottega.commons.math;

/**
 * Created by bartosz.paszkowski on 22.03.2016.
 */
public class Sorter {

    private String bubbleNumbers;

    public static int[] sort(int[] number){
        return sortBubble(number);
    }

    public static void sortInPlace(int[] number){
        sortBubble(number);
    }

    private static int[] sortBubble (int[] number){
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for (int i =0; i< number.length; i++){
                if (i< number.length && number[i]> number [i+1]){
                int cup = number [i];
                number [i] = number[i+1];
                number [i+1] = cup;
                sorted = false;
                }
            }
        }
        return number;

    }

}
