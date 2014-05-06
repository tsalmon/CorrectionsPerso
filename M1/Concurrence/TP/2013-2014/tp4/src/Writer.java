

public class Writer extends Thread {
	int nw;
	RWbasic ref_RWbasic;
	
	//nw: nombre d'écriture a effectuer
	//ptr: ref vers un objet RWbasic
	Writer(int nw, RWbasic ptr){
		this.nw = nw;
		this.ref_RWbasic = ptr;
	}
	
	public void run(){
		for(int i = 0; i < this.nw; i++){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.ref_RWbasic.write();
		}
	}
}
