import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;


/**
 * TP n°4:
 * 
 * Titre du TP : Hash join
 * Date : Vendredi 6 Novembre 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */
public class HashLinearProbing {
	private char[] values;
	private int[] keys;
	private static final char emptyChar = ' ';
	int size = 1000;
	int nb_data = 0;
	
	HashLinearProbing(){
		values = new char[size];
		keys = new int[size];
		for(int i = 0; i < values.length; i++){
			values[i] = emptyChar;
		}
		
	}

	private void reinitialize(){
		char[] new_values = new char[size * 2];
		int[] new_keys = new int[size * 2];

		for(int i = 0; i < nb_data; i++){
			new_values[i] = values[i];
			new_keys[i] = keys[i];
		}
		
		size = size*2;
		values = new_values;
		keys = new_keys;
	}
	
	private int hashCode(int hash){
		return hash % size;
	}
	
	public void put(char value, int key){
		if(nb_data == size){
			reinitialize();
		}

		int M = size;
		int index = this.hashCode(key);
		while(values[index] != emptyChar	&& keys[index] != key){
			System.out.println(index);
			index = (index + 1) % M; 
		}
		
		keys[index] = key;
		values[index] = value;
		nb_data++;
	}
	
	public char get(int key) throws java.lang.NullPointerException {
		 int M  = size;
		 int index = this.hashCode(key);
		 while(keys[index] != key && values[index] != emptyChar){
			 System.out.println("get" + index);
			 index = (index + 1) % M;
		 }
		 if(keys[index] == key){
			 return values[index];
		 } else {
			 throw new java.lang.NullPointerException();
		 }
	}
	
	static public final char[] tableFromFile(String fileName){

		BufferedReader br = null;		
		String buffer = "";
		String[] str_split;
		char[] char_ret;
		
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
			
			str_split=  buffer.split("\n");
			char_ret = new char[str_split.length];
			for(int i = 0; i < str_split.length; i++){
				char_ret[i] = str_split[i].charAt(0);
			}
			return char_ret;
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
	
	public static void main(String[] args){
		char[] s, r;
		HashLinearProbing ht;
		
		if(args.length != 2){
			System.err.println("Il faut 2 fichier en argument");
			return;
		}
		
		s = tableFromFile(args[0]);
		r = tableFromFile(args[1]);
		
		ht = new HashLinearProbing();
		
		for(int i = 0; i < s.length; i++){
			ht.put(s[i], (int)s[i]);
		}
		
		for(int i = 0; i < r.length; i++){
			try {
				if((int)r[i] == ht.get(r[i])){
					System.out.println(r[i]);
				}
			} catch(java.lang.NullPointerException ex) {
				
			}
		}
		
	}
}
