import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;

/* Pour la documentation des classes predefinies
 *   - Rectangle2D, Rectangle2D.Double
 *   - Line2D, Line2D.Double
 *   - Point2D, Point2D.Double
 * consulter :
 *
 * http://docs.oracle.com/javase/6/docs/api/
 */


/* extension de Rectangle2D.Double definissant la classe
 * d'un rectangle de fenetrage:
 * - de sommet superieur gauche (x,y)
 * - de largeur w
 * - de hauteur h
 */
class MyRectangle extends Rectangle2D.Double {   
    MyRectangle(double x, double y, double w, double h){
	super(x,y,w,h);
    }
    public Line2D clip(Line2D segment) {
	/* implementer ici l'algorithme de Cohen-Sutherland. 
	 * cette methode doit renvoyer :
	 *  - un nouveau segment egal au fenetrage du segment 
	 *    initial par this si le segment intersecte this
	 *  - null si le segment initial est totalement 
	 *    exterieur au rectangle.
	 */


	return new Line2D.Double(segment.getP1(), segment.getP2());
	/* implementation par defaut : nouveau segment 
	 * de memes coordonnees que le segment initial.
	 */
	
    }
}



/***** PARTIE A NE PAS MODIFIER *****/
public class Infographie_TD_2 {
     static final int DEF_W = 600, DEF_H = 400;
    public static void main(String[] args) {
	final JButton reset = new JButton("effacer");
	JPanel view = new JPanel() {
		BufferedImage buffer;
		Graphics2D gBuffer;
		MyRectangle rect;
		{  
		    buffer = new BufferedImage 
			(DEF_W, DEF_H, BufferedImage.TYPE_INT_RGB);
		    setPreferredSize(new Dimension(DEF_W, DEF_H));
		    gBuffer = (Graphics2D) buffer.getGraphics();
		    rect    = new MyRectangle 
			(150,100,300,200);
		    reset.addActionListener(new ActionListener () {
			    public void actionPerformed (ActionEvent e) {
				gBuffer.clearRect(0,0,getWidth(), getHeight());
				gBuffer.draw(rect);
				repaint();
			    }
			});
		    MouseInputAdapter adapter = new MouseInputAdapter () {
			    Line2D input = new Line2D.Double();
			    public void mousePressed(MouseEvent e) {
				Point2D p = e.getPoint();
				input.setLine(p,p);
				gBuffer.setXORMode(Color.blue);
				gBuffer.draw(input);
				repaint();
			    }
			    public void mouseReleased(MouseEvent e) {
				gBuffer.draw(input);
				input.setLine(input.getP1(),e.getPoint());
				gBuffer.setPaintMode();
				Line2D cut = rect.clip(input);
				if (cut != null) 
				    gBuffer.draw(cut);
				repaint();
			    }
			    public void mouseDragged(MouseEvent e) {
				gBuffer.draw(input);
				input.setLine(input.getP1(),e.getPoint());
				gBuffer.draw(input);
				repaint();  
			    }
			};
		    addMouseListener(adapter);
		    addMouseMotionListener(adapter);
		    gBuffer.draw(rect);
		    repaint();
		}
		public void paintComponent(Graphics g) {
		    g.drawImage(buffer, 0, 0, this);
		}    
	    };	
	JPanel panel = new JPanel();
	Box vbox = new Box(BoxLayout.Y_AXIS);
	vbox.add(reset);
	panel.add(vbox);
	Box hbox = new Box(BoxLayout.X_AXIS);
	hbox.add(view);
	hbox.add(panel);
	JFrame frame = new JFrame("Cohen-Sutherland");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(hbox);
	frame.pack();
	frame.setSize(new Dimension(DEF_W + panel.getWidth(), 
				    panel.getHeight()));
	frame.setVisible(true);
    }   
}