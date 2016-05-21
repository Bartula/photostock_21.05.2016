package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Purchase;

/**
 * Created by bartosz.paszkowski on 23.04.2016.
 */
public interface PurchaseRepository {
    public Purchase load(String nr);
    public void save(Purchase purchase);
}
