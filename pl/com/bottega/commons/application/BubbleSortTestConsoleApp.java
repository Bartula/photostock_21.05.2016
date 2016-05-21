package pl.com.bottega.commons.application;

import pl.com.bottega.commons.math.Sorter;
import pl.com.bottega.commons.utils.Converter;
import pl.com.bottega.commons.utils.Display;

/**
 * Created by bartosz.paszkowski on 22.03.2016.
 */
public class BubbleSortTestConsoleApp {
    public static void main(String[] args) {

        int[] numbers = Converter.converter(args);
        Display.display(numbers);
        Sorter.sortInPlace(numbers);
        Display.display(numbers);
    }

}
