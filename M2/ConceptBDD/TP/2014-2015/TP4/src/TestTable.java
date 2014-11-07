import static org.junit.Assert.*;

import junit.framework.TestCase;

/**
 * TP n°4:
 * 
 * Titre du TP : Merge Join 
 * Date : Vendredi 6 Novembre 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */


public class TestTable extends TestCase {
	//@Test
	public void testFileNoExist() {
		String[] s = Table.tableFromFile("UnFichierQuiNExistePas.txt");
		if(s != null){
			fail("Ce fichier n'est pas sensé marché");			
		}
	}

	//@Test
	public void testGetGoodsInformations() {
		String[] r = Table.tableFromFile("src/R");
		String[] r_test = {"A", "B", "B", "E", "G", "J", "K", "U", "V", "Z"};

		
		if(r.length != r_test.length){
			fail("N'a pas recuperer les bonnes valeurs (nombres de resultats differents)");
		}
		for(int i = 0; i < r.length; i++){
			if(!r[i].equals(r_test[i])){
				fail("N'a pas recuperer les bonnes valeurs");
			}			
		}
	}

	public void testJointureCorrecteFromExample() {		
		String[] r = {"A", "B", "B", "E", "G", "J", "K", "U", "V", "Z"};
		String[] s = {"B", "B", "E", "K", "M", "N", "U", "U", "V", "X"};
		String[] resultat = {"B","B","B","B","E","K","U","U","V"};

		String[] j = Table.mergejoin(r, s);
		
		for(int i = 0; i < resultat.length; i++){
			if(!resultat[i].equals(j[i])){
				fail("mauvais resultat: " + resultat[i] + ", " + j[i]);
			}			
		}
	}
	
	public void testJointureCorrecteFullSame() {		
		String[] r = {"A", "A"};
		String[] s = {"A", "A", "A"};
		String[] resultat = {"A", "A", "A", "A", "A"};

		String[] j = Table.mergejoin(r, s);

		for(int i = 0; i < resultat.length && resultat[i] != null; i++){
			if(!resultat[i].equals(j[i])){
				fail("mauvais resultat (indice=" + i + "): " + resultat[i] + ", " + j[i]);
			}			
		}
	}
}
