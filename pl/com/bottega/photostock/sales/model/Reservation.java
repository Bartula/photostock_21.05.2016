package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by bartosz.paszkowski on 12.03.2016.
 */
public class Reservation {


    private Client owner;
    private List<Product> items = new LinkedList<>();
    private String number;
    private boolean closed;


    public Reservation(Client owner) {
        this.owner = owner;
    }
    public Reservation(String number, Client owner) {
        this.number = number;
        this.owner = owner;
    }
    public void add (Product product) throws ProductNotAvailableException, IllegalArgumentException{
        //.... validacja do zrobienia !!!!!!
        if (!product.isAvailable())
            throw new ProductNotAvailableException("product is not avalable", product.getNumber(), Reservation.class);
        if (items.contains(product))
            throw new IllegalArgumentException("Products duplicated");

        items.add(product);
    }
    public void remove(Product product){
        boolean removed = items.remove(product);
        if(!removed)
            throw new IllegalArgumentException("Product never added");


    }
    public Offer generateOffer() {
        List<Product> result = new ArrayList<>();
        //tu zrobić!!!!!!!
        for (Product product : items) {
            if (product.isAvailable())
                result.add(product);
        }

        Comparator<Product> comparator = new PriceAndNameProductComparator();
        Collections.sort(result, comparator);

        for (Product product : items)
            System.out.println(product.getNumber());
        return new Offer(owner,result);
    }

    public int getItemsCount(){
        return items.size();
    }

    public Client getOwner(){
        return owner;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void close() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}
