package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartosz.paszkowski on 12.04.2016.
 */
public abstract class AbstractProduct implements Product{


    public enum TypeOfProduct{
        PICTURE, CLIP, SONG;
    }

    protected String number;
    protected String title;
    protected String[] tags;
    protected Money price;
    protected boolean isAvailable;


    public AbstractProduct(String number, Money price, String[] tags, boolean isAvailable) {
        this.number = number;
        this.price = price;
        this.tags = tags;
        this.isAvailable = isAvailable;
    }
    public AbstractProduct (String number, String title, String[] tags, Money price, boolean isAvailable) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.tags = tags;
        this.isAvailable = isAvailable;

    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public Money calculatePrice() {
        return price;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void reversePer(Client client) {

    }

    @Override
    public void unReservePer(Client client) {

    }
    public String getNumber() {
        return number;
    }

    public String getTitle(){
        return title;
    }
    public String[] getTags(){
        return tags;
    }

}
