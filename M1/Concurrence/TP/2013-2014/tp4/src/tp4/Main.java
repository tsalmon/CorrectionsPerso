package tp4;

public class Main {
	static final int NB_VOITURES_NORD = 3;
	static final int NB_VOITURES_SUD = 2;

	public static void ex1(){
		RWbasic ptr = new RWbasic();
		int nb = 100;

		Reader rd = new Reader(nb, ptr);
		Writer wt = new Writer(nb, ptr);

		rd.start();
		wt.start();
		try {
			rd.join();
			wt.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	//pour l'exercice 2 il suffit  de remplacer RWbasic par RWexclusive

	public static void ex3(){
		ReadersWriters ptr = new ReadersWriters();
		int nb = 10;

		Reader rd = new Reader(nb, ptr);
		Writer wt = new Writer(nb*nb, ptr);

		rd.start();
		wt.start();
		try {
			rd.join();
			wt.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void ex4(){
		Pont_Voiture P_V = new Pont_Voiture();
		Voiture voiture_nord[] = new Voiture[NB_VOITURES_NORD];
		Voiture voiture_sud[] = new Voiture[NB_VOITURES_SUD];
		Pont p = new Pont(P_V);
		p.start();

		int i = 0;
		for(; i < NB_VOITURES_NORD; i++){
			voiture_nord[i] = new Voiture(true, i, P_V);
			voiture_nord[i].start();
		}

		for(i = 0; i < NB_VOITURES_SUD; i++){
			voiture_sud[i] = new Voiture(false, i + NB_VOITURES_NORD, P_V);
			voiture_sud[i].start();
		}

		try {
			for(i = 0; i < NB_VOITURES_SUD; i++){
				voiture_sud[i].join();
			}
			for(i = 0; i < NB_VOITURES_NORD; i++){
				voiture_nord[i].join();
			}
			p.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String [] args){
		ex4();
	}
}
