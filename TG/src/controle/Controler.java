package controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AutorizarTrocaVH;
import controle.VH.DevolverProdutoVH;
import controle.VH.FinalizarVendaVH;
import controle.VH.IViewHelper;
import controle.VH.LoginVh;
import controle.VH.LogoutVH;
import controle.VH.Alterar.AlterarProdutoVH;
import controle.VH.Consultar.ConsultarCarrinhoVH;
import controle.VH.Consultar.ConsultarCuponsVH;
import controle.VH.Consultar.ConsultarGraficoVH;
import controle.VH.Consultar.ConsultarPedidoCliVH;
import controle.VH.Consultar.ConsultarProdutoVH;
import controle.VH.Consultar.ConsultarTrocasVH;
import controle.VH.Excluir.ExcluirIProdutoVH;
import controle.VH.Salvar.SalvarCartaoPagVH;
import controle.VH.Salvar.SalvarClienteVh;
import controle.VH.Salvar.SalvarItemCarrinhoVH;
import controle.VH.Salvar.SalvarProdutoVH;
import dominio.EntidadeDominio;
import util.Resultado;

//@WebServlet("/Controler")
public class Controler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, ICommand> commands;
    private Map<String, IViewHelper> vhs;

    
	public Controler() {
		super();
		commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("LOGIN", new ConsultarCommand());
		commands.put("LOGOUT", new ConsultarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("VISUALIZAR", new ConsultarCommand());
		commands.put("FINALIZAR VENDA", new SalvarCommand());
		commands.put("DEVOLVER_PRODUTO", new AlterarCommand());
		commands.put("TROCAS", new ConsultarCommand());
		commands.put("AUTORIZAR_TROCA", new AlterarCommand());
		commands.put("CONSULTAR_PEDIDOS_CLI", new ConsultarCommand());
		commands.put("ADICIONAR NO CARRINHO", new SalvarCommand());
		
		vhs = new HashMap<String,IViewHelper>();
		vhs.put("/Less/formularios/CadCliSALVAR", new SalvarClienteVh());
		vhs.put("/Less/formularios/ItemCarrinhoADICIONAR NO CARRINHO", new SalvarItemCarrinhoVH());
		vhs.put("/Less/formularios/LoginLOGIN", new LoginVh());
		vhs.put("/Less/formularios/LoginLOGOUT", new LogoutVH());
		vhs.put("/Less/formularios/PedidoFINALIZAR VENDA", new FinalizarVendaVH());
		vhs.put("/Less/formularios/PedidoAUTORIZAR_TROCA", new AutorizarTrocaVH());
		vhs.put("/Less/formularios/CadProdSALVAR", new SalvarProdutoVH());
		vhs.put("/Less/formularios/CadProdCONSULTAR", new ConsultarProdutoVH() );
		vhs.put("/Less/formularios/PedidoDEVOLVER_PRODUTO", new DevolverProdutoVH());
		vhs.put("/Less/formularios/PedidoTROCAS", new ConsultarTrocasVH());
		vhs.put("/Less/formularios/PedidoCONSULTAR_PEDIDOS_CLI", new ConsultarPedidoCliVH());  
		vhs.put("/Less/formularios/CarrinhoCONSULTAR", new ConsultarCarrinhoVH());   
		vhs.put("/Less/formularios/CadProdEXCLUIR", new ExcluirIProdutoVH());
		vhs.put("/Less/formularios/CadProdALTERAR", new AlterarProdutoVH());
		vhs.put("/Less/formularios/CuponsCONSULTAR", new ConsultarCuponsVH());
		vhs.put("/Less/formularios/GraficoCONSULTAR", new ConsultarGraficoVH());
		vhs.put("/Less/formularios/CartaoPagSALVAR", new SalvarCartaoPagVH());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcessRequest(request, response);


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

				
		String OPERACAO = request.getParameter("OPERACAO");
		
		uri = uri + OPERACAO;

		Resultado resultado = null;
		
		IViewHelper vh = vhs.get(uri);
		
		EntidadeDominio entidade = vh.getEntidade(request);
		
		
		resultado = commands.get(OPERACAO).executar(entidade);

		
		vh.setView(resultado, request,response);
		
		
	}

}
