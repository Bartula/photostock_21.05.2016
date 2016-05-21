package pl.com.bottega.photostock.sales.infrastructure.repositories;

import org.junit.Test;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.ClientStatus;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by Bartosz on 2016-05-21.
 */
public class FileClientRepositoryTest {
    @Test
    public void shouldWriteClient() {

        // given
        ClientRepository clientRepository = new FileClientRepository("temp/clients.csv");
        Client c1 = new Client("nr1", "Janusz", "adress", ClientStatus.STANDARD, new Money(100.00, "PLN"));
        Client c2 = new Client("nr2", "Zegrzysław", "adress", ClientStatus.STANDARD, new Money(200.00, "PLN"));
        Client c3 = new Client("nr3", "Zegrzysław3", "adress", ClientStatus.STANDARD, new Money(200.00, "PLN"));

        //when
        clientRepository.save(c1);
        clientRepository.save(c2);
        clientRepository.save(c3);
        //then


    }
}
