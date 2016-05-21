package pl.com.bottega.photostock.sales.model.clientStrategy;

import pl.com.bottega.commons.math.Fraction;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 11.05.2016.
 */
public class VipChargingStrategy implements ChargingStrategy{

    @Override
    public boolean canAfford(Money purchasePotential, Client client) {
        int debtNomin = Math.abs(client.getDebt().getValue().getNominator());
        int debtDenom = Math.abs(client.getDebt().getValue().getDenominator());
        Money plusDebt = new Money(new Fraction(debtNomin,debtDenom),"PLN");
        if (plusDebt.le(client.getCreditLimit())) { // tu musibyc wartośc bezwzgledna z debt
            return purchasePotential.ge(client.getAmount().add(client.getCreditLimit()).add(client.getDebt()));
        }
        else {
            return false;
        }
    }

    @Override
    public void charge(Money quantity, String cause, Client client)throws IllegalStateException{
        if (canAfford(quantity, client)){
            client.setAmount(client.getAmount().substract(quantity));
            if (client.getAmount().lt(client.getAmount().getZero())) {
                client.setDebt(client.getAmount().substract(quantity));
                client.setAmount(client.getAmount().getZero());
            }
        }
        else{
            throw new IllegalStateException("You do not have sufficient funds in your account ");
        }
    }

    @Override
    public void recharge(Money quantity, Client client){
        if (wantPayDebt() && !(client.getDebt().equals(client.getDebt().getZero()))) {
            client.setDebt(client.getDebt().add(quantity));
            if (client.getDebt().lt(client.getDebt().getZero())) {
                client.setAmount(client.getAmount().add(client.getDebt()));
                client.setDebt(client.getDebt().getZero());
            }
        }
        else {
            client.setAmount(client.getAmount().add(quantity));
        }
    }

    private boolean wantPayDebt() {
        return true;
    }
}
