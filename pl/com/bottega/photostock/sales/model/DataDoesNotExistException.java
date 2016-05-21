package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartosz.paszkowski on 21.04.2016.
 */
public class DataDoesNotExistException extends RuntimeException {

    private String number;
    private Class clazz;

    /**
     *
     * @param message
     * @param number business number
     * @param clazz thrower type
     */

    public DataDoesNotExistException(String message, String number, Class clazz){
        super(message);
        this.number = number;
        this.clazz = clazz;
    }
    public DataDoesNotExistException(String number, String message){
        super(message);
        this.number = number;
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
