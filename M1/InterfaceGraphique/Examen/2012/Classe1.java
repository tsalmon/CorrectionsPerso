import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;


public class Classe1 extends JFrame implements ActionListener{
	JMenuItem apropos;
	JMenuItem quitter;

	Classe1(){
		super("Salmon"); //title of the window

		/*menu config*/
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Fichier");
		apropos = new JMenuItem("À propos");
		quitter = new JMenuItem("Quitter");
		menu.add(apropos);
		menu.add(new JSeparator());
		menu.add(quitter);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		/*controls config*/
		quitter.addActionListener(this);
		
		/* window config */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // click on cross make close the program
		this.setSize(300, 300); //default size
		this.setVisible(true);  //show the window
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == quitter){
			System.exit(0);
		}
	}
	
	public static void main(String [] args){
		new Classe1();
	}
}
