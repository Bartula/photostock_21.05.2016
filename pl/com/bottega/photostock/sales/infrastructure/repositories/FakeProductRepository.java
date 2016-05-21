package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.DataDoesNotExistException;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.awt.peer.ListPeer;
import java.util.*;

/**
 * Created by bartosz.paszkowski on 17.04.2016.
 */
public class FakeProductRepository implements ProductRepository {

    private static Map<String, Product> fakeDatabase = new HashMap<>();

    static {
        Picture p1 = new Picture("nr1", new Money(10.00, "PLN"), new String[]{"ford", "mustang"},true);
        Picture p2 = new Picture("nr2", new Money (8.00, "PLN"), new String[]{"fiat", "multipla"},true);
        Picture p3 = new Picture("nr3", new Money (8.00, "PLN"), new String[]{"bmw", "8"},true);

        fakeDatabase.put(p1.getNumber(), p1);
        fakeDatabase.put(p2.getNumber(), p2);
        fakeDatabase.put(p3.getNumber(), p3);
    }


    @Override
    public Product load(String nr) {
        Product product = fakeDatabase.get(nr);
        if (product == null)
            throw new DataDoesNotExistException(nr, "Product does not exist"); // TODO wprowadzic własny wyjątek DataDoesNotExist
        return product;
    }

    @Override
    public void save(Product product) {
        fakeDatabase.put(product.getNumber(), product);
    }

    public ArrayList<Product> getProductsList() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.addAll(fakeDatabase.values());
        /*for (Product prod : fakeDatabase.values()) {
            productList.add(prod);
        }*/
        return productList;
    }

    @Override
    public List<Product> find(String[] tags, String author, Money nimPrice, Money maxPrice, boolean acceptNotAvailable) {
        return new ArrayList<>(fakeDatabase.values());


        /*List<Product> result = new LinkedList<>();

        if (emptyFilter(tags, author, nimPrice, maxPrice, acceptNotAvailable))
            return new ArrayList<>(fakeDatabase.values());

        for(Product product : fakeDatabase.values()){
            if (!(acceptNotAvailable || product.isAvailable()))
                continue;

            if (nimPrice != null && product.calculatePrice().gt(nimPrice))
                result.add(product);

            if (maxPrice != null && product.calculatePrice().lt(nimPrice))
                result.add(product);
        }

        return result;*/
    }

    private boolean emptyFilter(String[] tags, String author, Money nimPrice, Money maxPrice, boolean acceptNotAvailable) {
        if (acceptNotAvailable && (tags == null || tags.length == 0 )
                && author == null && nimPrice == null && maxPrice == null)
            return true;
        return false;
    }

}

