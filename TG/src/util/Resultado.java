package util;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;

public class Resultado {
	
	private String msg;
	private List<EntidadeDominio> entidades;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	
	public void addEntidade(EntidadeDominio entidade){
		if (entidades == null) {
			entidades = new ArrayList<EntidadeDominio>();
			
		}
		entidades.add(entidade);
	}
	

}
