package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.commons.math.Fraction;

/**
 * Created by bartosz.paszkowski on 12.04.2016.
 */
public class VIPClient extends Client {

    private Money debt;
    private Money creditLimit;
    private Money amount;

    public VIPClient(String name, String address, Money amount, Money creditLimit) {
        super(name, address, ClientStatus.VIP, amount);
        this.debt = new Money(0.00, "PLN");
        this.creditLimit = creditLimit;
        this.amount = amount;
    }

    public boolean canAfford(Money purchasePotential) {
        int debtNomin = Math.abs(debt.getValue().getNominator());
        int debtDenom = Math.abs(debt.getValue().getDenominator());
        Money plusDebt = new Money(new Fraction(debtNomin,debtDenom),"PLN");
        if (plusDebt.le(creditLimit)) { // tu musibyc wartośc bezwzgledna z debt
                return purchasePotential.ge(amount.add(creditLimit).add(debt));
            }
            else {
                return false;
            }
    }

    public void charge(Money quantity, String cause)throws IllegalStateException{
        if (canAfford(quantity)){
            amount = amount.substract(quantity);
            if (amount.lt(amount.getZero())) {
                debt = amount.substract(quantity);
                amount = amount.getZero();
            }
        }
        else{
            throw new IllegalStateException("You do not have sufficient funds in your account ");
        }
    }

    public void recharge(Money quantity, Client client){
        if (wantPayDebt() && debt != debt.getZero()) {
            debt = debt.add(quantity);
            if (debt.lt(debt.getZero())) {
                amount = amount.add(debt);
                debt = debt.getZero();
            }
        }
        else {
            amount = amount.add(quantity);
        }
    }
    public Money getSaldo() {
        return amount = amount.add(debt);
    }

    //chęć spłaty długu
    public boolean wantPayDebt() { return true; }
}
