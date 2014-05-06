import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

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

public class Classe_Salmon_9 extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
	class MonComposant5 extends JComponent{
		private static final long serialVersionUID = 1L;
		Color circleColor = Color.BLACK;
		int x1, y1, x2, y2;
		boolean init = false;
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

			if(!init){
				x1 = this.getWidth()/2 - this.getWidth()/4;
				y1 = this.getHeight()/2 - this.getWidth()/4;
				x2 = x1*2;
				y2 = x1*2;
				init = true;
			}
			
			System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			g2.fillOval(x1, y1, x2, y2);
		}
	}

	private static final long serialVersionUID = 1L;
	JMenuItem apropos;
	JMenuItem quit;
	JButton vert = new JButton("vert");
	JButton blue = new JButton("blue");
	JLabel label_sud = new JLabel("Rien");
	JPanel panneau_sud = new JPanel();
	MonComposant5 comp = new MonComposant5(Color.YELLOW);
	int x, y; long t = -1;
	
	Classe_Salmon_9(){
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
		this.add(comp);
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		JPanel panneau_nord = new JPanel();
		panneau_nord.add(vert);
		panneau_nord.add(blue);
		this.add(panneau_nord, BorderLayout.NORTH);

		panneau_sud.add(label_sud);
		this.add(panneau_sud, BorderLayout.SOUTH);

		this.vert.addActionListener(this);
		this.blue.addActionListener(this);
		/* end panel config */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);
	}

	public static void main(String[] arg){
		new Classe_Salmon_9();
	}

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

	public void mouseReleased(MouseEvent e){
		if(e.getSource() == comp){
			int dist = (int) Point2D.distance(e.getX(), e.getY(), comp.getWidth()/2, comp.getHeight()/2);
			int ray = Math.abs(comp.x1 - comp.x2) ;//(int) Point2D.distance(comp.getWidth()/4, comp.getHeight()/4, comp.getWidth()/8, comp.getWidth()/8);
			System.out.println(dist + " " + ray);
			if(dist > ray){
				this.label_sud.setText("Clic de la souris en dehors du disque");
			} else {
				this.label_sud.setText("Clic de la souris dans le disque");
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(t == -1){
			t = e.getWhen();
			x = e.getX();
			y = e.getY();
			return ;
		}
		
		if((e.getWhen() - t < 100) && (20 < Point2D.distance(x, y, e.getX(), e.getY()))){
			if(Math.abs(x - e.getX()) < 3){
				this.label_sud.setText("Horizontal");
				if(e.getY() > y && comp.y1 < comp.getHeight()){
					comp.y1 += 20;
				} else if(comp.x1 > 10){
					comp.y1 -= 20;
				}
			} else if(Math.abs(y - e.getY()) < 3){
				this.label_sud.setText("Vertical");
				if(e.getX() > x && comp.x1 < comp.getWidth()){
					comp.x1 += 20;
				} else if (comp.y1 > 0){
					comp.x1 -= 20;
				}
			} else {
				this.label_sud.setText("Bizarre");
			}
			comp.paint(comp.getGraphics());
		}
		t = e.getWhen();
		x = e.getX();
		y = e.getY();
	}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e) {}
}
