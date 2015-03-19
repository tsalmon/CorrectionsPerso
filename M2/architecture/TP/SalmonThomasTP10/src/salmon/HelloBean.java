package salmon;
/**
 *  
 * TP n 10
 * 
 * Date: 12 mars 2015
 *  
 * Nom: Salmon
 * Thomas: Thomas
 * email: th_s@hotmail.fr 
 * 
 */

import java.io.Serializable;

import javax.faces.event.ActionEvent;

public class HelloBean implements Serializable{
	private static final long serialVersionUID = 9040359120893077422L;
	 
	private String name;
 
	public void actionHook(ActionEvent event) {
		String clientId = event.getComponent().getClientId();
		System.out.println(" to handle HMI concerns in the  "
				+ clientId);
	}
 
	public String applicationAction() {
		System.out.println("to process business logic and navigation handling");
		return null;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
}
