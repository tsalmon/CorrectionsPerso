
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * TP n°2:
 * 
 * Titre du TP : Join Vector
 * Date : Vendredi 10 Octobre 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */

public class TableTest {

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
		String[] resultat = {"Z","U","K","E","B","V"};

		Vector<String> t1 = new Vector<String>(r.length);
		Vector<String> t2 = new Vector<String>(s.length);
		
		Table.appendArrayStringToVector(t1, r);
		Table.appendArrayStringToVector(t2, s);
		
		t1.retainAll(t2);
		
		if(resultat.length != t1.size()){
			fail("bad length of result");
		}

		for(int i = 0; i < t1.size(); i++){
			if(!resultat[i].equals(t1.get(i))){
				fail("mauvais resultat: " + resultat[i] + ", " + t1.get(i));
			}			
		}
		 
	}
	
	
}
