package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Picture;
import pl.com.bottega.photostock.sales.model.products.Song;

/**
 * Created by bartosz.paszkowski on 17.03.2016.
 */
public class ReservationTestConsoleApp {
    public static void main(String[] args) {
        ProductRepository repository = new FakeProductRepository();

        //Client zegrzyslaw =new Client("Zegrzysław", "tajny", ClientStatus.STANDARD,20); // TODO pobrac z repo
        ClientRepository clientRepository = new FakeClientRepository();
        Client zegrzyslaw = clientRepository.load("nr2");

        Product mustang = repository.load("nr1"); //new Picture("nr1", new String[]{"ford", "mustang"}, 10, true);
        Product mustang2 = repository.load("nr2"); //new Picture("nr1", new String[]{"ford", "mustang"}, 10, true);

        Picture grass = new Picture("n6", new Money(1.00, "PLN"), new String[]{"natura", "trawa" }, false);

        Song whiteTree = new Song("nr1","White Tree", "3:30", new String[]{"",""}, "New age", new Money (4.00, "PLN"), true, Song.Channel.STEREO, Song.File_Extension.MP3);
        Song bigBanana = new Song("nr2","Big Banana", "4:10", new String[]{"",""}, "Folk", new Money (5.00,"PLN"), true, Song.Channel.STEREO, Song.File_Extension.MP3);


        ReservationRepository reservationRepository = new FakeReservationRepository();
        Reservation reservation = reservationRepository.load("nr2");// TODO pobrac z repo
        //reservation.add(mustang);

        try{
            reservation.add(mustang);
            reservation.add(mustang2);
        }
        catch (ProductNotAvailableException ex){
            System.out.println(ex.getClass()+ " " + ex.getMessage()+ " " + ex.getNumber() );
        }

        repository.save(mustang);
        repository.save(mustang2);

        Offer ofertaDlaZegrzyslawa = reservation.generateOffer();
        int count = ofertaDlaZegrzyslawa.getItemsCount();
        System.out.println("Ilość pozycji w ofercie: " + count);
    }

}
