package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Clip;
import pl.com.bottega.photostock.sales.model.products.Picture;
import pl.com.bottega.photostock.sales.model.products.Song;

/**
 * Created by bartosz.paszkowski on 10.05.2016.
 */
public class AdminPanel {

    private ClientRepository clientRepository = new FakeClientRepository();
    private ProductRepository productRepository = new FakeProductRepository();

    public String addProduct(AbstractProduct.TypeOfProduct TypeOfProduct , String number, Money price, String[] tags){

        Product product;
        switch (TypeOfProduct){
            case PICTURE:
                product = new Picture(number, price, tags, true);
                break;
            case CLIP:
                product = new Clip(number, price, tags, 0, true);
                break;
            case SONG:
                product = new Song(number, price, tags, true);
                break;
            default:
                throw new IllegalArgumentException("Wrong type of product");
        }

        productRepository.save(product);
        return product.getNumber();
    }

    public void promoteClient(String clientNr){
        Client client = clientRepository.load(clientNr);
        if (client.getStatus() != ClientStatus.VIP){
            client.setStatus(ClientStatus.VIP);
            clientRepository.save(client);
        }else{
            throw new IllegalArgumentException("Client is already a VIP");
        }
    }

    public void changeCreditLimit(String clientNr, Money newCreditLimit){
        Client client = clientRepository.load(clientNr);
        if (client.getStatus() == ClientStatus.VIP){
            client.setCreditLimit(newCreditLimit);  // TODO to tylko na chwilę ponieważ trzeba zmienić strategie clienta
        }
    }


}
