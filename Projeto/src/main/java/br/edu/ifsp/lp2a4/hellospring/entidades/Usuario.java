package br.edu.ifsp.lp2a4.hellospring.entidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

//Esta classe está emulando um repositório de dados no modelo ActiveRecord

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "O nome é obrigatório!")
	private String nome;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNasc;	
	
	private String sexo;
	@NotBlank(message = "O telefone é obrigatório!")
	private String telefone;
	@NotBlank(message = "O e-mail é obrigatório!")
	private String email;
	private String logradouro;
	private String numeroend;
	private String cep;
	private String cidade;
	private String uf;

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNasc() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(this.dataNasc);   
		return strDate;
	}



	public void setDataNasc(String nascimento) throws ParseException {
				
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(nascimento);
		this.dataNasc = date1;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroend() {
		return numeroend;
	}

	public void setNumeroend(String numeroend) {
		this.numeroend = numeroend;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Usuario() {

	}

}
