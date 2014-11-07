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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Table{

	/*
	 * @param tableFromFile: a file wich on entry by line (separated by '\n')
	 * @return array of string of lines
	 */
	static public final String[] tableFromFile(String fileName){
		BufferedReader br = null;		
		String buffer = "";
		try {
 
			String sCurrentLine;
			try{
				br = new BufferedReader(new FileReader(fileName));
			} catch(FileNotFoundException excfile){
				//System.err.println("file '" + fileName + "' doesn't exist.");
				return null;
			}
			while ((sCurrentLine = br.readLine()) != null) {
				buffer += sCurrentLine + '\n';
			}
			return buffer.split("\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}		
		return null;
 	}

	public static final void appendArrayStringToVector(Vector<String> t1, String[] str_array){
		int array_size = str_array.length;
		for(int i = 0; i < array_size; i++){
			t1.add(str_array[i]);
		}
	}
	
	public static void main(String args[]){
		if(args.length != 2){
			System.err.println("Need 2 parameters: file1 file2");
			System.exit(1);
		}
		
		String[] r = tableFromFile(args[0]);
		String[] s = tableFromFile(args[1]);
		
		Vector<String> t1 = new Vector<String>(r.length);
		Vector<String> t2 = new Vector<String>(s.length);
		
		appendArrayStringToVector(t1, r);
		appendArrayStringToVector(t2, s);
		
		System.out.println(t1);
		System.out.println(t2);
		t1.retainAll(t2);
		
		System.out.println("Result:");
		System.out.println(t1);
	}
}
