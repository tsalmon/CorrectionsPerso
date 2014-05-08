import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;


public class Classe4 extends JFrame implements ActionListener{

	class MonComposant extends JComponent{
		Color c = Color.WHITE;//default background color
		Color c_rect = Color.BLACK; //default rectangle color 
		
		MonComposant(Color c, Color c_rect){
			this.c = c;
			this.c_rect = c_rect;
		}

		MonComposant(Color c){
			this.c = c;
		}
		
		MonComposant(){}
		
		public void paintComponent(Graphics g){
			g.setColor(c);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.translate(this.getWidth()/4, this.getHeight()/4);

			g.setColor(c_rect);
			g.fillRect(0, 0, this.getWidth()/2, this.getHeight()/2);
		}
	}
	
	JMenuItem apropos;
	JMenuItem quitter;
	JButton btn_red = new JButton("Rouge");
	JButton btn_green = new JButton("Vert");
	JButton btn_blue = new JButton("Bleu");
	
	MonComposant vue = new MonComposant();
	
	JRadioButton fond = new JRadioButton("Fond");
	JRadioButton dessin = new JRadioButton("Dessin");
	
	Classe4(){
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

		/* content config */
		JPanel panel = new JPanel();
		ButtonGroup gp_radio = new ButtonGroup();
		gp_radio.add(fond);
		gp_radio.add(dessin);
		panel.setLayout(new GridLayout(2, 1));
		this.setContentPane(panel);
		this.setVisible(true);  //show the window

		JPanel panel_nord = new JPanel();
		JPanel panel_sud = new JPanel();

		panel_nord.add(btn_red);
		panel_nord.add(btn_green);
		panel_nord.add(btn_blue);

		panel_nord.add(fond);
		panel_nord.add(dessin);
				
		panel.add(panel_nord);
		panel.add(vue);
	
		btn_red.setBackground(Color.RED);
		btn_blue.setBackground(Color.BLUE);
		btn_green.setBackground(Color.GREEN);
		
		btn_red.setOpaque(true);
		btn_green.setOpaque(true);
		btn_blue.setOpaque(true);
			
		/*controls config*/
		this.quitter.addActionListener(this);
		this.btn_green.addActionListener(this);
		this.btn_red.addActionListener(this);
		this.btn_blue.addActionListener(this);
		this.fond.addActionListener(this);
		this.dessin.addActionListener(this);
		
		/* window config */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // click on cross make close the program
		this.setSize(300, 300); //default size
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.quitter){
			System.exit(0);
		}
		if(e.getSource() == this.btn_red){
			System.out.println(this.btn_red.getText());
		}
		if(e.getSource() == this.btn_green){
			System.out.println(this.btn_green.getText());
		}
		if(e.getSource() == this.btn_blue){
			System.out.println(this.btn_blue.getText());
		}
		if(e.getSource() == this.dessin){
			System.out.println(dessin.getText());
		}
		if(e.getSource() == this.fond){
			System.out.println(fond.getText());
		}
	}
	
	public static void main(String [] args){
		new Classe4();
	}
}
