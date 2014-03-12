package tp4;

public class Voiture extends Thread{
	int id; 
	Pont_Voiture ref_pv;
	boolean sens_voiture;
	
	Voiture(boolean s, int id, Pont_Voiture p){
		this.sens_voiture = s;
		this.id = id;
		this.ref_pv = p;
	}
	
	public void run(){
		while(true){
			this.ref_pv.voiture_passe(this);
			try {
				Thread.sleep(id * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
