public class Main{
	public static void main(String args[]){
		FileConcur f = new FileConcur();
		MyThread W = new MyThread(10000, f);
		MyThread R = new MyThread(10000, f);
		W.start();
		R.start();
		try {
			R.join();
			W.join();
		} catch(InterruptedException e) {
		}	
		System.out.println("file utilise par deux threads " + f.head + " texte/fin " + f.tail);
	}
}
