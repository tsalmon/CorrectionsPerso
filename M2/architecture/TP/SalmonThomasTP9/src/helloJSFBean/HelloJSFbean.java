/**
 * TP 9
 * 
 * Titre : JSF
 * 
 * Date: Jeudi 5 Mars
 * 
 * Nom: Salmon
 * Prenom : Thomas
 * email: th_s@hotmail.fr
 */

package helloJSFBean;

import java.io.Serializable;

public class HelloJSFbean  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

}

