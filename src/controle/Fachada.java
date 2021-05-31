package controle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dao.CarrinhoDao;
import dao.CartaoDao;
import dao.ClienteDao;
import dao.IDao;
import dao.ItemCarrinhoDao;
import dao.LivroDao;
import dao.PedidoDao;
import dao.UsuarioDao;
import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;
import dominio.Pedido;
import dominio.Pessoa;
import dominio.Usuario;
import negocio.IStrategy;
import negocio.ValidarCarrinhoAlterar;
import negocio.ValidarCpf;
import negocio.ValidarCupom;
import negocio.ValidarDadosCartao;
import negocio.ValidarDadosObrigatoriosLivro;
import negocio.ValidarDadosPedido;
import negocio.ValidarItemCarrinho;
import negocio.ValidarLogin;
import negocio.ValidarPagamento;
import negocio.gerarCupom;
import util.Resultado;

public class Fachada implements IFachada {

	private Map<String, IDao> daos;
	private Map<String, List<IStrategy>> rns;
	private Map<String, List<IStrategy>> rnsAlterar;
	private StringBuilder sb = new StringBuilder();
	private Resultado resultado;

	public Fachada() {
		daos = new HashMap<String, IDao>();
		rns = new HashMap<String, List<IStrategy>>();
		rnsAlterar = new HashMap<String, List<IStrategy>>();

		daos.put(Cliente.class.getName(), new ClienteDao());
		daos.put(Pessoa.class.getName(), new ClienteDao());
		daos.put(Pedido.class.getName(), new PedidoDao());
		daos.put(Carrinho.class.getName(), new CarrinhoDao());
		daos.put(Usuario.class.getName(), new UsuarioDao());
		daos.put(ItemCarrinho.class.getName(), new ItemCarrinhoDao());
		daos.put(Livro.class.getName(), new LivroDao());
		daos.put(Cartao.class.getName(), new CartaoDao());
		
		
		List<IStrategy> rnsCarrinhoAlterar = new ArrayList<IStrategy>();
		rnsCarrinhoAlterar.add(new ValidarCarrinhoAlterar());
		
		List<IStrategy> rnsLivro = new ArrayList<IStrategy>();
		rnsLivro.add(new ValidarDadosObrigatoriosLivro());
		
		List<IStrategy> rnsCliente = new ArrayList<IStrategy>();
		rnsCliente.add(new ValidarCpf());

		List<IStrategy> rnsPedido = new ArrayList<IStrategy>();
		rnsPedido.add(new ValidarDadosPedido());
		rnsPedido.add(new gerarCupom());
		rnsPedido.add(new ValidarCupom());
		rnsPedido.add(new ValidarPagamento());
		
		List<IStrategy> rnsCarrinho = new ArrayList<IStrategy>();
		rnsCarrinho.add(new ValidarItemCarrinho());
		
		List<IStrategy> rnsLogin = new ArrayList<IStrategy>();
		rnsLogin.add(new ValidarLogin());
		
		List<IStrategy> rnsItemCarrinho = new ArrayList<IStrategy>();
		rnsItemCarrinho.add(new ValidarItemCarrinho());
		
		List<IStrategy> rnsCartao = new ArrayList<IStrategy>();
		rnsCartao.add(new ValidarDadosCartao());
		
		
		
		rns.put(Cliente.class.getName(), rnsCliente);
		rns.put(Pedido.class.getName(), rnsPedido);
		rns.put(Carrinho.class.getName(), rnsCarrinho);
		rns.put(Usuario.class.getName(), rnsCarrinho);
		rns.put(Livro.class.getName(), rnsLivro);
		rns.put(ItemCarrinho.class.getName(), rnsItemCarrinho);
		rns.put(Cartao.class.getName(), rnsCartao);
		rnsAlterar.put(Cliente.class.getName(), rnsCliente);
		rnsAlterar.put(Pedido.class.getName(), rnsPedido);
		rnsAlterar.put(Carrinho.class.getName(),rnsCarrinhoAlterar);
		rnsAlterar.put(Usuario.class.getName(), rnsCarrinho);
		rnsAlterar.put(Livro.class.getName(), rnsLivro);
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();

			
		List<IStrategy> rnsEntidade = rns.get(entidade.getClass().getName());
		executarRegras(entidade, rnsEntidade);

		if (sb.length() == 0) {

			IDao dao = daos.get(entidade.getClass().getName());
			dao.salvar(entidade);
		} 
		resultado.setMsg(sb.toString());
		resultado.setEntidades(new ArrayList<EntidadeDominio>());
		resultado.getEntidades().add(entidade);

		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		

		List<IStrategy> rnsEntidade = rnsAlterar.get(entidade.getClass().getName());
		executarRegras(entidade, rnsEntidade);

		if (sb.length() == 0) {
			IDao dao = daos.get(entidade.getClass().getName());
			dao.alterar(entidade);
			resultado.setMsg(null);

		} else {

			resultado.setMsg(sb.toString());
		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		sb.setLength(0);
		IDao dao = daos.get(entidade.getClass().getName());
		dao.excluir(entidade);


		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		IDao dao = daos.get(entidade.getClass().getName());
		List<EntidadeDominio> retorno = dao.consultar(entidade);
		resultado.setEntidades(retorno);
		return resultado;
	}

	private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
		sb.delete(0, sb.length());
		for (IStrategy rn : rnsEntidade) {
			String msg = rn.processar(entidade);
			if (msg != null) {
				sb.append(msg);
			}
		}

	}

}
