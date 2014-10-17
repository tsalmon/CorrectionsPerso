import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * TP n°1:
 * 
 * Titre du TP : Jointure
 * Date : Vendredi 10 Octobre 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */

public class TestTable {

	@Test
	public void testFileNoExist() {
		String[] s = Table.tableFromFile("UnFichierQuiNExistePas.txt");
		if(s != null){
			fail("Ce fichier n'est pas sensé marché");			
		}
	}

	@Test
	public void testGetGoodsInformations() {
		String[] s = Table.tableFromFile("R");
		String[] s_test = { "R", "Z", "O", "X", "Q", "L", "J" };
		
		if(s.length != s_test.length){
			fail("N'a pas recuperer les bonnes valeurs (nombres de resultats differents)");
		}
		for(int i = 0; i < s.length; i++){
			if(!s[i].equals(s_test[i])){
				fail("N'a pas recuperer les bonnes valeurs");
			}			
		}
	}

	@Test
	public void testJointureCorrecte() {
		String[] r = {"A", "Z", "G","J","U","K","E","B","V","D"};
		String[] s = {"B", "U", "Z", "K", "X" ,"V", "N", "L", "M", "E"};
		String[] resultat = {"B","U","Z","K","V","E"};

		Table<String> t1 = new Table<String>(r);
		Table<String> t2 = new Table<String>(s);
		LinkedList<Element<String>> j = t1.join(t2);

		if(resultat.length != j.size()){
			fail("nombre de resultats de la jointure mauvais");			
		}
		for(int i = 0; i < j.size(); i++){
			if(resultat[i].equals(j.get(i))){
				fail("mauvais resultat: " + resultat[i] + ", " + j.get(i));
			}			
		}
		
	}
	
	
}
