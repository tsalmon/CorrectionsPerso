import static org.junit.Assert.*;

import org.junit.Test;


/**
 * TP n°8:
 * 
 * Titre du TP : DiskMergeJoin 
 * Date : Jeudi 4 Dec 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */

public class DiskMergeJoinTest {

	@Test
	public void testTriFusion() {
		String[] R = {
				"A",
				"X",
				"Z",
				"E",
				"R",
				"S",
				"P",
				"I",
				"D",
				"S",
				"Q",
				"U",
				"I",
				"G",
				"J",
				"I",
				"Y",
				"T",
				"B",
				"S",
				"A",
				"X",
				"P",
				"M",
				"N",
				"I",
				"L",
				"U",
				"Y",
				"E",
				"Z",
				"Q",
				"D",
				"N",
				"B",
				"X"
		};
		
		String[] R_sorted = {
				"A",
				"A",
				"B",
				"B",
				"D",
				"D",
				"E",
				"E",
				"G",
				"I",
				"I",
				"I",
				"I",
				"J",
				"L",
				"M",
				"N",
				"N",
				"P",
				"P",
				"Q",
				"Q",
				"R",
				"S",
				"S",
				"S",
				"T",
				"U",
				"U",
				"X",
				"X",
				"X",
				"Y",
				"Y",
				"Z",
				"Z"
		};
		
		DiskMergeJoin d = new DiskMergeJoin();
		R = d.triFusion(R, 0, R.length-1);
		
		for(int i = 0; i < R.length; i++){
			if(R[i] != R_sorted[i]){
				fail("TriFusion : non trié (i="+i+") " + R[i] + " " + R_sorted[i]);
			}
		}
		
	}

}
