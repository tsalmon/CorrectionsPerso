package tp4;
/**
 * ex3
 * @author salmon
 *
 */
public class ReadersWriters extends RWbasic{
	int cpt_lecteurs;


	ReadersWriters(){
		this.cpt_lecteurs = 0;
	}

	public synchronized void startRead(){
		cpt_lecteurs++;
	}

	public synchronized void endRead(){
		if(this.cpt_lecteurs <= 0){
			return ;
		}
		this.cpt_lecteurs--;
		if(this.cpt_lecteurs == 0){
			notifyAll();
		}
	}

	public int read(){
		this.startRead();
		int d;
		synchronized (this) { d = this.data; }
		this.endRead();
		return d;
	}

	synchronized void write(){
		while(this.cpt_lecteurs != 0){}
		super.write();
	}
}
