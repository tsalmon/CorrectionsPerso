/**
 * TP n: 13
 *
 * Date : 13/03/2015
 * 
 * Nom  : Salmon Thomas
 * E-mail : th_s@hotmail.fr
 *
 * Remarques :
 */

package org.salmon.servlet;

import org.salmon.ejb.HelloEJBLocal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloEJBServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			InitialContext ctx = new InitialContext();
			HelloEJBLocal ejb = (HelloEJBLocal) ctx.lookup("BenYoussefHamzaTP13/HelloEJB/local");
			ejb.printMessage();
			PrintWriter writer = response.getWriter();
			writer.println("<h1>Please check Server console</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

}
