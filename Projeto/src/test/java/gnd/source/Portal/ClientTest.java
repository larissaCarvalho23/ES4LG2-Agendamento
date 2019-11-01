package gnd.source.Portal;

import gnd.source.Portal.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ClientTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllClient() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/client",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetClientById() {
        Client client = restTemplate.getForObject(getRootUrl() + "/client/1", Client.class);
        System.out.println(client.getName());
        assertNotNull(client);

    }

    @Test
    public void testCreateClient() {
        Client client = new Client();
        client.setName("RAMON O MAESTRO");
        client.setGender("M");
        ResponseEntity<Client> postResponse = restTemplate.postForEntity(getRootUrl() + "/client", client, Client.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateClient() {
        int id = 1;
        Client client = restTemplate.getForObject(getRootUrl() + "/client/" + id, Client.class);
        client.setName("RUBINHO A LENDA");
        client.setGender("M");

        restTemplate.put(getRootUrl() + "/client/" + id, client);

        Client updatedClient = restTemplate.getForObject(getRootUrl() + "/client/" + id, Client.class);
        assertNotNull(updatedClient);
    }

    @Test
    public void testDeleteClient() {
        int id = 2;
        Client client = restTemplate.getForObject(getRootUrl() + "/client/" + id, Client.class);
        assertNotNull(client);

        restTemplate.delete(getRootUrl() + "/client/" + id);

        try {
            client = restTemplate.getForObject(getRootUrl() + "/client/" + id, Client.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}


