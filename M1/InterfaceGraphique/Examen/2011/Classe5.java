/*pareil que 4*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Classe5 extends JFrame implements ActionListener{
	JMenuBar menu = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenuItem quitter = new JMenuItem("Quitter");
	JButton ovale = new JButton("Ovale");
	JButton rectangle = new JButton("Rectangle");
	JButton Effacer = new JButton("Effacer");
	Dessin dessin = new Dessin();
	boolean isEmpty = true;

	Classe5(){
		super("Salmon");

		this.quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		ovale.addActionListener(this);
		rectangle.addActionListener(this);
		Effacer.addActionListener(this);

		/*menu*/
		this.fichier.add(quitter);
		this.menu.add(fichier);
		this.setJMenuBar(menu);


		/*panneau*/
		JPanel panneau = new JPanel();
		JPanel panneau_bouton = new JPanel();

		panneau_bouton.add(ovale);
		panneau_bouton.add(rectangle);
		panneau_bouton.add(Effacer);

		panneau.setLayout(new BorderLayout());
		panneau.add("West", panneau_bouton);
		panneau.add("Center",dessin);
		this.setContentPane(panneau);

		/*fenetre*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ovale){
			isEmpty = false;
			Graphics g = dessin.getGraphics();
			g.setColor(this.getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.RED);
			g.fillOval(dessin.getWidth()/4, dessin.getHeight()/4, dessin.getWidth()/2, dessin.getHeight()/2);
			dessin.paint(g);
		}
		if(e.getSource() == rectangle){
			isEmpty = false;
			Graphics g = dessin.getGraphics();
			g.setColor(this.getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.GREEN);
			g.fillRect(dessin.getWidth()/4, dessin.getHeight()/4, dessin.getWidth()/2, dessin.getHeight()/2);
			dessin.paint(g);			
		}
		if(e.getSource() == Effacer){
			if(!isEmpty && JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Etes vous sur d'effacer")){
				Graphics g = dessin.getGraphics();
				g.setColor(this.getBackground());
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				dessin.paint(g);
				isEmpty = true;
			}
		}
	}

	class Dessin extends JPanel{
		public void paintComponent(Graphics g){
		}
	}
}
