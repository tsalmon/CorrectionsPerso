package salmon;

/**
 *  
 * TP n 11
 * 
 * Date: 12 mars 2015
 *  
 * Nom: Salmon
 * Thomas: Thomas
 * email: th_s@hotmail.fr 
 * 
 */
import salmon.HelloRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	public static void main(String[] args){
		try {
			InitialContext ctx = new InitialContext();
			Object obj = ctx.lookup("HelloBean/remote");
			System.out.println("lookp returned " + obj);
			HelloRemote hello = (HelloRemote) obj;
			String s = hello.echo("Hello EJB3");
			System.out.println(hello + "echo return");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
