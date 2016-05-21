package pl.com.bottega.photostock.sales.api;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bartosz.paszkowski on 24.04.2016.
 */
public class PurchaseProcessTest {

    private static final String STANDARD_USER_NR = "nr1";
    private static final String LAME_USER_NR = "nr2";
    private static final String AVAILABLE_PRODUCT_NR = "nr1";
    /*
    @Test
    public void shouldCreateEmptyReservationForStandardClient(){
        // given
        PurchaseProcess purchaseProcess = new PurchaseProcess();
        //dodajemy usera i produkty
        //when
        String reservationNr = purchaseProcess.create(STANDARD_USER_NR);
        //then
        Assert.assertNotNull(reservationNr); //sprawdza pole czy jest nulem, zwraca wyjątek jak jest
        Assert.assertNotEquals(reservationNr, "");
    }*/

    @Test
    public void shouldAddAvailableProduct(){
        //given
        PurchaseProcess purchaseProcess = new PurchaseProcess();
        //String reservationNr =  purchaseProcess.create(STANDARD_USER_NR);
        //when
        purchaseProcess.add(STANDARD_USER_NR,AVAILABLE_PRODUCT_NR);
        //then

        //teardown
    }

    @Test //spodziewany wyjątek
    public void canNotAddAlreadyAddedProduct(){
        //given
        PurchaseProcess purchaseProcess = new PurchaseProcess();
        //String reservationNr =  purchaseProcess.create(STANDARD_USER_NR);
        purchaseProcess.add(LAME_USER_NR,AVAILABLE_PRODUCT_NR);
        //when
        try {
            purchaseProcess.add(LAME_USER_NR, AVAILABLE_PRODUCT_NR);
            Assert.fail();
        }
        catch (IllegalArgumentException ex){
            //expected
        }
    }


}
