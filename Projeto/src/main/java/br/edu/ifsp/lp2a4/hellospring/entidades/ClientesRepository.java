package br.edu.ifsp.lp2a4.hellospring.entidades;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//faz uso do sping jpa

@Repository
public interface ClientesRepository extends CrudRepository<Cliente, Long>{	

}
