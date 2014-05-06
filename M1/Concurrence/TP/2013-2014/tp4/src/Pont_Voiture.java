

import java.util.LinkedList;

/**
 * Ex4
 * @author salmon
 *
 */

// Note: notify() va liberer le plus ancien thread en attente (ie qui a fait wait() )
public class Pont_Voiture {
	private LinkedList<Voiture> voitures_nord = new LinkedList<Voiture>();
	private LinkedList<Voiture> voitures_sud = new LinkedList<Voiture>();  
	boolean sens_circulation = false;

	void ajout_voiture_en_attente(Voiture v){
		if(v.sens_voiture){
			this.voitures_nord.add(v);
		} else {
			this.voitures_sud.add(v);
		}
	}

	//on retire une voiture de la file (celle qui est au debut)
	void attente_finie(Voiture v){
		if(v.sens_voiture){
			this.voitures_nord.pop();
		} else {
			this.voitures_sud.pop();
		}		
	}
	
	synchronized void voiture_passe(Voiture v){
		this.ajout_voiture_en_attente(v);		
		//tant que ce n'est ni le sens de la circulation ou qu'il y a des voitures avant nous, on attend
		while(v.sens_voiture != sens_circulation && !preums_file(v)){
			try {
				String str_sens = (v.sens_voiture) ? "Nord" : "Sud";
				System.out.println("Voiture " + v.id + " ,"+ str_sens +"  en attente");
				wait();
			} catch (InterruptedException e) {System.exit(1);}
		}
		//on peut passer
		System.out.println("Voiture" + v.id + " passe !");
		this.attente_finie(v);
		message(v.sens_voiture);
		notifyAll();
	}
	
	void message(boolean sens){
		String str_sens = (sens) ? "nord" : "sud";
		System.out.println("Une voiture passe par le " + str_sens);
	}
	
	private boolean preums_file (Voiture v) {
		if(v.sens_voiture){
			return voitures_nord.size() > 0 && voitures_nord.getFirst() == v;
		} else {
			return voitures_sud.size() > 0 && voitures_sud.getFirst() == v;
		}
	}
	
	boolean nord_libre(){
		return this.voitures_nord.size() == 0;
	}

	boolean sud_libre(){
		return this.voitures_nord.size() == 0;
	}
	
	boolean sens_libre(){
		if(this.sens_circulation){
			return nord_libre();
		} else {
			return sud_libre();
		}
	}
	
	synchronized void pont(){
		while(!sens_libre()){
			
		}
		this.sens_circulation = (this.sens_circulation) ? false : true;
		notifyAll();
	}
}
