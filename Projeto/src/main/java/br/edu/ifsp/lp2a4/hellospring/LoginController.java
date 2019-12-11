package br.edu.ifsp.lp2a4.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import br.edu.ifsp.lp2a4.hellospring.entidades.UsuariosRepository;
import br.edu.ifsp.lp2a4.hellospring.entidades.Usuario;

@Controller
public class LoginController {

	private UsuariosRepository repository;

	public LoginController(UsuariosRepository repository) {
		this.repository = repository;

	}	
	
//	@Autowired
//	private Usuario usuario;
	
	
	
	
	@GetMapping("/login")
	public String login() {
		return "login";

	}
	
	@PostMapping("/login")
	public String logar(@RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session){
		
		Usuario usuario = repository.findByEmailAndPassword(login, password);
		try {
			if (login.compareTo(usuario.getEmail())==0 && password.compareTo(usuario.getPassword())==0) {
				String sucesso = "Login efetuado";
				model.addAttribute("Sucesso", sucesso);
				if (usuario.isAdmin()) {
					session.setAttribute("login", login);
					session.setAttribute("isAdmin", usuario.isAdmin());
					return "redirect:/admin";
				}
				else {
					session.setAttribute("login", login);
					session.setAttribute("isAdmin", usuario.isAdmin());
					return "redirect:/clientes";
				}
			}
		}
		catch (Exception e) {
			String mensagem = "Login ou senha incorreto";
			model.addAttribute("ErrorLogin", mensagem);
		}
		return "login";
	}
	
}
