package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartosz.paszkowski on 03.04.2016.
 */
public enum ClientStatus {
    STANDARD, VIP, GOLD, SILVER, PLATINUM;

    private static final String[] POLISHDICTIONARY = {"STANGARD", "VIP", "ZŁOTY", "SREBRNY", "PLATYNOWY"};

    public String getPolishString(){
        int nr = this.ordinal();
        return POLISHDICTIONARY[nr];
    }


}
