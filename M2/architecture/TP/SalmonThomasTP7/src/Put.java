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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Put
 */
public class Put extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = this.getServletConfig().getInitParameter("firstName");
		String lastName = this.getServletConfig().getInitParameter("lastName");
		this.getServletContext().setAttribute("firstName", firstName);
		this.getServletContext().setAttribute("lastName", lastName);
		PrintWriter w = response.getWriter();
		w.println(firstName+" "+lastName+" From PutServlet");
	}

}
