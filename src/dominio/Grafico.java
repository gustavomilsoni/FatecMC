package dominio;

import java.sql.Date;

public class Grafico extends EntidadeDominio {
	
	private Date dataInicial;
	private Date datafinal;
	private EntidadeDominio entidade;
	
	
	public Grafico() {
		
	}


	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}


	public Date getDatafinal() {
		return datafinal;
	}


	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}


	public EntidadeDominio getEntidade() {
		return entidade;
	}


	public void setEntidade(EntidadeDominio entidade) {
		this.entidade = entidade;
	}
	
	

}
