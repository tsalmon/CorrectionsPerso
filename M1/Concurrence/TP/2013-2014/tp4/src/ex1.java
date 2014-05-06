

class TestThread extends Thread{
	String str;
	public TestThread(String value){
		this.str = value;
	}
	
	public void run(){
		System.out.println("run : " + str);
	}
}

public class ex1{
	public static void main(String [] args){
		TestThread test1 = new TestThread("A");
		TestThread test2 = new TestThread("B");
		test1.start();
		test2.start();
		try {
			test1.join();
			test2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
