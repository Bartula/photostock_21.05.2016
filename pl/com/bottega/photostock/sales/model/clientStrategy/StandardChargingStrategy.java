package pl.com.bottega.photostock.sales.model.clientStrategy;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 11.05.2016.
 */
public class StandardChargingStrategy implements ChargingStrategy{


    @Override
    public boolean canAfford(Money money, Client client) {
        return client.getAmount().ge(money);
    }

    @Override
    public void charge(Money quantity,String cause, Client client) {
        if (canAfford(quantity, client)){
            client.setAmount(client.getAmount().substract(quantity));
        }
        else{
            throw new IllegalStateException("You do not have sufficient funds in your account ");
        }
    }

    @Override
    public void recharge(Money quantity, Client client) {
        client.setAmount( client.getAmount().add(quantity));
    }
}
