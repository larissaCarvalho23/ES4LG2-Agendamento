package gnd.source.Portal.controller;

import gnd.source.Portal.domain.Client;
import gnd.source.Portal.repository.ClientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/AllClients") // aqui pega todos os clients
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @GetMapping("/Client/{id}") // aqui pega o cliente pelo id
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientid)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientid)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientid));
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/NewClient") //aqui cria um novo client
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/Client/{id}")//aqui realiza um update no cliente com o id
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientid,
                                                   @Valid @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientid)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientid));

        client.setCpf(clientDetails.getCpf());
        client.setName(clientDetails.getName());
        client.setGender(clientDetails.getGender());
        final Client updatedEmployee = clientRepository.save(client);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/client/{id}") // delete por  id  do client
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientid)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientid)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientid));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
