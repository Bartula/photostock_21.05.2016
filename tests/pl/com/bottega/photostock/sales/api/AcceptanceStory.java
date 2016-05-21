package pl.com.bottega.photostock.sales.api;

import org.junit.Assert;
import org.junit.Test;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.List;

/**
 * Created by bartosz.paszkowski on 14.05.2016.
 */
public class AcceptanceStory {

    private static final Money INITIAL_MONEY = new Money(50.00, "PLN");

    public static final Money PRICE_1 = new Money(10.00,"PLN");
    public static final Money PRICE_2 = new Money(20.00,"PLN");
    public static final Money PRICE_3 = new Money(30.00,"PLN");

    public static final Money TOTAL_COST = PRICE_1.add(PRICE_2).add(PRICE_3);

    //tyle udzielamy limitu kredytu aby wystarczyłona różnicę między kosztem a tymco ma klient
    public static final Money CREDIT_LIMIT = TOTAL_COST.substract(INITIAL_MONEY);

    private ProductsCatalog catalog = new ProductsCatalog();
    private ClientManagement clientManagement = new ClientManagement();
    private LightBoxManagement lightBoxManagement = new LightBoxManagement();
    private PurchaseProcess purchaseProcess = new PurchaseProcess();
    private AdminPanel adminPanel = new AdminPanel();


    @Test
    public void story (){
        adminPanel.addProduct(AbstractProduct.TypeOfProduct.PICTURE,"nr1", PRICE_1, new String[] {"ford", "mustang"});
        adminPanel.addProduct(AbstractProduct.TypeOfProduct.PICTURE,"nr2", PRICE_2, new String[] {"bmw", "m6"});
        adminPanel.addProduct(AbstractProduct.TypeOfProduct.PICTURE,"nr3", PRICE_3, new String[] {"fiat", "multipla"});

        //użytkownik się rejestruje i doładowuje konto
        String clientNr = clientManagement.register("name", "login 1", "email@server.com", "adress");
        clientManagement.recharge(clientNr, INITIAL_MONEY);

        //admin awansuje klienta i daje mu limit kredytu
        adminPanel.promoteClient(clientNr);
        adminPanel.changeCreditLimit(clientNr,CREDIT_LIMIT);

        //użytkownik przeszukuje katalog dostępnych produktów
        List<Product> products = catalog.find(null,null,null,null,null,null,true);
        /*for(Product product : products)
            purchaseProcess.add(clientNr,product.getNumber());*/

        //użytkownik dodaje pierwszy produkt do lbx
        String lightBoxNr = lightBoxManagement.create(clientNr, "lightbox 1");
        lightBoxManagement.add(lightBoxNr,products.get(0).getNumber());

        //użytkownik dodaje drugi i trzeci produkt do rezerwacji
        purchaseProcess.add(clientNr, products.get(1).getNumber());
        purchaseProcess.add(clientNr, products.get(2).getNumber());

        //użytkownik dodaje do rezerwacji jeszcze produkt z lightboxa
        lightBoxManagement.addAllToReserwation(lightBoxNr);

        //użytkownik przegląda ofertę
        Offer offer = purchaseProcess.calculateOffer(clientNr);
        Assert.assertEquals(TOTAL_COST,offer.getTotalCost());

        //uzytkownik zatwierdza ofertę
        purchaseProcess.confirm(clientNr);

        //użytkownik przegląda swoje zakupy
        List<Purchase> purchases = clientManagement.findPurchases(clientNr);


    }
}
