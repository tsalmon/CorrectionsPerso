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
import javax.ejb.EJBObject;

public interface HelloWorld extends EJBObject {
	public String sayHello() throws RemoteException;
}
