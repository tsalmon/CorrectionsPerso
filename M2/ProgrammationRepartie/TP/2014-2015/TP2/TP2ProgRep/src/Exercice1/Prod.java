package Exercice1;

public class Prod extends Thread{
	private File file;
	private Integer number;
	
	public Prod(File file, Integer number) {
		this.file = file;
		this.number = number;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Producer #" + this.number + " put: " + i);
			file.put(i);
			try {
				sleep(50);
			}
			catch (InterruptedException e) {}
		}
	}
}
