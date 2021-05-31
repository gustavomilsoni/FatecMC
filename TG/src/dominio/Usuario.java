package dominio;

public class Usuario extends EntidadeDominio {
	private String usuario;
	private String senha;
	private String confirmacaoSenha;
	private boolean adm;
	private Pessoa pessoa;


	public Usuario( Pessoa p,String senha, String confirmacaoSenha, boolean adm) {
		this.senha = senha;
		this.confirmacaoSenha = confirmacaoSenha;
		this.adm = adm;
		this.pessoa = p;
	}

	public Usuario(Pessoa p, int id, String senha, boolean adm) {
		this.pessoa = p;
		this.usuario = p.getEmail();
		this.senha = senha;
		this.adm = adm;
		super.setId(id);
	}
	
	public Usuario( int id, String user, String senha, boolean adm) {
		this.usuario = user;
		this.senha = senha;
		this.adm = adm;
		super.setId(id);
	}

	public Usuario(Pessoa p, String senha) {
		this.usuario = p.getEmail();
		this.senha = senha;
		this.pessoa = p;

	}

	public Usuario() {

	}
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isAdm() {
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
