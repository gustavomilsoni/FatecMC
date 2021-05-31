package dominio;


public class Cartao extends FormaPgto {
	
	private String nomeCartao;
	private String numCartao;
	private String cvv;
	private String validade;
	private BandeiraCart bandeira;
	
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cartao(int nQtdParcelas, int nValorParcela,String nomeCartao, String numCartao, String cvv, String validade, BandeiraCart bandeira) {
		super(nQtdParcelas,nValorParcela);
		this.numCartao = numCartao;
		this.cvv = cvv;
		this.validade = validade;
		this.bandeira = bandeira;
		this.nomeCartao = nomeCartao;
		
	}
	
	public Cartao(int id,String nomeCartao, String numCartao, String cvv, String validade, BandeiraCart bandeira) {
		super(id);
		this.numCartao = numCartao;
		this.cvv = cvv;
		this.validade = validade;
		this.bandeira = bandeira;
		this.nomeCartao = nomeCartao;
		
	}
	
	public Cartao(String nomeCartao, String numCartao, String cvv, String validade, BandeiraCart bandeira) {

		this.numCartao = numCartao;
		this.cvv = cvv;
		this.validade = validade;
		this.bandeira = bandeira;
		this.nomeCartao = nomeCartao;
		
	}
	
	public Cartao(int id,int nQtdParcelas, int nValorParcela,String nomeCartao, String numCartao, String cvv, String validade, BandeiraCart bandeira) {
		super(id,nQtdParcelas,nValorParcela);
		this.numCartao = numCartao;
		this.cvv = cvv;
		this.validade = validade;
		this.bandeira = bandeira;
		this.nomeCartao = nomeCartao;
		
	}
	
	
	public Cartao() {
		
	}
	
	public String getNomeCartao() {
		return nomeCartao;
	}


	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}



	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}


	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public BandeiraCart getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCart bandeira) {
		this.bandeira = bandeira;
	}
	

}

