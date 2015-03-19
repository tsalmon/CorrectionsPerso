/**
 * TP n: 12
 *
 * Date : 13/03/2015
 * 
 * Nom  : Salmon Thomas
 * E-mail : th_s@hotmail.fr
 *
 * Remarques :
 */

package org.salmon.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloWorldBean implements SessionBean {

	protected SessionContext ctx;
	private static final long serialVersionUID = 1L;
	
	public String sayHello(){
		System.out.println("HelloWorld from client !");
		return "Hello, world !";
	}
	
	public void ejbCreate(){}
	
	@Override
	public void ejbActivate() throws EJBException, RemoteException {}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {}

	@Override
	public void setSessionContext(SessionContext context) {ctx=context;}

}
