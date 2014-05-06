
/**
 * ex4
 * @author salmon
 *
 */
public class Pont extends Thread{
	boolean sens = false;
	Pont_Voiture ptr_pv;
	int nb_voitures;

	Pont(Pont_Voiture pv){
		ptr_pv = pv;
	}

	boolean getsens(){
		return sens;
	}

	public void run(){
		
		/*
		for(;;){
			while(nb_voitures == 0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int d;
			do{
				d = nb_voitures;
				notify();
			}while(nb_voitures != d);
			sens = sens ^ true;
		}
		*/
	}
}