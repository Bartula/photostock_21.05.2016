package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.AbstractProduct;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 03.04.2016.
 */
public class Clip extends AbstractProduct{

    private String name;
    private String time;
    private String[] tags;
    private Money price;
    private boolean isAvailable;
    private long length;

    public Clip(String number, String name, String time, String[] tags, Money price, boolean isAvailable, long length){
        super(number,price,tags,isAvailable);
        this.name = name;
        this.time = time;
        this.tags = tags;
        this.price = price;
        this.isAvailable = isAvailable;
        this.length = length;
    }

    public Clip(String title, Money price, String[] tags, long length,boolean b) {
        super(title, price, tags, b);
        this.length = length;
    }


    public long getLength() {
        return length;
    }

    @Override
    public String[] export() {
        Money price = calculatePrice();

        return new String[]{
                getNumber(),
                String.valueOf(price.cents()),
                String.valueOf(price.getCurrency()),
                String.valueOf(isAvailable()),
                String.valueOf(this.length),
                " ",
                "Clip"
        };
    }
}
