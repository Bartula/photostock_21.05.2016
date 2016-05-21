package pl.com.bottega.photostock.sales.infrastructure.repositories;

import org.junit.Assert;
import org.junit.Test;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.products.Clip;
import pl.com.bottega.photostock.sales.model.products.Picture;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by bartosz.paszkowski on 14.05.2016.
 */
public class FileProductRepositoryTest {

    @Test
    public void shouldLoadProduct(){
        //given
        ProductRepository productRepository = new FileProductRepository(getClass().getResource("/fixtures/products.csv").getPath());

        //when
        Product product = productRepository.load("nr2");

        //then
        assertEquals("nr2",product.getNumber());
        assertEquals(Picture.class, product.getClass());
        assertEquals(new Money(20.00,"PLN"), product.calculatePrice());
        assertTrue(product.isAvailable());
        //assertArrayEquals(new String[]{"tag1", "tag2", "tag3", "tag4"}, ((Picture) product).getTags());
        assertArrayEquals(new String[]{"t1", "t2"}, ((Picture) product).getTags());
    }
    @Test
    public void shouldWriteProducts(){
        ProductRepository productRepository = new FileProductRepository("temp/products.csv");
        Product clip = new Clip("nr1", new Money(500.00,"USD"),null,200,true);
        Product picture = new Picture("nr2", new Money(20.00,"PLN"),new String[] {"t1","t2"},true );

        productRepository.save(clip);
        productRepository.save(picture);

        Product clipRead = productRepository.load("nr1");
        Product pictureRead = productRepository.load("nr2");
        Assert.assertEquals("nr1",clipRead.getNumber());
        Assert.assertEquals("nr2",pictureRead.getNumber());
        Assert.assertEquals(new Money(500.00, "USD"),clipRead.calculatePrice());
        assertArrayEquals(new String[] {"t1","t2"}, ((Picture)pictureRead).getTags());
        Assert.assertEquals(200, ((Clip)clipRead).getLength());
        Assert.assertEquals(true,pictureRead.isAvailable());

    }
}
