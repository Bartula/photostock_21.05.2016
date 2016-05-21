package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz.paszkowski on 10.05.2016.
 */
public class ClientManagement {

    private ClientRepository clientRepository = new FakeClientRepository();
    private ReservationRepository reservationRepository = new FakeReservationRepository();
    private PurchaseRepository purchaseRepository = new FakePurchaseRepository();

    public String register(String name, String login, String email, String adress){
        Client client = new Client(null, name,adress, ClientStatus.STANDARD,new Money(0.00,"PLN"));
        clientRepository.save(client);
        return client.getNumber();
    }

    public List<Reservation> findReservations(String clientNr){

        //Client client = clientRepository.load(clientNr);
        //Reservation reservation = reservationRepository.load(client);
        return reservationRepository.find(clientNr);
    }

    public List<Purchase> findPurchases(String clientNr){

        return new ArrayList<>();
    }

    public void recharge(String clientNr, Money value){
        Client client = clientRepository.load(clientNr);
        client.recharge(value,client);
        clientRepository.save(client);
    }
}
