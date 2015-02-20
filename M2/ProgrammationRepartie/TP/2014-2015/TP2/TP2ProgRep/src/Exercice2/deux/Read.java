package Exercice2.deux;

public class Read extends Thread {
	private Data data;
	private Integer number;
	
	public Read(Data data, Integer number) {
		this.data = data;
		this.number = number;
	}
	
	public void run() {
		data.read(number);
	}
}
