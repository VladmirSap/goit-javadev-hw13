package example;

import org.example.entity.Client;
import org.example.service.ClientCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ClientCrudServiceTest {
    private ClientCrudService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientCrudService();
    }

    @Test
    public void testSaveClient() {
        Client client = new Client();
        client.setName("Test Client");

        clientService.save(client);


        Client retrievedClient = clientService.findById(client.getId());
        assertNotNull(retrievedClient, "Client should be saved to the database");
        assertEquals("Test Client", retrievedClient.getName(), "Client name should match");
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client();
        client.setName("Test Client");

        clientService.save(client);
        assertNotNull(clientService.findById(client.getId()), "Client should be in the database");

        clientService.delete(client.getId());
        assertNull(clientService.findById(client.getId()), "Client should be deleted from the database");
    }
}
