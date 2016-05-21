package pl.com.bottega.commons.utils;

/**
 * Created by bartosz.paszkowski on 30.03.2016.
 */
public class Converter {

    public static int[] converter(String[] convertTable) {
        int [] table = new int [convertTable.length];
        for (int i = 0; i < convertTable.length; i++ ){
            table[i] = Integer.parseInt(convertTable[i]);
        }
        return table;
    }
}
