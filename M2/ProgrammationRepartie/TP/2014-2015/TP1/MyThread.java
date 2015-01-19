public class MyThread extends Thread {
	public FileConcur f;
	public int nb;
	public MyThread(int nb, FileConcur f){
		this.nb = nb;
		this.f = f;
	}
	
	public void run(){
		System.out.println("Je suis la thread " + ThreadID.get());		
		int k = ThreadID.get() + 1;
		for(int i = 0; i < nb; i++){
			f.enq(k);
			this.yield();
		}
		int x = 0;
		for(int i = 0; i < nb; i++){
			x = x + f.deq();
			this.yield();
		}	
		System.out.println("Thread " + ThreadID.get() + " somme " + x);
	}	
}
