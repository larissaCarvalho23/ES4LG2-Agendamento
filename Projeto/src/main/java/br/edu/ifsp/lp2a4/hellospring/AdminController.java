package br.edu.ifsp.lp2a4.hellospring;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
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
	public String index(HttpSession session) {
		if(session.getAttribute("login")!=null) {
			if(session.getAttribute("isAdmin")!=null) {
				return "admin/indexAdmin";
			}
			else {
				return "NotFound";
			}
		}
		return "redirect:/login";
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
	
	@GetMapping("/admin/{id}/delete")
	public String delete(@PathVariable long id, Model model) {

		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

		repository.delete(cliente);

		model.addAttribute("cliente", repository.findAll());

		return "admin/listCliente";
	}
	
	@GetMapping("/admin/{id}/editCliente")
	public String edit(@PathVariable long id, Model model) {

		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

		model.addAttribute("cliente", cliente);

		return "admin/editCliente";
	}

	@PostMapping("/admin/{id}")
	public String edit(@PathVariable long id, @Valid ClienteView cliente, BindingResult result, Model model) throws ParseException {

		if (result.hasErrors())
			return "admin/editCliente";

SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = formatter.parse(cliente.getDataNasc());
		
		Cliente cli = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
		
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

		return "admin/listCliente";

	}
}
