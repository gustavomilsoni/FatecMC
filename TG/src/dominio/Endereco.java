package dominio;

public class Endereco extends EntidadeDominio {
	
	private String tipoEndereco;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String observacoes;
	private Cidade cidade;

	
	public Endereco() {
		
	}
	
	public Endereco(String tipoEndereco, String tipoLogradouro, String logradouro, String numero, String bairro,
		String cep, String observacoes, Cidade cidade) {
		this.tipoEndereco = tipoEndereco;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.observacoes = observacoes;
		this.cidade = cidade;
				
	}
	
	public Endereco(int id,String tipoEndereco, String tipoLogradouro, String logradouro, String numero, String bairro,
		String cep, String observacoes, Cidade cidade) {
		super.setId(id);
		this.tipoEndereco = tipoEndereco;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.observacoes = observacoes;
		this.cidade = cidade;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
