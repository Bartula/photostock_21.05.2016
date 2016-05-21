package pl.com.bottega.photostock.sales.model.clientStrategy;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.ClientStatus;
import pl.com.bottega.photostock.sales.model.Money;

import static pl.com.bottega.photostock.sales.model.ClientStatus.STANDARD;

/**
 * Created by bartosz.paszkowski on 11.05.2016.
 */
public class ChargingAndRecharging {


    public void charge (Money money, String cause, Client client){

        switch (client.getStatus()) {
            case STANDARD:
                StandardChargingStrategy scs = new StandardChargingStrategy();
                scs.charge(money,cause,client);
                break;
            case VIP:
                VipChargingStrategy vcs = new VipChargingStrategy();
                vcs.charge(money, cause, client);
                break;
            case GOLD:
                break;
            case SILVER:
                break;
            case PLATINUM:
                break;
        }

    }
    public void recharge (Money money, Client client){

        switch (client.getStatus()) {
            case STANDARD:
                StandardChargingStrategy scs = new StandardChargingStrategy();
                scs.recharge(money,client);
                break;
            case VIP:
                VipChargingStrategy vcs = new VipChargingStrategy();
                vcs.recharge(money, client);
                break;
            case GOLD:
                break;
            case SILVER:
                break;
            case PLATINUM:
                break;
        }

    }
    public void canAfford (Money money, Client client){

        switch (client.getStatus()) {
            case STANDARD:
                StandardChargingStrategy scs = new StandardChargingStrategy();
                scs.canAfford(money,client);
                break;
            case VIP:
                VipChargingStrategy vcs = new VipChargingStrategy();
                vcs.canAfford(money, client);
                break;
            case GOLD:
                break;
            case SILVER:
                break;
            case PLATINUM:
                break;
        }

    }
}
