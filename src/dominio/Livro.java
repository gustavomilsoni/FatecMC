package dominio;

public class Livro extends EntidadeDominio {
	
	private String titulo = "";
    private String isbn = "";
    private String autor = "";
    private String status = "";
    private String editora = "";
    private String ano = "";
    private String edicao= "";
    private String numPag = "";
    private String sinopse = "";
    private String altura = "";
    private String peso = "";
    private String codBarras = "";
    private String profundidade = "";
    private GrupoPreco grpPreco ;
    private Categoria categoria;
    private String largura = "";
	private Estoque estoque;
	private double valorVenda;
	private double margem;
    
    public Livro(){
    	estoque = new Estoque();
    	categoria = new Categoria();
    	grpPreco = new GrupoPreco();
    	
    }

	public Livro(String titulo, String isbn, String autor, String status, String editora, String ano, String edicao,
			String numPag, String sinopse, String altura, String peso, String codBarras, String profundidade,
			GrupoPreco grpPreco, Categoria categoria, String largura, Estoque estoque, double valorVenda,double margem) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.autor = autor;
		this.status = status;
		this.editora = editora;
		this.ano = ano;
		this.edicao = edicao;
		this.numPag = numPag;
		this.sinopse = sinopse;
		this.altura = altura;
		this.peso = peso;
		this.codBarras = codBarras;
		this.profundidade = profundidade;
		this.grpPreco = grpPreco;
		this.categoria = categoria;
		this.largura = largura;
		this.estoque = estoque;
		this.valorVenda = valorVenda;
		this.margem = margem;

	}
	
	public Livro(String titulo, String isbn, String autor, String status, String editora, String ano, String edicao,
			String numPag, String sinopse, String altura, String peso, String codBarras, String profundidade,
			GrupoPreco grpPreco, Categoria categoria, String largura,double margem) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.autor = autor;
		this.status = status;
		this.editora = editora;
		this.ano = ano;
		this.edicao = edicao;
		this.numPag = numPag;
		this.sinopse = sinopse;
		this.altura = altura;
		this.peso = peso;
		this.codBarras = codBarras;
		this.profundidade = profundidade;
		this.grpPreco = grpPreco;
		this.categoria = categoria;
		this.largura = largura;
		this.margem = margem;

	}

	public Livro(int id,String titulo, String isbn, String autor, String status, String editora, String ano, String edicao,
			String numPag, String sinopse, String altura, String peso, String codBarras, String profundidade,
			GrupoPreco grpPreco, Categoria categoria, String largura, Estoque estoque, double valorVenda,double margem) {
		super.setId(id);
		this.titulo = titulo;
		this.isbn = isbn;
		this.autor = autor;
		this.status = status;
		this.editora = editora;
		this.ano = ano;
		this.edicao = edicao;
		this.numPag = numPag;
		this.sinopse = sinopse;
		this.altura = altura;
		this.peso = peso;
		this.codBarras = codBarras;
		this.profundidade = profundidade;
		this.grpPreco = grpPreco;
		this.categoria = categoria;
		this.largura = largura;
		this.estoque = estoque;
		this.valorVenda = valorVenda;
		this.margem = margem;

	}




	public double getMargem() {
		return margem;
	}

	public void setMargem(double margem) {
		this.margem = margem;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}


	public Estoque getEstoque() {
		return estoque;
	}


	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getNumPag() {
		return numPag;
	}

	public void setNumPag(String numPag) {
		this.numPag = numPag;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}

	public GrupoPreco getGrpPreco() {
		return grpPreco;
	}

	public void setGrpPreco(GrupoPreco grpPreco) {
		this.grpPreco = grpPreco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria cat) {
		this.categoria = cat;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

    
    
}
