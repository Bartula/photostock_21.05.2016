package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.DataDoesNotExistException;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartosz.paszkowski on 21.04.2016.
 */
public class FakeLightBoxRepository implements LightBoxRepository{
    private static Map<String, LightBox> fakeDataBase = new HashMap<>();

    static {
        ClientRepository clientRepository = new FakeClientRepository();
        ProductRepository productRepository = new FakeProductRepository();

        LightBox lbx1 = new LightBox("nr1", clientRepository.load("nr1"), clientRepository.load("nr1").getCompany());
        LightBox lbx2 = new LightBox("nr2", clientRepository.load("nr2"), clientRepository.load("nr2").getCompany());
        Product pic = productRepository.load("nr1");
        lbx1.add((Picture) pic);

        fakeDataBase.put(lbx1.getNumber(), lbx1);
    }

    @Override
    public LightBox load(String nr) {
        LightBox lbx = fakeDataBase.get(nr);
        if (lbx == null)
            throw  new DataDoesNotExistException(lbx.getNumber(), "LightBox does not exist");
        return lbx;
    }

    @Override
    public void save(LightBox lbx) {
        fakeDataBase.put(lbx.getNumber(), lbx);
    }
}
