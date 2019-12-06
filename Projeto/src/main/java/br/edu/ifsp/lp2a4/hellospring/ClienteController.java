package br.edu.ifsp.lp2a4.hellospring;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsp.lp2a4.hellospring.entidades.SubClientesRepository;
import br.edu.ifsp.lp2a4.hellospring.entidades.SubClienteView;
import br.edu.ifsp.lp2a4.hellospring.entidades.SubCliente;

@Controller
public class ClienteController {
	private SubClientesRepository repository;

	public ClienteController(SubClientesRepository repository) {
		this.repository = repository;

	}

	@GetMapping("/clientes")
	public String index(Model model) {


		return "clientes/indexCliente";

	}
	
	@GetMapping("/clientes/createSubCliente")
	public String create(SubClienteView cliente) {

		return "clientes/createSubCliente";

	}
	
	@PostMapping("/clientes/createSubCliente")
	public String create(@Valid SubClienteView cliente, BindingResult result, Model model) throws ParseException {
		if (result.hasErrors()) {
			return "clientes/createSubCliente";
		}
			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = formatter.parse(cliente.getDataNasc());
		
		SubCliente cli = new SubCliente();
		
		cli.setNome(cliente.getNome());
		cli.setSexo(cliente.getSexo());
		cli.setCep(cliente.getCep());
		cli.setCidade(cliente.getCidade());
		cli.setDataNasc(date);
		cli.setEmail(cliente.getEmail());
		cli.setLogradouro(cliente.getLogradouro());
		cli.setNumeroend(cliente.getNumeroend());
		cli.setTelefone(cliente.getTelefone());
		cli.setUf(cliente.getUf());
		
		repository.save(cli);
		

		return "redirect:/clientes/listSubCliente";

	}

	
	@GetMapping("/clientes/listSubCliente")
	public String listCliente(Model model) {

		model.addAttribute("cliente", repository.findAll());

		return "clientes/list";

	}
	
}
