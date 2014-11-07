import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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

public class Table {
	
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
	
	static public final String[] jointure(String[] t1, String[] t2){
		String[] ret = new String[10];
		int size_ret = 0;
		for(int i = 0; i < t1.length; i++){
			boolean exist_in_t2 = false;
			for(int j = 0; j < t2.length && !exist_in_t2; j++){
				if(t1[i].equals(t2[j])){
					exist_in_t2 = true;
				}
			}
			if(exist_in_t2){
				ret[size_ret++] = t1[i];				
			}
		}
		
		for(int i = size_ret; i < 10; i++){
			ret[i] = null;
		}
		return (ret);
	}

	static public final void writeFileFromTable(String fileName, String table[]){
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			for(int i = 0; i < table.length && table[i] != null; i++){
				writer.println(table[i]);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		if(args.length != 2){
			System.err.println("Need 2 parameters: file1 file2");
			System.exit(1);
		}
		
		String[] r = tableFromFile(args[0]);
		String[] s = tableFromFile(args[1]);

		if(r.length != s.length || r.length != 10){
			System.out.println("Les deux tableaux doivent etres de taille 10");
		}  else {
			writeFileFromTable("/Users/salmon/Documents/Etudes/M2/ConceptBDD/TP/2014-2015/TP3/RS", jointure(r, s));		
		}
	}
}
