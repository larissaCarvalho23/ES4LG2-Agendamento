package br.edu.ifsp.lp2a4.hellospring;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsp.lp2a4.hellospring.entidades.SubClienteRepository;
import br.edu.ifsp.lp2a4.hellospring.entidades.SubCliente;

@Controller
public class ClientesController {

	private SubClienteRepository repository;

	public ClientesController(SubClienteRepository repository) {
		this.repository = repository;

	}

	@GetMapping("/clientes")
	public String index(Model model) {


		return "clientes/indexCliente";

	}
	
	@GetMapping("/clientes/listClientes")
	public String list(Model model) {


		return "clientes/listCliente";

	}


	@GetMapping("/clientes/create")
	public String create(SubCliente cliente) {

		return "clientes/createSubCliente";

	}
	
	@PostMapping("/clientes/create")
	public String create(@Valid SubCliente cliente, BindingResult result, Model model) throws ParseException {
		if (result.hasErrors()) {
			return "clientes/createSubCliente";
		}
		
		repository.save(cliente);
		

		return "redirect:/admin/listCliente";

	}
}
