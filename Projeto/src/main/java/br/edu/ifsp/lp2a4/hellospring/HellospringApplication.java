package br.edu.ifsp.lp2a4.hellospring;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifsp.lp2a4.hellospring.entidades.UsuariosRepository;

@SpringBootApplication
public class HellospringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellospringApplication.class, args);
	}

}
