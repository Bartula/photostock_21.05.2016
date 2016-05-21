package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.model.clientStrategy.ChargingStrategy;
import pl.com.bottega.photostock.sales.model.clientStrategy.StandardChargingStrategy;
import pl.com.bottega.photostock.sales.model.clientStrategy.VipChargingStrategy;

/**
 * Created by bartosz.paszkowski on 12.03.2016.
 */
public class Client {

    private String number;
    private String name;
    private String adress;
    private ClientStatus status;
    private Money debt; // powinna być liczba ujemna
    private Money amount;
    private Money creditLimit;
    private String company;
    private ChargingStrategy chargingStrategy;


    public Client(String name, String adress, ClientStatus status, Money amount) {   //konstruktor
        this.name = name;
        this.adress = adress;
        this.status = status; //???????????
        //this.debt = debt; //powinna być liczbaujemna
        this.amount = amount;
        //this.creditLimit = creditLimit;
        this.chargingStrategy = payingStrategy(status);
    }

    public Client(String number, String name, String adress, ClientStatus status, Money amount) {   //konstruktor
        this.name = name;
        this.adress = adress;
        this.status = status;
        this.amount = amount;
        this.number = number;
        this.chargingStrategy = payingStrategy(status);
    }

    public Client(String number, String name, String adress, ClientStatus status, Money amount, String company) {   //konstruktor
        this.name = name;
        this.adress = adress;
        this.status = status;
        this.amount = amount;
        this.number = number;
        this.company = company;
        this.chargingStrategy = payingStrategy(status);
    }

    public Client(String name, String adress) {   //konstruktor
        this.name = name;
        this.adress = adress;
        this.status = ClientStatus.STANDARD;
        this.amount = new Money(0.00, "PLN");
        this.chargingStrategy = payingStrategy(status);
    }
/*
    public Client(String[] name, String[] address, double creditlimit) {   //konstruktor
        this.name = name;
        this.address = address;
        this.isVip = false;
        this.saldo = 0;
        this.creditlimit = creditlimit;
    }
 */
    /*public Client(String name, String address, Money amount) {
        this(name, address, ClientStatus.STANDARD,amount);
    }*/


    public boolean canAfford(Money purchasePotential, Client client) {

        return chargingStrategy.canAfford(purchasePotential, client);
    }

    public void charge(Money quantity, String cause, Client client) throws IllegalStateException {
        chargingStrategy.charge(quantity, cause, client);
    }

    /*if (canAfford(quantity,client)){
        amount = amount.substract(quantity);
    }
    else{
        throw new IllegalStateException("You do not have sufficient funds in your account ");
    }
}*/
    public void recharge(Money quantity, Client client) {
        chargingStrategy.recharge(quantity, client);
        //amount = amount.add(quantity);
    }


    public static boolean isActive() {
        return true;
    }

    public String getOwnerName() {
        return name;
    }

    public String introduce() {
        return name + "-" + status.getPolishString();
    }

    public String getNumber() {
        return number;
    }

    public String getCompany() {
        return company;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public Money getDebt() {
        return debt;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public Money getAmount() {
        return amount;
    }
    public String getAdress() {
        return adress;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setDebt(Money debt) {
        this.debt = debt;
    }

    private ChargingStrategy payingStrategy(ClientStatus status) {

        switch (status) {
            case STANDARD:
                chargingStrategy = new StandardChargingStrategy();
                break;
            case VIP:
                chargingStrategy = new VipChargingStrategy();
                break;
            case GOLD:
                break;
            case SILVER:
                break;
            case PLATINUM:
                break;
        }
        return chargingStrategy;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String[] export() {
        Money amoun = getAmount();
        return new String[]{
                getNumber(),
                getOwnerName(),
                getAdress(),
                String.valueOf(getStatus()),
                String.valueOf(getAmount().getValue().getNominator()),
                String.valueOf(getAmount().getCurrency())
                //"\r\n"

        };
    }


}
