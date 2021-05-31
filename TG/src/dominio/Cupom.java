package dominio;

public class Cupom extends FormaPgto {

	private String numCupom;

			
	public Cupom(String numCupom, Double valorCupom) {
		this.numCupom = numCupom;
		super.setnValorParcela(valorCupom);
	}
	
	public Cupom(String numCupom) {
		this.numCupom = numCupom;
		
	}
	public Cupom() {
		
	}
	
	public String getNumCupom() {
		return numCupom;
	}
	public void setNumCupom(String numCupom) {
		this.numCupom = numCupom;
	}

	
	
	
	
}