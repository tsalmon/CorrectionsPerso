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

package org.salmon.ejb;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloEJBServlet
 */
@Stateless
public class HelloEJB implements HelloEJBRemote, HelloEJBLocal {

    /**
     * Default constructor. 
     */
    public HelloEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String getMessageToPrint() {
		System.out.println("[Remote call] HelloWorld EJB Remote");
		return "HelloWorld EJB Remote";
	}

	@Override
	public void printMessage() {
		System.out.println("[Local  call] HelloWorld EJB Local");
	}

}
