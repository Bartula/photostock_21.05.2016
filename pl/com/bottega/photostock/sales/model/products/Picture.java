package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.AbstractProduct;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.Map;

/**
 * Created by bartosz.paszkowski on 03.04.2016.
 */
public class Picture extends AbstractProduct {



    public Picture(String number,  Money price, String[] tags, boolean isAvailable) {
        super(number,price, tags,isAvailable);
    }

    public Picture(String number, String[] tags, Money price) {
        this(number, price, tags,  true) ;
    }


    public String getNumber() {
        return number;
    }



    public String getPrice() {
        return String.valueOf(price);
    }

    @Override  // Code->Generate->equals() and hashCode()
    public boolean equals(Object o) {           // TO TRZEBA ROZUMIEC !!!!!!!!!!!!!!!
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        //if (! (o instanceof Picture)) // operator sprawdza zgodność co do przypisania
        // return false;

        Picture picture = (Picture) o;

        return number.equals(picture.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String[] export() {
        Money price = calculatePrice();
        String tagsJoined = String.join(" ", this.getTags());
        return new String[]{
                getNumber(),
                String.valueOf(price.cents()),
                String.valueOf(price.getCurrency()),
                String.valueOf(isAvailable()),
                "",
                tagsJoined,
                "Picture"
        };
    }
}

