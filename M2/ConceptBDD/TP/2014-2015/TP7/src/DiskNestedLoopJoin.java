import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;



/**
 * TP n°7:
 * 
 * Titre du TP : Nested Loop Join 
 * Date : Jeudi 27 Novembre 2014
 * Nom  : Salmon
 * Prenom : Thomas
 * email : th_s@hotmail.fr
 * 
 * Remarques : 
 */

public class DiskNestedLoopJoin {
	int nb_r_files;
	int nb_s_file;	
	String[][] r_files;
	String[] s_file;
	
	DiskNestedLoopJoin(String[] args){
		if(!correctArgs(args)){
			System.err.println("Appel du programme: DiskNestedLoopJoin "+
													"-r <fichiers> ... "+
													"-s fichiers ");
			return;
		}
		
		r_files = new String[nb_r_files][0];
		s_file = new String[1];
		init_files(args);
		
		System.out.println("---- S -----");
		this.print_arraydim1(s_file);
		
		System.out.println("---- R ("+ nb_r_files +") -----");
		this.print_arraydim2(r_files);
	
		int all_files_size = this.length_arraydim2(r_files) * s_file.length;		
		String[] all_files = new String[all_files_size];

		int x = 0;
		for(int i = 0; i < r_files.length; i++){
			for(int j = 0; j < r_files[i].length; j++){
				for(int k = 0; k < s_file.length; k++){
					all_files[x++] = r_files[i][j];
				}
			}
		}

		for(int k = 0; k < all_files.length; ){
			for(int i = 0; i < s_file.length; i++){
				all_files[k] += s_file[i];
				k += 1;
			}
		}

		System.out.println("---- ALL -----");
		this.print_arraydim1(all_files);

		this.shuffle(all_files);

		System.out.println("---- ALL(SUFFLE) -----");
		this.print_arraydim1(all_files);
		
		int k = 0;
		for(int i = 0; i < all_files.length; i+=10){
			this.exportFile(all_files, "RS"+(k++), i, i+10);
		}
		
	}

	public void exportFile(String[] tab, String filename, int start, int end){		
		File fout = new File(filename);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (int i = start; i < end && i < tab.length; i++) {
			try {
				bw.write(tab[i]);
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int length_arraydim2(String[][] file){
		int k = 0;
		for(int i = 0; i < file.length; i++){
			k += file[i].length;
		}	
		return k;
	}
	
	public void print_arraydim2(String[][] files){
		for(int i = 0; i < files.length; i++){
			for(int j = 0; j < files[i].length; j++){
				System.out.print(files[i][j] + " ");
			}			
			System.out.println("");
		}
	}

	public void shuffle(String[] tab){
		for(int i = 0; i < tab.length; i++){
			int j = (int)(Math.random() * tab.length);
			String aux = tab[i];
			tab[i] = tab[j];
			tab[j] = aux;
		}
	}
	
	public void print_arraydim1(String[] files){
		for(int j = 0; j < files.length; j++){
			if(j > 0 && j % 10 == 0){
				System.out.println("");				
			}
			System.out.print(files[j] + " ");			
		}			
		System.out.println("");
	}
	
	public void init_files(String[] args){
		boolean s, r;
		int s_k, r_k;
		
		s_k = 0;
		r_k = 0;
		s = false;
		r = false;
				
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-s")){
				s = true;
				r = false;
				continue;
			} else if(args[i].equals("-r")){
				r = true;
				s = false;
				continue;
			} 
			
			if(s){
				s_file = this.tableFromFile(args[i]);
			}
	
			if(r){
				r_files[r_k++] = this.tableFromFile(args[i]);
			}
		}
	}
	
	public final String[] tableFromFile(String fileName){

		BufferedReader br = null;		
		String buffer = "";
		
		try {
 
			String sCurrentLine;
			try{
				br = new BufferedReader(new FileReader(fileName));
			} catch(FileNotFoundException excfile){
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

	boolean correctArgs(String [] args){
		boolean s, r;
		
		if(args.length < 4){
			System.err.println("Appel du programme: DiskNestedLoopJoin "+
													"-r <fichiers> ... "+
													"-s fichiers");
			return false;
		} 
		
		s = false;
		r = false;
				
		for(int i = 0; i < args.length; i++){
			if(args[i].charAt(0) == '-'){
				if(args[i].equals("-s")){
					if(s){
						return false;
					} else { 
						s = true;
						r = false;
						continue;
					}
				} else if(args[i].equals("-r")){
					if(r){
						return false;
					} else { 
						r = true;
						s = false;
						continue;
					}
				} else {
					return false;
				}
			}
			
			if(s){
				nb_s_file++;
			}
	
			if(r){
				nb_r_files++;
			}			
		}
				
		return nb_r_files > 0 && nb_s_file == 1 ;
	}
	
	public static void main(String [] args){
		DiskNestedLoopJoin dnlj = new DiskNestedLoopJoin(args);
	}
}
