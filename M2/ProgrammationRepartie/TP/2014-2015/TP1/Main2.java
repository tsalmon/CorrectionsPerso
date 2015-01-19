public class Main2{
	public static void main(String args[]){
		MonObjet o = new MonObjet(0);
		MyThread2 W, R;
		W = new MyThread2(o, 10000);
		R = new MyThread2(o, 10000);
		W.start();
		R.start();
		try{
			R.join();
			W.join();
		} catch(InterruptedException e){

		}
	}
}