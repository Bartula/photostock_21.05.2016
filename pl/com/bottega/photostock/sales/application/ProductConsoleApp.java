package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.ProductRepository;

import java.net.SocketTimeoutException;

/**
 * Created by bartosz.paszkowski on 04.05.2016.
 */
public class ProductConsoleApp {
    public static void main(String[] args) {
        ProductRepository productRepository = new FakeProductRepository();
        System.out.println(productRepository.getProductsList());
    }
}
