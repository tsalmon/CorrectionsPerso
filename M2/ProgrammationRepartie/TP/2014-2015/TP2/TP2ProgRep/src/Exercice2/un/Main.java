package Exercice2.un;

public class Main {
// start ma chua gaint duoc thi nghia la no dang wait
	public static void main(String[] args) {
		Data d = new Data();
		int nWriter = 3;
		int nReader = 5;
		for(int i=0; i<nReader; i++) {
			Read r = new Read(d, i);
			r.start();
		}
		for(int i=0; i<nWriter; i++) {
			Write w = new Write(d, i);
			w.start();
		}
		for(int i=0; i<nReader; i++) {
			Read r = new Read(d, i + nReader);
			r.start();
		}
	}
}
