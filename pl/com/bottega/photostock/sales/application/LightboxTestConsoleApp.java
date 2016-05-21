package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.infrastructure.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeLightBoxRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.LightBoxRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.ArrayList;

/**
 * Created by bartosz.paszkowski on 19.03.2016.
 */
public class LightboxTestConsoleApp {
    public static void main(String[] args) {

        //Client Janusz = new Client("Janusz", "ksiezyc", ClientStatus.STANDARD, 10);
        ClientRepository clientRepository = new FakeClientRepository();
        Client janusz = clientRepository.load("nr1");

        LightBoxRepository lightBoxRepository = new FakeLightBoxRepository();
        LightBox lightBoxJanusza = lightBoxRepository.load("nr1");

        //LightBox lightBoxJanusza = new LightBox(Janusz, "Ogolne");
        //LightBox lightBoxJanuszaHome = new LightBox(Janusz, "Home" );
       // LightBox lightBoxJanuszaNature = new LightBox(Janusz, "Nature");
        //LightBox lightBoxJanuszaGirls = new LightBox(Janusz, "Girls");


        Picture lubberJack = new Picture("n1", new Money(2.00, "PLN"), new String[]{"piła", "drewno"}, false);
        Picture kitty = new Picture("n2", new Money(2.00, "PLN"), new String[]{"zwierz", "kotek"}, false);
        Picture lamp = new Picture("n3", new Money(3.00, "PLN"), new String[]{"oswietlenie", "lampa" }, false);
        Picture lamp2 = new Picture("n4", new Money(2.00, "PLN"), new String[]{"oswietlenie", "lampa" }, false);
        Picture tree = new Picture("n5", new Money(1.00, "PLN"), new String[]{"natura", "drzewo" }, false);
        Picture grass = new Picture("n6", new Money(1.00, "PLN"), new String[]{"natura", "trawa" }, false);
        Picture waterfall = new Picture("n6", new Money (4.00, "PLN"), new String[]{"natura", "woda" }, false);
        Picture girl1 = new Picture("n7", new Money(2.00, "PLN"), new String[]{"kobieta", "portret" }, false);
        Picture girl2 = new Picture("n8", new Money(2.00, "PLN"), new String[]{"kobieta", "portret" }, false);
        Picture girl3 = new Picture("n9", new Money(1.00, "PLN"), new String[]{"kobieta", "portret" }, false);
       //Picture lubberJack2 = new Picture ("n1", new String[]{"piła", "drewno"} , 2, true);

        try {
            lightBoxJanusza.add(lubberJack);
            //lightBoxJanusza.close(); //!!!!!
            lightBoxJanusza.add(kitty);

          //  lightBoxJanuszaHome.add(lamp);
           // lightBoxJanuszaHome.add(lamp2);

          //  lightBoxJanuszaNature.add(tree);
           // lightBoxJanuszaNature.add(grass);
           // lightBoxJanuszaNature.add(waterfall);

           // lightBoxJanuszaGirls.add(girl1);
           // lightBoxJanuszaGirls.add(girl2);
           // lightBoxJanuszaGirls.add(girl3);

            //lightBoxJanusza.add(lubberJack);
        }
        catch(ProductNotAvailableException ex){
            System.out.println(ex.getClass()+ " " + ex.getMessage()+ " " + ex.getNumber() );
        }
       // catch (IllegalArgumentException ex){
       //     System.out.println("nie udało się " + ex);
       // }

        int count = lightBoxJanusza.getItemsCount();
        System.out.println("ilość elementów " + count);

        ArrayList<LightBox> lightBoxes = new ArrayList<>();
        lightBoxes.add(lightBoxJanusza);
        //lightBoxes.add(lightBoxJanuszaHome);
        //lightBoxes.add(lightBoxJanuszaNature);
        //lightBoxes.add(lightBoxJanuszaGirls);

        LightBox.displayLbxPictures(lightBoxes);

    }
}
