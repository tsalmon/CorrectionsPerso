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

package org.salmon.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.salmon.ejb.HelloWorld;
import org.salmon.ejb.HelloWorldHome;

public class HelloClient {

	public static void main(String[] args) {
		Context initialContext = null;
		try{
			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			p.put(Context.PROVIDER_URL, "localhost");
			initialContext = new InitialContext(p);
			Object objRef = (HelloWorldHome) initialContext.lookup("myHelloWorld/home");
			HelloWorldHome home = (HelloWorldHome) PortableRemoteObject.narrow(objRef, HelloWorldHome.class);
			HelloWorld hello = home.create();
			System.out.println("test"+hello.sayHello());
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
