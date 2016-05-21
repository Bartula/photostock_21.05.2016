package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartosz.paszkowski on 16.04.2016.
 */
public class ProductNotAvailableException extends RuntimeException {

    private String number;
    private Class clazz;

    /**
     *
     * @param message
     * @param number business number
     * @param calzz thrower type
     */

    public ProductNotAvailableException(String message, String number, Class calzz){
        super(message);
        this.number = number;
        this.clazz = calzz;
    }

    public Class getClazz(){
        return clazz;
    }
    public String getNumber(){
        return number;
    }

    // komunikat
    // nr obiektu
    //typ obiektu
}
