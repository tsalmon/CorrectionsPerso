import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Classe7 extends JFrame implements ActionListener{
	JMenuBar menu = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenuItem quitter = new JMenuItem("Quitter");
	JButton ovale = new JButton("Ovale");
	JButton rectangle = new JButton("Rectangle");
	JButton Effacer = new JButton("Effacer");
	Dessin dessin = new Dessin();
	ButtonGroup bn=new ButtonGroup();
	JRadioButton dessinRadio = new JRadioButton("Dessin");
	JRadioButton fondRadio = new JRadioButton("Fond");
	Color d, f;
	
	Classe7(){
		super("Salmon");

		this.quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		ovale.addActionListener(this);
		rectangle.addActionListener(this);
		Effacer.addActionListener(this);
		dessinRadio.addActionListener(this);
		fondRadio.addActionListener(this);
		
		/*menu*/
		this.fichier.add(quitter);
		this.menu.add(fichier);
		this.setJMenuBar(menu);

		/*panneau*/
		JPanel panneau = new JPanel();
		JPanel panneau_bouton = new JPanel();
		JPanel panneau_forme = new JPanel();
		JPanel panneau_suppr = new JPanel();

		panneau_bouton.setLayout(new GridLayout(2,2));
		
		bn.add(dessinRadio);
		bn.add(fondRadio);

		panneau_forme.add(ovale);
		panneau_forme.add(rectangle);
		panneau_bouton.add(panneau_forme);
		panneau_suppr.add(Effacer);
		panneau_bouton.add(panneau_suppr);
		panneau_bouton.add(dessinRadio);
		panneau_bouton.add(fondRadio);
		
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
			dessin.id_forme = 2;
		}
		if(e.getSource() == rectangle){
			dessin.id_forme = 1;		
		}
		if(e.getSource() == Effacer){
			if(dessin.id_forme > 0 && JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Etes vous sur d'effacer")){
				dessin.id_forme = 0;
			}
		}
		if(e.getSource() == dessinRadio){
			Color couleur = JColorChooser.showDialog(this, "lol", Color.black);
			if(couleur != null){
				d = couleur;
			}
		}
		if(e.getSource() == fondRadio){
			Color couleur = JColorChooser.showDialog(this, "lol", Color.black);
			if(couleur != null){
				f = couleur;
			}			
		}
		dessin.repaint();
	}

	class Dessin extends JPanel{
		int id_forme = 0;
		Color bg = this.getBackground();
		Color couleur = null;
		
		public void make_rectangle(Graphics g){
			if(!dessinRadio.isSelected()){
				g.setColor(Color.GREEN);
			} 

			g.fillRect(dessin.getWidth()/4, dessin.getHeight()/4, dessin.getWidth()/2, dessin.getHeight()/2);
		}

		public void make_ovale(Graphics g){			
			if(!dessinRadio.isSelected()){
				g.setColor(Color.RED);
			} 
			
			g.fillOval(dessin.getWidth()/4, dessin.getHeight()/4, dessin.getWidth()/2, dessin.getHeight()/2);
		}
		
		public void paintComponent(Graphics g){
			if(fondRadio.isSelected()){
				g.setColor(f);
			} else {		
				g.setColor(bg);
			}
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			if(dessinRadio.isSelected()){
				g.setColor(d);
			} 
			
			if(id_forme == 1){
				this.make_rectangle(g);
			}
			if(id_forme == 2){
				this.make_ovale(g);
			}
		}
	}
}
