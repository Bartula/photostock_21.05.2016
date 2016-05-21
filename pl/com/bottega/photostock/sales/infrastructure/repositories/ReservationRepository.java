package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Reservation;

import java.util.List;

/**
 * Created by bartosz.paszkowski on 21.04.2016.
 */
public interface ReservationRepository {
    public Reservation load(String nr);
    public Reservation load(Client client);
    public void save(Reservation reservation);

    Reservation findOpenedPer(Client Client);

    List<Reservation> find(String clientNr);
}
