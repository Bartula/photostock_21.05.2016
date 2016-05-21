package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.List;

/**
 * Created by bartosz.paszkowski on 12.03.2016.
 */
public class ConsoleApplication {

    public static void main(String[] args) {

        Client client = new Client("Zenobiusz", "ul. X",ClientStatus.STANDARD, new Money (100.00, "PLN")); //w nawiasie parametry powiązane z konstruktorem

        ClientRepository clientRepository = new FakeClientRepository();
        Client janusz = clientRepository.load("nr1");

        LightBoxRepository lightBoxRepository = new FakeLightBoxRepository();
        LightBox lbx = lightBoxRepository.load("nr1");

        //LightBox lbx = new LightBox(client);
        lbx.changeName("piły");
        System.out.println(lbx.getName());
        lbx.close();

        lbx.changeName("piłki");
        System.out.println(lbx.getName());

        if (true)
            return;

        Picture pic1 = new Picture("1-1-1", new Money (2.00, "PLN"),new String[]{"piła", "drewno"}, false);
        Picture pic2 = new Picture("1-1-2", new Money (3.00, "PLN"),new String[]{"piła", "drewno"}, true);
        Picture pic3 = new Picture("1-1-3", new Money (4.00, "PLN"),new String[]{"piła", "drewno"}, true);

        //Reservation reservation = new Reservation(client) ;
        ReservationRepository reservationRepository = new FakeReservationRepository();
        Reservation reservation = reservationRepository.load("nr1");

        //=============================================


        reservation.add(pic1);
        reservation.add(pic2);
        reservation.add(pic3);

        //sytuacja niedozwolona - ponowne dodanie tych samych pozycji nie powinno być możliwe (add jeszcze tego nie obsługuje)
        reservation.add(pic1);
        reservation.add(pic2);
        reservation.add(pic3);

        int count = reservation.getItemsCount(); //jeszcze nie działa
        System.out.println("Ilośćpozycji rezerwacji: " + count); //powinny być tylko 2 (jedno zdjęcie jest niedostępne) pomimo6 dodań

        //symulacja tego, że jakieśzdjęcie wycofano z handlu
        pic2.cancel();

        Offer offer = reservation.generateOffer(); //jeszcze nie działa, generateOffer zwraca null
        count = offer.getItemsCount();
        System.out.println("Ilośc pozycji oferty: " + count); //powinno być 1,dwa zdjęcia są już niedostępne

        Money offerTotalCost = offer.getTotalCost();
        boolean canAfford  =client.canAfford(offerTotalCost,client);

        if (canAfford){
            client.charge(offerTotalCost, "Za zdjecia",client);
            List<Product> items = offer.getItems();
            Purchase purchase = new Purchase(client, items); //TODO przepakowac z oferty do zakupu
        }
        else{
            System.out.println("can not affort! You need" + offerTotalCost);
        }



    /*
    LightBox lightBox1 = new LightBox();
    LightBox lightBox2 = new LightBox();
    LightBox lightBox3 = new LightBox();

    Picture pictureSaw = new Picture();
    lightBox1.add(pictureSaw);
    */
    }
}
