package pl.com.bottega.commons.application;

import pl.com.bottega.commons.math.Sorter;
import pl.com.bottega.commons.utils.Converter;
import pl.com.bottega.commons.utils.Display;

/**
 * Created by bartosz.paszkowski on 24.03.2016.
 */
public class BubbleSortTest2ConsoleApp {
    public static void main(String[] args) {

        int[] numbers = Converter.converter(args);
        int[] sortedTable = Sorter.sort(numbers);
        Display.display(numbers);
        Display.display(sortedTable);
    }
}
