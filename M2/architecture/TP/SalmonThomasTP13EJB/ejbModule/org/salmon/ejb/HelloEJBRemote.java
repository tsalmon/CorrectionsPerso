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

import javax.ejb.Remote;

@Remote
public interface HelloEJBRemote {
	public String getMessageToPrint();
}
