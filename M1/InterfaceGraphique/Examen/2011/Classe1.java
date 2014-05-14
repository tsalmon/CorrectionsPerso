import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Classe1 extends JFrame{
	JMenuBar menu = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenuItem quitter = new JMenuItem("Quitter");
	Classe1(){
		super("Salmon");

		quitter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.exit(0);
				}
		});
		
		fichier.add(quitter);
		menu.add(fichier);
		this.setJMenuBar(menu);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}
}
