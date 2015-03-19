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

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LifeCycle implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("END PHASE "+arg0.getPhaseId());

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		if(arg0.getPhaseId() == PhaseId.RESTORE_VIEW){
			System.out.println("----------------------------------------");
		}
		System.out.println("START PHASE "+arg0.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
