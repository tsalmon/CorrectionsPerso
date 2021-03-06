import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Classe_Salmon_2 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JMenuItem apropos;
	JMenuItem quit;
	Classe_Salmon_2(){
		super("Salmon");
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Fichier");
		apropos = new JMenuItem("a propos"); 
		quit = new JMenuItem("Quitter");

		apropos.addActionListener(this);
		quit.addActionListener(this);
		
		menu.add(apropos);
		menu.add(new JSeparator());
		menu.add(quit);
		menubar.add(menu);
		this.setJMenuBar(menubar);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);
	}
	
	public static void main(String[] arg){
		new Classe_Salmon_2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == apropos){
			JOptionPane.showMessageDialog( null , "C'est l'ex" );
		}
		if(e.getSource() == quit){
			System.exit(0);
		}
	}
}
