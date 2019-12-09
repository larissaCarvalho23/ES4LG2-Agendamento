package br.edu.ifsp.lp2a4.hellospring.entidades;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//faz uso do sping jpa

@Repository
public interface UsuariosRepository extends CrudRepository<Usuario, Long>{	
	
	@Query("SELECT u FROM Usuario u where u.email =:login AND u.password =:senha")
	Usuario findByEmailAndPassword(@Param("login") String login,@Param("senha") String senha);

}
