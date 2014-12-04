import java.io.*;
import java.util.*;

public class DiskMergeJoin {
	private final int ENTRIES_BY_FILES = 10;
	LinkedList<String> filenames = new LinkedList<String>();

	DiskMergeJoin(String filename){
		String[] lines = this.getStringsOfFile(filename);
		this.splitFile(filename, lines);
		// tri au prealable
		for(String file : filenames){
			this.sortFusionStrings(file);
		}
		this.sortFusionFiles();
	}

	public void sortFusionFiles(){
		while(this.filenames.size() > 1){
			for(int i = 1; i < this.filenames.size(); i+=2){
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
		this.triFusion(concat_lines, 0, concat_lines.length);
		this.printtoFile(filename1, concat_lines, 0, concat_lines.length);
	}
	
	public void sortFusionStrings(String filename){
		String[] tableau = this.getStringsOfFile(filename);
		if (tableau.length>0){
			triFusion(tableau, 0, tableau.length-1);
		}
		this.printtoFile(filename, tableau, 0, tableau.length);
	}

	private void triFusion(String tableau[],int deb,int fin){
		if (deb!=fin){
			int milieu=(fin+deb)/2;
			triFusion(tableau,deb,milieu);
			triFusion(tableau,milieu+1,fin);
			fusion(tableau,deb,milieu,fin);
		}
	}

	private void fusion(String tableau[],int deb1,int fin1,int fin2){
		int deb2=fin1+1;
		String table1[]=new String[fin1-deb1+1];
		int compt1=deb1;
		int compt2=deb2;

		for(int i=deb1; i<=fin1; i++){
			table1[i-deb1]=tableau[i];
		}

		
		
		for(int i=deb1;i<fin2;i++){        
			if(tableau.length == compt2){
				return;
			}
			if (compt1==deb2){
				break;
			}
			else if (compt2==(fin2+1)){
				tableau[i]=table1[compt1-deb1];
				compt1++;
			}
			else if (table1[compt1-deb1].charAt(0) <tableau[compt2].charAt(0)){
				tableau[i]=table1[compt1-deb1]; 
				compt1++;
			}
			else{
				tableau[i]=tableau[compt2];
				compt2++;
			}
			
		}
	}


	public void splitFile(String filename, String[] entries){
		int filename_k = 0;
		int i = 0;
		for(; i < entries.length; i+=this.ENTRIES_BY_FILES){
			String name = filename + filename_k;
			this.filenames.add(name);
			printtoFile(name, entries, i, i + this.ENTRIES_BY_FILES);
			filename_k++;
		}
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

		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String lines = "";
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				lines += line + "\n";
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
