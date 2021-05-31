package filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.Resultado;

public class FiltroAutenticacao implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest requestOriginal, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
        HttpServletRequest request = (HttpServletRequest) requestOriginal;

        String recurso = request.getRequestURI().substring(request.getContextPath().length());
		
		String OPERACAO = request.getParameter("OPERACAO");
        

        
        if(!recurso.endsWith("/CadCli") && !recurso.endsWith("/Login")) {
        	if(OPERACAO != null && !OPERACAO.equals("LOGIN")) {
   	
	            HttpSession session = request.getSession();
	            if (null == session.getAttribute("usuario")) {
	            	Resultado result = new Resultado();
	            	result.setMsg("Você deve se autenticar antes de tentar acessar a página.");
	                request.setAttribute("resultado",result);
	                request.getRequestDispatcher("index.jsp").forward(request, response);
	                
	                return;
	            }
        	}
        }
        
        

        chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
