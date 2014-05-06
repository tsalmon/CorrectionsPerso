import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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


public class Classe_Salmon_5 extends JFrame implements ActionListener{
	class MonComposant5 extends JComponent{
		private static final long serialVersionUID = 1L;
		Color circleColor = Color.BLACK;
		
		MonComposant5(){
			this.setBackground(Color.RED);
		}

		MonComposant5(Color c){
			this.setBackground(c);
			this.add(new JButton("eee"));
		}

		public void paintInGreen(){
			circleColor = Color.GREEN;
			this.paint(this.getGraphics());
		}

		public void paintInBlue(){
			circleColor = Color.BLUE;
			this.paint(this.getGraphics());
		}
		
		public void paintComponent(Graphics g){
		    Graphics2D g2 =(Graphics2D) g;
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(this.getBackground());
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(circleColor);
			g2.fillOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		}
	}

	private static final long serialVersionUID = 1L;
	JMenuItem apropos;
	JMenuItem quit;
	JButton vert = new JButton("vert");
	JButton blue = new JButton("blue");
	MonComposant5 comp = new MonComposant5(Color.YELLOW);
	
	Classe_Salmon_5(){
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
		JPanel panneau_nord = new JPanel();
		panneau_nord.add(vert);
		panneau_nord.add(blue);
		this.add(comp);
		this.add(panneau_nord, BorderLayout.NORTH);
		
		this.vert.addActionListener(this);
		this.blue.addActionListener(this);
		
		/* end panel config */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);	
	}
	
	public static void main(String[] arg){
		new Classe_Salmon_5();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == apropos){
			JOptionPane.showMessageDialog( null , "C'est l'examen IG, ex2" );
		}
		if(e.getSource() == vert){
			comp.paintInGreen();
		}
		if(e.getSource() == blue){
			comp.paintInBlue();
		}
		if(e.getSource() == quit){
			System.exit(0);
		}
	}
}
