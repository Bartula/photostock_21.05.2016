package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartosz.paszkowski on 03.04.2016.
 */
public interface Product {

    public boolean isAvailable();
    public Money calculatePrice();
    public void cancel();
    public void reversePer(Client client);
    public void unReservePer(Client client);

    String getNumber();

    String getTitle();

    String[] getTags();

    String[] export();
}
