package pl.com.bottega.photostock.sales.model;


import java.util.List;

/**
 * Created by bartosz.paszkowski on 12.03.2016.
 */
public class Offer {
    private final Client owner;
    private List<Product> items;
    private Money totalCost;


    public Offer(Client owner, List<Product> items) {
        this.owner = owner;
        this.items = items;
        this.totalCost = calculateTotalCost();
    }

    private Money calculateTotalCost(){
        Money sum = items.get(0).calculatePrice().getZero();//aby ustalić walutę
        for (Product p : items)
            sum = sum.add(p.calculatePrice());
        return sum;
    }

    public boolean sameAss (Offer offer, double secondReserv){

        return false;
    }
    public int getItemsCount(){

        return items.size(); //TODO
    }

    public Money getTotalCost(){

        return totalCost; //TODO
    }

    public List<Product> getItems() {
        return items;
    }
}
