package br.edu.ifsp.lp2a4.hellospring;

import br.edu.ifsp.lp2a4.hellospring.entidades.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HellospringApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestesProjeto {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Usuario usuario;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void test_Get_All_Usuarios() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/usuarios",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateUsuario() throws ParseException {
        usuario.setNome("MATEUS SANTOS");
        usuario.setDataNasc("21/08/1998");
        usuario.setEmail("mateus@hotmail.com");
        usuario.setSexo("M");
        usuario.setTelefone("11980797658");
        ResponseEntity<Usuario> postResponse = restTemplate.postForEntity(getRootUrl() + "/usuarios/create", usuario, Usuario.class );
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }


    @Test
    public void testDeleteUsuario() {
        int id = 2;
        Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
        assertNotNull(usuario);
        restTemplate.delete(getRootUrl() + "/usuarios/" + id);
        try {
            usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
        } catch (final Error e) {
        }
    }
    @Test
    public void testLogin() {
    	String login = "fabriciots99@gmail.com";
    	String senha = "123";
    	
    }
}
