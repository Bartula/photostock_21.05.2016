package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz.paszkowski on 26.04.2016.
 */
public class ProductsCatalog {
    private ProductRepository productRepository = new FakeProductRepository();


    public List <Product> find(String nameFragment){
        List<Product> searchList = new ArrayList<>();
        for (Product product : productRepository.getProductsList()) {

            if (product.getTitle().contains(nameFragment))
                searchList.add(product);
        }
        return searchList;
    }

    public List <Product> find(Money priceFrom, Money priceTo){
        List<Product> searchList = new ArrayList<>();
        for (Product product : productRepository.getProductsList()){
            if(product.calculatePrice().ge(priceFrom)&& product.calculatePrice().le(priceTo))
                searchList.add(product);
        }
        return searchList;
    }
    public List <Product> find(String[] tags, String author, Money nimPrice, Money maxPrice, String tagOne, String tagTwo,boolean acceptNotAvailable){
       /* List<Product> searchList = new ArrayList<>();

        for (Product product : productRepository.getProductsList()){
            for (int i = 0; i <= product.getTags().length-1; i++){
                if(product.getTags()[i].contains(tagOne)||product.getTags()[i].contains(tagTwo))
                searchList.add(product);
            }
        }*/
        return productRepository.find(tags,author,nimPrice,maxPrice,acceptNotAvailable);
    }
}
