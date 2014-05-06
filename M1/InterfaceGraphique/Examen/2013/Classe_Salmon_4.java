import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

class MonComposant4 extends JComponent{
	private static final long serialVersionUID = 1L;
	
	MonComposant4(){
		this.setBackground(Color.RED);
	}

	MonComposant4(Color c){
		this.setBackground(c);
		this.add(new JButton("eee"));
	}
	
	public void paintComponent(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
	}
}

public class Classe_Salmon_4 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JMenuItem apropos;
	JMenuItem quit;
	Classe_Salmon_4(){
		super("Salmon");
		
		/* begin menu config*/
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
		/* end menu config */
			
		/* begin panel config */
		this.add(new MonComposant4(Color.BLUE));
		//this.setContentPane(panneau);
		/* end panel config */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);	
	}
	
	public static void main(String[] arg){
		new Classe_Salmon_4();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == apropos){
			JOptionPane.showMessageDialog( null , "C'est l'examen IG, ex2" );
		}
		if(e.getSource() == quit){
			System.exit(0);
		}
	}
}
