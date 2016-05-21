package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz.paszkowski on 17.04.2016.
 */
public interface ProductRepository {
    public Product load(String nr);
    public void save(Product product);
    public ArrayList<Product> getProductsList();

    List<Product> find(String[] tags, String author, Money nimPrice, Money maxPrice, boolean acceptNotAvailable);
}
