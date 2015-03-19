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

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class Listener implements ActionListener {

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		System.out.println("Technical processng inside ProcessAction");

	}

}
