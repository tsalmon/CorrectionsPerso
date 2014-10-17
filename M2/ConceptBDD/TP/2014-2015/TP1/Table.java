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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Element<T>{
	private T obj;

	Element(T t){
		obj = t;
	}

	public final T getObj(){
		return obj;
	}

	public final void setObj(T elem){
		obj = elem;
	}

	public final String toString(){
		return obj.toString();
	}
}

class Table<T>{
	private LinkedList<Element<T>> items = new LinkedList<Element<T>>();

	public Table(T[] array){
		for(T t : array){
			items.add(new Element<T>(t));
		}
	}

	public final LinkedList<Element<T>> join(Table<T> b){
		LinkedList<Element<T>> jointure = new LinkedList<Element<T>>();
		int table_size = b.getItemsSize();
		for(int i = 0; i < table_size; i++){
			Element<T> item = b.getItem(i);
			if(this.existInTable(item)){
				jointure.add(item);
			} 
		}
		return jointure;
	}

	public final Element<T> getItem(int index) throws IllegalArgumentException{
		if(index < 0 || index >= this.getItemsSize()){
			throw new IllegalArgumentException("depassement du tableau");
		}
		return this.items.get(index);
	}

	public final int getItemsSize(){
		return this.items.size();
	}	

	private final boolean existInTable(Element<T> e){
		int this_size = this.getItemsSize();
		for(int i = 0; i < this_size; i++){
			if(e.getObj().equals(this.getItem(i).getObj())){
				return true;
			}
		}
		return false;
	}

	public final String toString(){
		return this.items.toString();
	}

	static public final String[] tableFromFile(String fileName){
		BufferedReader br = null;		
		String buffer = "";
		try {
 
			String sCurrentLine;
			try{
				br = new BufferedReader(new FileReader(fileName));
			} catch(FileNotFoundException excfile){
				System.err.println("le fichier " + fileName + " n'existe pas");
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
	
	public static void main(String args[]){
		String[] r = tableFromFile(args[0]);
		String[] s = tableFromFile(args[1]);
		
		Table<String> t1 = new Table<String>(r);
		Table<String> t2 = new Table<String>(s);
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t1.join(t2));		
	}
}
