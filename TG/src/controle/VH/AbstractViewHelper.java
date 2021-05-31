package controle.VH;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Resultado;

public abstract class AbstractViewHelper implements IViewHelper {
	
	protected HttpSession session;
	protected RequestDispatcher rd;
	
	
    protected void enviarFront(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
    	
    	request.setAttribute("resultado", resultado);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
