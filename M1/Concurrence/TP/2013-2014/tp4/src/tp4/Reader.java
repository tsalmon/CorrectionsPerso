package tp4;
/**
 * 1.3 Exercice
 * @author salmon
 *
 */
public class Reader extends Thread{
	int nr;
	RWbasic ref_RWbasic;
	
	//nr: nombre de lectures a effectuer
	//ptr: ref vers un objet RWbasic
	Reader(int nr, RWbasic ptr){
		this.nr = nr;
		this.ref_RWbasic = ptr;
	}
	
	public void run(){
		for(int i = 0; i < this.nr; i++){
			System.out.println("lu :" + this.ref_RWbasic.read());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
