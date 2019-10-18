package gnd.source.Portal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends Object {
    @Autowired
    private ClientService repository;

    public List<ClientService> findAll() {
        List<ClientService> clientService = new ArrayList<>();
        this.repository.findAll().forEach(clientService:: add);
        return clientService;
    }

    public Optional<ClientService> findById (Integer id) {
        return this.repository.findById(id);
    }

    // public List<Client> findByActivated(Pageable pageable){
    //     return this.repository.findByActivated(true, client);
    //}

    public ClientService save (ClientService clientService) {
        return this.repository.save(clientService);
    }

    public ClientService delete (Integer id) {
        ClientService clientService = null;
        Optional <ClientService> fetchedClient = repository.findById(id);
        if (fetchedClient.isPresent()) {
            clientService = fetchedClient.get();
            //clientService.setStatus(0);
            this.repository.save(clientService);
        }
        return clientService;
    }
}
