package pl.com.bottega.photostock.sales.infrastructure.repositories;

/**
 * Created by bartosz.paszkowski on 15.05.2016.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(Exception e) {
        super(e);
    }
}
