package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.ClientStatus;
import pl.com.bottega.photostock.sales.model.DataDoesNotExistException;
import pl.com.bottega.photostock.sales.model.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bartosz.paszkowski on 20.04.2016.
 */
public class FakeClientRepository implements ClientRepository {
    private static Map<String, Client> fakeDatabase = new HashMap<>();

    static {
        Client c1 = new Client("nr1", "Janusz", "ksiezyc", ClientStatus.STANDARD,new Money(100.00, "PLN"), "Sollers");
        Client c2 = new Client("nr2", "Zegrzysław", "tajny", ClientStatus.STANDARD,new Money(200.00, "PLN"), "Sollers");

        fakeDatabase.put(c1.getNumber(), c1);
        fakeDatabase.put(c2.getNumber(), c2);
    }

    @Override
    public Client load(String name) {
        Client client = fakeDatabase.get(name);
        if (client == null)
            throw  new DataDoesNotExistException(client.getNumber(), "Client does not exist"); // TODO wprowadzic własny wyjątek DataDoesNotExist
        return client;
    }

    @Override
    public void save(Client client) {
        if (client.getNumber() == null)
            client.setNumber(UUID.randomUUID().toString());
        fakeDatabase.put(client.getNumber(), client);
    }


}

