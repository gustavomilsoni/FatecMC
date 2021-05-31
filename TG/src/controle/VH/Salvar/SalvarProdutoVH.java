package controle.VH.Salvar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.Categoria;
import dominio.EntidadeDominio;
import dominio.GrupoPreco;
import dominio.Livro;
import util.Resultado;

public class SalvarProdutoVH extends AbstractViewHelper {
	
	private String titulo;
	private String ano;
	private String autor;
	private String edicao;
	private String editora;
	private String isbn;
	private String numPag;
	private String peso;
	private String codBarras;
	private String altura;
	private String profundidade;
	private int categoria;
	private int precificacao;
	private String sinopse;
	private String largura;
	private double margem;
	
	
	private Livro livro;
	private GrupoPreco grupoPreco;
	private Categoria categ;
	

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		titulo = request.getParameter("txtTitulo");
		ano = request.getParameter("txtAno");
		autor = request.getParameter("txtAutor");
		edicao = request.getParameter("txtEdicao");
		editora = request.getParameter("txtEditora");
		isbn = request.getParameter("txtIsbn");
		numPag = request.getParameter("txtNumPagina");
		peso = request.getParameter("txtPeso");
		codBarras = request.getParameter("txtCodBarras");
		altura = request.getParameter("txtAltura");
		profundidade = request.getParameter("txtProfundidade");
		categoria = Integer.valueOf(request.getParameter("cmbCategoria"));
		precificacao = Integer.valueOf(request.getParameter("cmbGrupoPreco"));
		sinopse = request.getParameter("txtSinopse");
		largura = request.getParameter("txtLargura");
		margem = Double.valueOf(request.getParameter("txtMargem"));
		
		
		categ = new Categoria();
		categ.setId(categoria);
		
		grupoPreco = new GrupoPreco();
		grupoPreco.setId(precificacao);
		

		
		livro = new Livro(titulo, isbn, autor, "Ativo", editora, ano, edicao, numPag, sinopse, altura, peso, codBarras, profundidade, grupoPreco, categ, largura, margem);
		return livro;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		rd = request.getRequestDispatcher("frmCadProduto.jsp");		
			
		if (resultado.getMsg() == null || resultado.getMsg().equals("")) 
			resultado = null;
		enviarFront(resultado, request, response);
		
	}

}
