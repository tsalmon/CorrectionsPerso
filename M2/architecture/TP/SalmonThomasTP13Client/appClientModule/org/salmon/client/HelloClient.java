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

package org.salmon.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.salmon.ejb.HelloEJBRemote;

public class HelloClient {

	public static void main(String[] args) throws FileNotFoundException, IOException, NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL, "localhost");
		
		InitialContext ctx = new InitialContext(p);
		HelloEJBRemote ejb = (HelloEJBRemote) ctx.lookup("BenYoussefHamzaTP13/HelloEJB/remote");
		System.out.println("Server returned : "+ejb.getMessageToPrint());
	}

	public HelloClient() {
		super();
	}
}
