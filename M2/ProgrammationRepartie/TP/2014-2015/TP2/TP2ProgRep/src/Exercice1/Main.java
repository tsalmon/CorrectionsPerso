package Exercice1;

public class Main {
	public static void main(String[] args) {
		File f = new File();
		Prod p1 = new Prod(f, 1);
		Prod p2 = new Prod(f, 2);
		Cons c1 = new Cons(f, 1);
		Cons c2 = new Cons(f, 2);
		p1.start();
		c1.start();
		p2.start();
		c2.start();
	}
}
