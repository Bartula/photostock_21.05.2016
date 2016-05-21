package pl.com.bottega.commons.math.formatter;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by bartosz.paszkowski on 02.04.2016.
 */
public class FormatterConsoleApplication {
    public static void main(String[] args) {
        shouldFormatDigits();
    }

    private static void shouldFormatDigits() {
        Formatter formatter1 = new Formatter(123456);
        String[] digits = formatter1.formatDigits(Formatter.FormattingLanguage.PL);
        String[] digits2 = formatter1.formatDigits(Formatter.FormattingLanguage.EN);
        System.out.println(Arrays.toString(digits));
        System.out.println(Arrays.toString(digits2));

    }
}
