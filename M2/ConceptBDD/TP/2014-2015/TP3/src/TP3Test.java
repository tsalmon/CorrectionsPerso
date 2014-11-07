import static org.junit.Assert.*;

import junit.framework.TestCase;

/**
 * TP n°3:
 * 
 * Titre du TP : Nested Loop
 * Date : Vendredi 10 Octobre 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */


public class TP3Test extends TestCase {
	//@Test
	public void testFileNoExist() {
		String[] s = Table.tableFromFile("UnFichierQuiNExistePas.txt");
		if(s != null){
			fail("Ce fichier n'est pas sensé marché");			
		}
	}

	//@Test
	public void testGetGoodsInformations() {
		String[] s = Table.tableFromFile("R");
		String[] s_test = {"A", "Z", "G", "J", "U", "K", "E", "B", "V", "D" };

		
		if(s.length != s_test.length){
			fail("N'a pas recuperer les bonnes valeurs (nombres de resultats differents)");
		}
		for(int i = 0; i < s.length; i++){
			if(!s[i].equals(s_test[i])){
				fail("N'a pas recuperer les bonnes valeurs");
			}			
		}
	}

	//@Test
	public void testJointureCorrecte() {
		
		String[] r = {"A", "Z", "G","J","U","K","E","B","V","D"};
		String[] s = {"B", "U", "Z", "K", "X" ,"V", "N", "L", "M", "E"};
		String[] resultat = {"Z","U","K","E","B","V"};

		String[] j = Table.jointure(r, s);
		
		for(int i = 0; i < resultat.length; i++){
			if(!resultat[i].equals(j[i])){
				fail("mauvais resultat: " + resultat[i] + ", " + j[i]);
			}			
		}
		 
	}
}
