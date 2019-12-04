package br.edu.ifsp.lp2a4.hellospring;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsp.lp2a4.hellospring.entidades.ClientesRepository;
import br.edu.ifsp.lp2a4.hellospring.entidades.Cliente;

@Controller
public class ClientesController {

	private ClientesRepository repository;

	public ClientesController(ClientesRepository repository) {
		this.repository = repository;

	}

	@GetMapping("/clientes")
	public String index(Model model) {


		return "clientes/indexCliente";

	}


	@GetMapping("/clientes/create")

	public String create(Cliente cliente) {

		return "clientes/createSubCliente";

	}
}
