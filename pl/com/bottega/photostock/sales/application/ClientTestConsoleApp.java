package pl.com.bottega.photostock.sales.application;


import pl.com.bottega.photostock.sales.infrastructure.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 13.03.2016.
 */
public class ClientTestConsoleApp {
    public static void main(String[] args) {
        //Client panJanusz = new Client("Janusz", "ksiezyc", ClientStatus.STANDARD,10);
        ClientRepository clientRepository = new FakeClientRepository();
        Client panJanusz = clientRepository.load("nr1");
        panJanusz.recharge(new Money(10.00, "PLN"), panJanusz);
        if (panJanusz.canAfford(new Money(12.00, "PLN"),panJanusz)) {
            panJanusz.charge(new Money(12.00, "PLN"), "za coś", panJanusz);
            System.out.println("Saldo: " + panJanusz.getAmount());
        }
        else{
            System.out.println("can not afford!");
        }
    }
}
