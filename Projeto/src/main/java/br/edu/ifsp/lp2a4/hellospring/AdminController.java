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

import br.edu.ifsp.lp2a4.hellospring.entidades.ClientesRepository;
import br.edu.ifsp.lp2a4.hellospring.entidades.ClienteView;
import br.edu.ifsp.lp2a4.hellospring.entidades.Cliente;

@Controller
public class AdminController {

	private ClientesRepository repository;

	public AdminController(ClientesRepository repository) {
		this.repository = repository;

	}

	@GetMapping("/admin")
	public String index(Model model) {


		return "admin/indexAdmin";

	}
	
	@GetMapping("/admin/createCliente")
	public String create(ClienteView cliente) {

		return "admin/createCliente";

	}
	
	@PostMapping("/admin/createCliente")
	public String create(@Valid ClienteView cliente, BindingResult result, Model model) throws ParseException {
		if (result.hasErrors()) {
			return "admin/createCliente";
		}
			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = formatter.parse(cliente.getDataNasc());
		
		Cliente cli = new Cliente();
		
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
		

		return "redirect:/admin/listCliente";

	}

	
	@GetMapping("/admin/listCliente")
	public String listCliente(Model model) {

		model.addAttribute("cliente", repository.findAll());

		return "admin/listCliente";

	}
}
