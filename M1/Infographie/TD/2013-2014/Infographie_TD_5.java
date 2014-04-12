import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


// classe de l'ecran de l'application
class Ecran extends JPanel {
    // dimensions par defaut
    static final int WIDTH  = 600;
    static final int HEIGHT = 400;
    // couleur du bord des taches
    static final Color couleurBord = Color.red;
    // couleur de remplissage des taches
    static final Color couleurInterieur = Color.blue;
    // couleur des pixels non encore dessines
    static final Color couleurFond = Color.black;
    
    
    BufferedImage buffer;
    Graphics2D gBuffer;
    Ecran() { 
	buffer = new BufferedImage 
	    (WIDTH, HEIGHT, 
	     BufferedImage.TYPE_INT_RGB);
	gBuffer = (Graphics2D) buffer.getGraphics();
	gBuffer.setColor(couleurBord);
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    // methodes inutiles pour le remplissage.
    public void paintComponent(Graphics g) {
	super.paintComponent(g); 
	g.drawImage(buffer, 0, 0, this);
    }
    void effacer() {
	gBuffer.clearRect(0,0, WIDTH, HEIGHT);
    }
    void rafraichir() {
	repaint();
    }
    void selectionCouleurBord() {
	gBuffer.setColor(couleurBord);
    }
    void selectionCouleurInterieur() {
	gBuffer.setColor(couleurInterieur);
    }

    
    /* METHODES UTILES */

    // renvoie la hauteur de l'ecran 
    int hauteur() {
	return HEIGHT;
    }
    // renvoie la largeur de l'ecran
    int largeur() {
	return WIDTH;
    }
    /* methode permettant de determiner la couleur
     * d'un pixel (x,y). renvoie :
     * - couleurBord si la couleur du pixel (x,y)
     *   est la couleur de bord
     * - couleurInterieur si la couleur de ce
     *   ce pixel est celle de remplissage
     * - couleurFond sinon
     */
    Color couleurPixel(int x, int y) {
	Color c = new Color(buffer.getRGB(x, y));
	if (c.equals(couleurBord))
	    return couleurBord;
	if (c.equals(couleurInterieur))
	    return couleurInterieur;
	return couleurFond;
    }
    // trace d'une ligne sur l'ecran
    void tracerLigne(int x1, int y1, int x2, int y2) {
	gBuffer.drawLine(x1, y1, x2, y2);
    }
    // allumage de pixel
    void allumerPixel(int x, int y) {
	Color c = gBuffer.getColor();
	buffer.setRGB(x, y, c.getRGB());
    }

    /* ALGORITHME DE SMITH
     *
     * inutile de creer des classes pour la pile ou
     * pour les entrees de la pile : les classes 
     * predefinies Stack et Point suffisent.
     *
     * ajouter ici les methodes auxiliaires necessaires :
     * procedure de l'algo, estInterieur, etc.
     */

    // methode principale
    void remplirSmith(int x, int y) {
	// creation de la pile
	Stack<Point> stack = new Stack<Point>();

	//a completer
	System.out.println("non encore implemente !");
    } 
}


/****************************************************/
/*          PARTIE A NE PAS MODIFIER                */
/****************************************************/


class Controller extends MouseInputAdapter implements ActionListener {
    static final int ENTREE = 0, REMPL = 1;
    Ecran ecran;
    int x0, y0, xc, yc;    
    int mode;
    Controller(Ecran ecran) {
	this.ecran = ecran;
    }
    public void actionPerformed(ActionEvent e) {
	String s = e.getActionCommand();
	if (s.equals("ACT_0" )) {
	    System.out.println("mode entree");
	    ecran.selectionCouleurBord();
	    mode = ENTREE;
	    return;
	}	
	if (s.equals("ACT_1" )) {
	    System.out.println("mode remplissage (Smith)");
	    ecran.selectionCouleurInterieur();
	    mode = REMPL;
	    return;
	}
	if (s.equals("ACT_2" )) {
	    System.out.println("effacement !");
	    ecran.effacer();
	    ecran.rafraichir();
	    ecran.selectionCouleurBord();
	    mode = ENTREE;
	    return;
	}
    }
    public void mousePressed(MouseEvent e) {
	switch (mode) {
	case ENTREE :
	    x0 = e.getX();
	    y0 = e.getY();
	    xc = x0;
	    yc = y0;
	    return;
	case REMPL :
	    ecran.remplirSmith(e.getX(), e.getY());
	    ecran.rafraichir();
	    return;
	}
    }
    public void mouseReleased(MouseEvent e) {
	if (mode == ENTREE) {
	    ecran.tracerLigne(xc, yc, e.getX(), e.getY());
	    ecran.tracerLigne(x0, y0, e.getX(), e.getY());
	    ecran.rafraichir();
	}
    }
    public void mouseDragged (MouseEvent e) {
	if (mode == ENTREE) {
	    ecran.tracerLigne(xc, yc, e.getX(), e.getY());
	    xc = e.getX();
	    yc = e.getY();
	    ecran.rafraichir();
	}
    }
}
    				     
public class Infographie_TD_5 {
    public static void main(String[] args) {
	Ecran ecran = new Ecran();	
	Controller controller = new Controller(ecran);
	ecran.addMouseListener(controller);
	ecran.addMouseMotionListener(controller);
	JPanel controlPanel = new JPanel ();
	String[] labels = {"dessiner", 
			   "remplir",
			   "effacer"};
	Box box = new Box(BoxLayout.Y_AXIS);
	for (int i = 0; i < labels.length; i++) {
	    Button button = new Button(labels[i]);
	    button.setActionCommand("ACT_" + i);
	    button.addActionListener(controller);
	    box.add(button);
	}
	controlPanel.add(box);
	JFrame mainFrame = new JFrame();
	box = new Box(BoxLayout.X_AXIS);
	box.add(ecran);
	box.add(controlPanel);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	mainFrame.getContentPane().add(box);
	mainFrame.pack();
	mainFrame.setVisible(true);
    }
}
