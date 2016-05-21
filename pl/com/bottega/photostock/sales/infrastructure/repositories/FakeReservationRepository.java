package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bartosz.paszkowski on 20.04.2016.
 */
public class FakeReservationRepository implements ReservationRepository {

    private static Map<String, Reservation> fakeDatabase = new HashMap<>();

    /*static {
        ClientRepository repo = new FakeClientRepository();

        Client c1 = repo.load("nr1");
        Client c2 = repo.load("nr2");

        Reservation r1 = new Reservation("nr1", c1);
        Reservation r2 = new Reservation("nr2", c2);

        fakeDatabase.put(r1.getNumber(), r1);
        fakeDatabase.put(r2.getNumber(), r2);
    }*/


    @Override
    public Reservation load(String nr) {
        Reservation reservation = fakeDatabase.get(nr);
        if (reservation == null)
            throw  new DataDoesNotExistException(reservation.getNumber(), "Reservation does not exist"); // TODO wprowadzic własny wyjątek DataDoesNotExist
        return reservation;
    }
    @Override
    public Reservation load(Client client) {
        Reservation reservation = fakeDatabase.get(client);
        if (reservation == null)
            throw  new DataDoesNotExistException(reservation.getNumber(), "Reservation does not exist"); // TODO wprowadzic własny wyjątek DataDoesNotExist
        return reservation;
    }

    @Override
    public void save(Reservation reservation) {
        if (reservation.getNumber() == null)
            reservation.setNumber(UUID.randomUUID().toString());//symulacja generowania ID przez baze danych
        fakeDatabase.put(reservation.getNumber(),reservation);
    }

    @Override
    public Reservation findOpenedPer(Client client) {
        for(Reservation reservation : fakeDatabase.values()){
            if(reservation.getOwner().equals(client) && !reservation.isClosed())
                return reservation;
        }
        return null;
    }

    @Override
    public List<Reservation> find(String clientNr) {
        return null;
    }


}
