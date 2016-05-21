package pl.com.bottega.photostock.sales.infrastructure.repositories;
import pl.com.bottega.photostock.sales.model.DataDoesNotExistException;
import pl.com.bottega.photostock.sales.model.Purchase;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bartosz.paszkowski on 23.04.2016.
 */
public class FakePurchaseRepository implements PurchaseRepository{

    private static Map<String, Purchase> fakeDatabase = new HashMap<>();

    @Override
    public Purchase load(String nr) {
        Purchase purchase = fakeDatabase.get(nr);
        if (purchase == null)
            throw  new DataDoesNotExistException(purchase.getNumber(), "Reservation does not exist"); // TODO wprowadzic własny wyjątek DataDoesNotExist
        return purchase;
    }

    @Override
    public void save(Purchase purchase) {
        if (purchase.getNumber() == null)
            purchase.setNumber(UUID.randomUUID().toString());//symulacja generowania ID przez baze danych
        fakeDatabase.put(purchase.getNumber(),purchase);
    }
}
