import java.io.*;
import java.util.*;

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

public class DiskMergeJoin {
	private final int ENTRIES_BY_FILES = 10;

	DiskMergeJoin(){

	}

	DiskMergeJoin(String filename){
		String[] lines = this.getStringsOfFile(filename);
		LinkedList<String> filenames = this.splitFile(filename, lines);
		for(String file : filenames){
			this.sortFusionStrings(file);
		}
		this.sortFusionFiles(filenames);
	}

	public void sortFusionFiles(LinkedList<String> filenames){
		while(filenames.size() > 1){
			for(int i = 1; i < filenames.size(); i+=2){
				this.concatFiles(filenames.get(i-1), filenames.get(i));
				filenames.remove(i);
			}
		}
	}

	public void concatFiles(String filename1, String filename2){
		String[] lines1 = getStringsOfFile(filename1);
		String[] lines2 = getStringsOfFile(filename2);

		String[] concat_lines = new String[lines1.length + lines2.length];
		for(int i  = 0 ; i < lines1.length; i++){
			concat_lines[i] = lines1[i];
		}
		for(int i  = 0 ; i < lines2.length; i++){
			concat_lines[lines1.length + i] = lines2[i];
		}
		this.triFusion(concat_lines, 0, concat_lines.length-1);
		this.printtoFile(filename1, concat_lines, 0, concat_lines.length);
	}

	public void sortFusionStrings(String filename){
		String[] tableau = this.getStringsOfFile(filename);
		if (tableau.length>0){
			triFusion(tableau, 0, tableau.length-1);
		}
		this.printtoFile(filename, tableau, 0, tableau.length);
	}

	public String[] triFusion(String tableau[],int deb,int fin){
		if (deb!=fin){
			int milieu=(fin+deb)/2;
			triFusion(tableau,deb,milieu);
			triFusion(tableau,milieu+1,fin);
			fusion(tableau,deb,milieu,fin);
		}
		return tableau;
	}

	private void fusion(String tableau[],int debut,int milieu,int fin){

		int deb2=milieu+1;
		String table1[]=new String[milieu-debut+1];
		int compt1=debut;
		int compt2=deb2;

		for(int i=debut; i<=milieu; i++){
			table1[i-debut]=tableau[i];
		}

		for(int i=debut;i<=fin;i++){
			if (compt1==deb2){
				break;
			}
			else if (compt2==(fin+1)){
				tableau[i]=table1[compt1-debut];
				compt1++;
			}
			else if (table1[compt1-debut].charAt(0) <tableau[compt2].charAt(0)){
				tableau[i]=table1[compt1-debut]; 
				compt1++;
			}
			else{
				tableau[i]=tableau[compt2];
				compt2++;
			}

		}
		
	}

	public LinkedList<String> splitFile(String filename, String[] entries){
		LinkedList<String> filenames = new LinkedList<String>();
		int filename_k = 0;
		int i = 0;
		for(; i < entries.length; i+=this.ENTRIES_BY_FILES){
			String name = filename + filename_k;
			filenames.add(name);
			printtoFile(name, entries, i, i + this.ENTRIES_BY_FILES);
			filename_k++;
		}
		return filenames;
	}

	public void printtoFile(String sub_filename, String[] lines , int start, int end){
		File fout = new File(sub_filename);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.exit(1);
		}		

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (int i = start; i < end && i < lines.length; i++) {
			try {
				bw.write(lines[i]);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

	}

	public String[] getStringsOfFile(String filename) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String lines = "";
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				if(line.length() > 0 && line.charAt(0) != '\n'){
					lines += line + "\n";					
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return lines.split("\n");
	}

	public static void main(String[] args){
		if(args.length == 1){
			new DiskMergeJoin(args[0]);
		}
	}
}
