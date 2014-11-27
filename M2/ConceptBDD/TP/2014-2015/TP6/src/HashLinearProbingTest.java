import static org.junit.Assert.*;

import org.junit.Test;


public class HashLinearProbingTest {

	@Test
	public void test() {
		
		char[] s, r;
		char[] rs = {'R', 'Z', 'O'};
		int k = 0;
		
		HashLinearProbing ht;
		
		s = HashLinearProbing.tableFromFile("S");
		r = HashLinearProbing.tableFromFile("R");
		ht = new HashLinearProbing();
		
		for(int i = 0; i < s.length; i++){
			ht.put(s[i], (int)s[i]);
		}
		
		for(int i = 0; i < r.length; i++){
			try {
				if((int)r[i] == ht.get(r[i]) && rs[k++] != r[i]){
					fail("Mauvais résultats");
				}
			} catch(java.lang.NullPointerException ex) {
				
			}
		}		
	}

}
