package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;

/**
 * Created by bartosz.paszkowski on 20.04.2016.
 */
public interface ClientRepository {
    public Client load(String numer);
    public void save(Client client);

    // canAfford, charge, recharge
}
