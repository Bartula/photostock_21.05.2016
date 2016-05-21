package pl.com.bottega.photostock.sales.model.clientStrategy;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 11.05.2016.
 */
public interface ChargingStrategy {
    public boolean canAfford(Money money, Client client);
    public void charge(Money quantity,String cause, Client client);
    public void recharge(Money quantity, Client client);

}
