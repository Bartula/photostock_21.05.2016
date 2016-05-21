package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Reservation;
import pl.com.bottega.photostock.sales.model.products.Picture;

/**
 * Created by bartosz.paszkowski on 23.04.2016.
 */
public class LightBoxManagement {

    private ClientRepository clientRepository = new FakeClientRepository();
    private LightBoxRepository lightBoxRepository = new FakeLightBoxRepository();
    private ProductRepository productRepository = new FakeProductRepository();
    private ReservationRepository reservationRepository = new FakeReservationRepository();

    public String create (String clientNr, String name){
        Client client = clientRepository.load(clientNr);
        LightBox lightBox = new LightBox(client);
        lightBoxRepository.save(lightBox);
        return lightBox.getNumber();
    }
    public void add(String lightBoxNr, String productNr){
        LightBox lbx = lightBoxRepository.load(lightBoxNr);
        Product product = productRepository.load(productNr);
        if (!(product instanceof Picture)){
            throw new IllegalArgumentException("Product is not a picture");
        }

        lbx.add((Picture) product);
        lightBoxRepository.save(lbx);
        productRepository.save(product);
    }

    public void share (String lightBoxNr, String clientNr){
        Client client = clientRepository.load(clientNr);
        LightBox lightBox = lightBoxRepository.load(lightBoxNr);
        if (!(lightBox.getCompany().equals(client.getCompany())))
            throw new IllegalArgumentException("Lightbox & Client are not in the same company");

        //czy wskazany uzytkownistaje się właścicielem lightboxa?
        lightBox = new LightBox(lightBoxNr,client,client.getCompany());//???????????????
        lightBoxRepository.save(lightBox);
    }

    public void reserve (String lightBoxNr, String productNr, String reservationNr){

        LightBox lbx = lightBoxRepository.load(lightBoxNr);
        Reservation reservation = reservationRepository.load(reservationNr);
        for (Picture pic : lbx.getPictures()){
            if (pic.getNumber().equals(productNr))
                reservation.add(pic);
        }
        //lbx.getPictures().stream().filter(pic -> productNr.equals(pic.getNumber())).forEach(pic-> reservation.add(pic));
        reservationRepository.save(reservation);

    }

    public String clone (String lightBoxNr){
        LightBox lbx = lightBoxRepository.load(lightBoxNr);
        LightBox lbxnew = new LightBox(lbx); // konstruktor kopiujący
        lightBoxRepository.save(lbxnew);
        return lbxnew.getNumber();
    }

    public void addAllToReserwation(String lightBoxNr) {
        LightBox lbx = lightBoxRepository.load(lightBoxNr);
        Reservation reservation = reservationRepository.findOpenedPer(lbx.getOwner());
        for (Picture pic : lbx.getPictures()){
            reservation.add(pic);
        }

    }
}
