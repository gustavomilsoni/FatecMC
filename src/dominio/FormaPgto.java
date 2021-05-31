package dominio;

public class FormaPgto extends EntidadeDominio {
	private int nQtdParcelas;
	private double nValorParcela;
		
	
	public FormaPgto() {
		
	}
	
	public FormaPgto(int id) {
		super.setId(id);
		
	}
	
	public FormaPgto(int nQtdParcelas, int nValorParcela) {
		this.nQtdParcelas = nQtdParcelas;
		this.nValorParcela = nValorParcela;
	}
	
	public FormaPgto(int id,int nQtdParcelas, int nValorParcela) {
		super.setId(id);
		this.nQtdParcelas = nQtdParcelas;
		this.nValorParcela = nValorParcela;
	}
	
	public int getnQtdParcelas() {
		return nQtdParcelas;
	}
	public void setnQtdParcelas(int nQtdParcelas) {
		this.nQtdParcelas = nQtdParcelas;
	}
	public double getnValorParcela() {
		return nValorParcela;
	}
	public void setnValorParcela(double nValorParcela) {
		this.nValorParcela = nValorParcela;
	}
	
	
	
}
