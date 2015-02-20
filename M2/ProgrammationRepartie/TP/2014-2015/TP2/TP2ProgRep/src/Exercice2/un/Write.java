package Exercice2.un;

public class Write extends Thread {
	private Data data;
	private Integer number;
	
	public Write(Data data, Integer number) {
		this.data = data;
		this.number = number;
	}
	
	public void run() {
		data.write(number);
	}
}
