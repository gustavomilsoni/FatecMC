package dominio;

import java.util.Date;
import java.util.List;

public class Pessoa extends EntidadeDominio {
	private String nome;
	private List<Endereco> enderecos;
	private String genero;
	private String telefone;
	private String tipoFone;
	private String cpf;
	private String dataNascimento;
	private String email;
	private Usuario usuario;

	public Pessoa() {

	}

	public Pessoa(String fone, String tipoFone, List<Endereco> enderecos, String nome, String genero, String dataNasc,
			String cpf, String email) {
		this.genero = genero;
		this.enderecos = enderecos;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = fone;
		this.dataNascimento = dataNasc;
		this.tipoFone = tipoFone;
		this.email = email;

	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoFone() {
		return tipoFone;
	}

	public void setTipoFone(String tipoFone) {
		this.tipoFone = tipoFone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
