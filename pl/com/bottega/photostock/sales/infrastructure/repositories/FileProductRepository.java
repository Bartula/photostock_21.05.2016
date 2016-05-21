package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.products.Clip;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz.paszkowski on 14.05.2016.
 */
public class FileProductRepository implements ProductRepository {
    private final String path;

    public FileProductRepository(String path) {
        this.path = path;
    }

    @Override
    public Product load(String nr) {
        try (InputStream is = new FileInputStream(path)) {
            readLine(is);
            String line;
            while ((line = readLine(is)) != null) {
                if (line.trim().length() == 0)
                    return null;
                Product product;
                product = parseProduct(line);
                if (product.getNumber().equals(nr))
                    return product;
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return null;
    }
    private Product parseProduct(String line) {
        Product product;
        String[] components = line.split(",");
        String number = components[0];
        int priceCents = Integer.parseInt(components[1]);
        Money price = new Money(priceCents / 100, priceCents % 100, components[2]);
        boolean available = Boolean.parseBoolean(components[3]);
        if (components[6].trim().equals("Picture")) {
            String[] tags = components[5].split(" ");
            product = new Picture(number, price, tags, available);
        } else {
            long length = Long.parseLong(components[4]);
            product = new Clip(number, price, null, length, true);
        }
        return product;
    }

    private String readLine(InputStream is) throws IOException {
        int ch;
        StringBuilder sb = new StringBuilder();
        while (((ch = is.read()) != '\n') && ch != -1) {
            sb.append((char) ch);
        }
        return sb.toString();
    }

    @Override
    public void save(Product product) {
        File f = new File(this.path);
        boolean newRepo = !f.exists();
        try (OutputStream os = new FileOutputStream(this.path,true)){ //automayczne zamknięcie strumienia jak jest w nawiasie przy try
             //true jeżeli chcemy aby dopisywać do pliku
            if (newRepo)
                os.write("number,priceCents,priceCurrency,available,length,tags,type\r\n".getBytes());
            String[] productExported = product.export();
            String csvLine = String.join(",",productExported) + "\r\n";
            os.write(csvLine.getBytes());

        } catch (Exception ex) {
            throw  new DataAccessException(ex);
        }
    }

    @Override
    public ArrayList<Product> getProductsList() {
        return null;
    }

    @Override
    public List<Product> find(String[] tags, String author, Money nimPrice, Money maxPrice, boolean acceptNotAvailable) {
        return null;
    }
}
