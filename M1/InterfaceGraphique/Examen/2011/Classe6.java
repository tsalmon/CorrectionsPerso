import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Classe6 extends JFrame implements ActionListener{
	JMenuBar menu = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenuItem quitter = new JMenuItem("Quitter");
	JButton ovale = new JButton("Ovale");
	JButton rectangle = new JButton("Rectangle");
	JButton Effacer = new JButton("Effacer");
	Dessin dessin = new Dessin();
	ButtonGroup bn=new ButtonGroup();
	JRadioButton dessinRadio = new JRadioButton("Dessin");
	JRadioButton fondRadio = new JRadioButton("Dessin");
	boolean isEmpty = true;

	Classe6(){
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
