package br.edu.ifsp.lp2a4.hellospring;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/clientes/{id}/delete")
	public String remove(@PathVariable long id, Model model) {
		
		repository.deleteById(id);
		
		model.addAttribute("cliente", repository.findAll()); 
		
		return "clientes/list";
	}
	
	@GetMapping("/clientes/{id}/edit")
	public String edit (@PathVariable long id,Model model) {
		
		SubCliente subcliente = repository
				.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Cliente não encontrado"));
		
		SubClienteView cli = new SubClienteView(); 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		

		String date = dateFormat.format(subcliente.getDataNasc());
		
		cli.setId(subcliente.getId());
		cli.setNome(subcliente.getNome());
		cli.setSexo(subcliente.getSexo());
		cli.setCep(subcliente.getCep());
		cli.setCidade(subcliente.getCidade());
		cli.setDataNasc(date);
		cli.setEmail(subcliente.getEmail());
		cli.setLogradouro(subcliente.getLogradouro());
		cli.setNumeroend(subcliente.getNumeroend());
		cli.setTelefone(subcliente.getTelefone());
		cli.setUf(subcliente.getUf());
		
		model.addAttribute("cliente",cli);
		
		return "clientes/edit";
	}
	
	@PostMapping("/clientes/{id}")
	public String edit(@PathVariable long id, @Valid SubClienteView cliente, BindingResult result, Model model)  throws ParseException {

		if(result.hasErrors())
			return "clientes/edit";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = formatter.parse(cliente.getDataNasc());
		
		SubCliente cli = new SubCliente();
		
		cli.setId((int)id);
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
		
		model.addAttribute("cliente", repository.findAll()); 
		
		return "clientes/list";
	}
	
}
