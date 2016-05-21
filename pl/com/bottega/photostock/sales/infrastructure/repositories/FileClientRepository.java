package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by bartosz.paszkowski on 19.05.2016.
 */
public class FileClientRepository implements ClientRepository {


    private final String path;

    public FileClientRepository(String path) {
        this.path = path;
    }

    @Override
    public Client load(String numer) {
        return null;
    }

    @Override
    public void save(Client client) {
        File file = new File("temp/clients.csv");
        boolean newRepo = !file.exists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
           // if (newRepo)
           //     bw.write("number,name,adress,status,amount" + "\r\n");
            String[] clientExported = client.export();
            String csvLine = String.join(",", clientExported);

            bw.newLine();
            bw.write(csvLine + "\r\n");
            //System.getProperty(",");
            //bw.newLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
