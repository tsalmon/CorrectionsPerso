package Exercice1;

public class Cons extends Thread{
	private File file;
	private Integer number;
	
	public Cons(File file, Integer number) {
		this.number = number;
		this.file = file;
	}
	
	public void run() {
		Integer value = 0;
		for (int i = 0; i < 10; i++) {
			value = file.get();
			System.out.println("Consommer #" + this.number + " got: " + value);
		}
	}
}
