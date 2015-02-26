/**
 * TP 7 : Servlet Context, Listener 
 * 
 * Date : 14 fevrier
 * 
 * Nom: Thomas
 * Prenom: Salmon
 * email: th_s@hotmail.fr
 * 
 * remarque:
 */


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Get
 */
public class Get extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = (String)this.getServletContext().getAttribute("firstName");
		String lastName = (String)this.getServletContext().getAttribute("lastName");
		PrintWriter w = response.getWriter();
		w.println(firstName+" "+lastName+" From GetServlet");
	}

}
