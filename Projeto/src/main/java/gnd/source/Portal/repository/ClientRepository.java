package gnd.source.Portal.repository;


import gnd.source.Portal.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<ClientRepository> findByActivated(boolean activated, Pageable pageable);
}