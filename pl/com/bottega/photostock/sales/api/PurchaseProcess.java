package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.*;

/**
 * Created by bartosz.paszkowski on 23.04.2016.
 */
public class PurchaseProcess {

    private ClientRepository clientRepository = new FakeClientRepository();
    private ReservationRepository reservationRepository = new FakeReservationRepository();
    private ProductRepository productRepository = new FakeProductRepository();
    private PurchaseRepository purchaseRepository = new FakePurchaseRepository();

    private Reservation create (String clientNr){
        Client client = clientRepository.load(clientNr);

        Reservation reservation = new Reservation(client);

        reservationRepository.save(reservation);
        return reservation;
    }

    public void add(String clientNr, String productNr){
        Client client = clientRepository.load(clientNr);
        Reservation reservation = reservationRepository.findOpenedPer(client);
        if (reservation == null){
            reservation = create(clientNr);
        }
        Product product = productRepository.load(productNr);
        reservation.add(product);

        reservationRepository.save(reservation);
        productRepository.save(product);
    }

    public Offer calculateOffer (String clientNr){
        Client client = clientRepository.load(clientNr);
        Reservation reservation = reservationRepository.findOpenedPer(client);
        if (reservation == null)
            throw new IllegalArgumentException("client does not have reservation");
        return reservation.generateOffer();
    }

    public void confirm (String clientNr){
        Client client = clientRepository.load(clientNr);
        Reservation reservation = reservationRepository.findOpenedPer(client);
        if (reservation == null)
            throw new IllegalArgumentException("client does not have reservation");

        confirm(client,reservation);
    }
/*
    public void confirm (String reservationNr, String payerNr){

        Reservation reservation = reservationRepository.load(reservationNr);
        Client client = clientRepository.load(payerNr);
        confirm(client,reservation);
    }*/


    private void confirm (Client client, Reservation reservation){
        Offer offer = reservation.generateOffer();
        if (client.canAfford(offer.getTotalCost(), client))
            client.charge(offer.getTotalCost(), "za coś" + reservation.getNumber(), client);

        Purchase purchase = new Purchase(client,offer.getItems());

        reservation.close();

        purchaseRepository.save(purchase);
        clientRepository.save(client);
    }
}

