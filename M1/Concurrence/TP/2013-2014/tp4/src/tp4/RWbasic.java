package tp4;

/**
 * 1.3 Exercice
 * @author salmon
 *
 */

public class RWbasic {
	int data;
	
	RWbasic(){
		this.data = 0;
	}
	
	int read(){
		return data;
	}
	
	void write(){
		data++;
	}
}
