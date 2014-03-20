import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * extension de la classe predefinie Polygon - c.f. la doc
 * de Polygon sur */
class MyPolygon extends Polygon {
    
    // renvoie l'indice du successeur du point n. i
    int succ(int i) {
	return (i + 1) % npoints;
    }
    // renvoie l'indice du predecesseur du point n. i
    int pred(int i) {
	return (i - 1 + npoints) % npoints;
    }

    /** ALGORITHME DE LA LIGNE DE BALAYAGE 
     * les listes de l'algorithme seront representees par
     * des ArrayList d'entrees, de classe Entree.
     * le tableau statique sera represente par une ArrayList
     * (la creation de tableaux d'objets de classe parametree
     *  est interdite).
     */

    // classe d'une entree de liste
    static class Entree {
	// x courant
	double x;
	// deltax 
	double dx;
	// hauteur courante
	int h;

	Entree (int x, double dx, int h) {
	    this.x  = x;
	    this.dx = dx;
	    this.h  = h;
	}
	// mise a jour de l'entree
	void maj() {
	    x += dx;
	    h--;
	}
	// extraction du x courant, converti au int le plus proche
	int getX() {
	    return (int) Math.round(x);
	}
	// indique que l'entree est de hauteur nulle
	boolean nul() {
	    return h == 0;
	}
	// insertion de l'entree a sa place correcte dans
	// un vecteur trie d'entrees
	void insertion(ArrayList<Entree> v) {
	    int i;
	    for (i = 0; i < v.size(); i++) {
		Entree e = v.get(i);
		if (e.x > x || (e.x == x && e.dx > dx)) 
		    break;
	    }
	    v.add(i, this);
	}
	// utile pour le debuggage
	public String toString() {
	    String s =  "(x : " + x + ", dx : " + dx + ", h : " + h + ")";
	    return s;
	}
    }

    /**
     * METHODE AUXILIAIRE
     * construction de l'entree pour l'arete dirigee 
     * (P_i, P_succ(i)) si succ vaut true, (P_i, P_pred(i)) 
     * sinon. si l'arete est dirigee vers le haut ou 
     * horizontale, cette methode renvoie null.
     */
    Entree construireEntree(int i, boolean succ) {
	int x = xpoints[i];
	int y = ypoints[i];
	int is  = succ ? succ(i)  : pred(i);
	int xs  = xpoints[is];
	int ys  = ypoints[is];
	// cas d'une arete dirigee vers le bas
	if (y < ys) {
	    return new Entree
		(x, ((double) (xs - x))/(ys - y), ys - y);
	}
	return null;
    } 

    
    /**
     * CONSTRUCTION DU TABLEAU STATIQUE
     */
    ArrayList<ArrayList<Entree>> construireStatique(int height) {
	// DEBUG
	System.out.println("construction du tableau statique :");
	// un vecteur par ligne
	ArrayList<ArrayList<Entree>> st = 
	    new ArrayList<ArrayList<Entree>>(height);
	for (int i = 0; i < height; i ++)
	    st.add(new ArrayList<Entree>());
	// pour chaque arete,
	for (int i = 0; i < npoints; i ++) {
	    /* construire l'entree de cette arete 
	     * (l'une au moins des entrees eg, ed
	     *  est a null, c.f. ci-dessus)
	     */
	    Entree eg = construireEntree(i, true);
	    Entree ed = construireEntree(i, false);
	    if (eg != null) {
		// DEBUG
		System.out.println
		    ("entree : " + " y : " + ypoints[i] + " : " + eg);
		eg.insertion(st.get(ypoints[i]));
	    }
	    if (ed != null) {
		// DEBUG
		System.out.println
		    ("entree : " + " y : " + ypoints[i] + " : " + ed);
		ed.insertion(st.get(ypoints[i]));
	    }
	}
	System.out.println("fin de la construction.\ndepart :");
	return st;
    }

    /**
     * REMPLISSAGE PAR BALAYAGE
     */
    void balayage(Graphics2D g, int width, int height) {
	// 1ere etape : on dessine en rouge le bord du polygone,
	// on passe au blanc pour afficher l'interieur :
	g.setColor(Color.red);
	g.draw(this);
	g.setColor(Color.white);
	// 2eme etape, on construit le tableau statique
	ArrayList<ArrayList<Entree>> 
	    st = construireStatique(height);
	// 3eme etape, on construit la liste dynamique,
	// initialement vide, et on lance le balayage.
	ArrayList<Entree> dyn = new ArrayList<Entree>();
	for (int y = 0; y < height; y++) {
	    // mises a jours, et suppression des entrees negatives
	    for (int j = dyn.size() - 1; j >= 0; j--) {
		Entree e = dyn.get(j);
		e.maj();
		if (e.nul()) {
		    // DEBUG
		    System.out.println("ligne " + y + " : suppression : " + e);
		    dyn.remove(j);
		}
	    }
	    // insertion des nouvelles entrees
	    for (int j = 0; j < st.get(y).size(); j++) {
		Entree e =  st.get(y).get(j);
		// DEBUG
		System.out.println 
		    ("ligne " + y + " : insertion : " + e);
		e.insertion(dyn);
	    }
	    // affichage 
	    for (int j = 0; j < dyn.size(); j +=2) {
		int xd = dyn.get(j).getX();
		int xf = dyn.get(j+1).getX(); 
		g.drawLine(xd, y, xf, y);
	    }
	}
    }
}

/****************************************************/
/*          PARTIE A NE PAS MODIFIER                */
/****************************************************/

class Visual extends JPanel {
    static final int DEF_WIDTH  = 600;
    static final int DEF_HEIGHT = 400;
    BufferedImage buffer;
    Graphics2D gBuffer;

    Visual() { 
	this(DEF_WIDTH,DEF_HEIGHT); 
    }
    Visual(int width, int height) {
	buffer = new BufferedImage 
	    (width, height, 
	     BufferedImage.TYPE_INT_RGB);
	gBuffer = (Graphics2D) buffer.getGraphics();
	setPreferredSize(new Dimension(width, height));
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g); 
	g.drawImage(buffer, 0, 0, this);
    }

    void clear() {
	gBuffer.clearRect(0,0, getWidth(), getHeight());
    }
    void xor(Shape s) {
	gBuffer.setXORMode(Color.blue);
	if (s instanceof MyPolygon) {
	    MyPolygon p = (MyPolygon) s;
	    if (p.npoints != 2) gBuffer.draw(p);
	    else gBuffer.drawLine(p.xpoints[0], p.ypoints[0],
				  p.xpoints[1], p.ypoints[1]);
	}
	else gBuffer.draw(s);
	gBuffer.setPaintMode();
	repaint();
    }
    void draw(Shape s) {
	gBuffer.draw(s);
	repaint();
    }
    void balayage(MyPolygon p) {
	clear();
	p.balayage(gBuffer, getWidth(), getHeight());
	repaint();
    }
    
}

class Controller extends MouseInputAdapter implements ActionListener {
    MyPolygon  next;
    Visual   visual;
    boolean  entree;
    Controller(Visual visual) {
	this.visual = visual;
    }
    public void actionPerformed(ActionEvent e) {
	if (next == null) {
	    System.out.println("entrez d'abord un polygone !");
	    return;
	}
	entree = false;
	System.out.println("calcul du rendu par balayage...");
	visual.balayage(next);
	System.out.println("termine !");
    }
    public void mousePressed(MouseEvent e) {
	if (next == null || !entree) {
	    entree = true;
	    next = new MyPolygon ();
	    next.addPoint(e.getX(),e.getY());
	    next.addPoint(e.getX(),e.getY());
	    visual.clear();
	    visual.xor(next);
	}
	else {
	    visual.xor(next);
	    if (e.getButton () != MouseEvent.BUTTON1) {
		visual.draw(next);
		entree = false;
	    }
	    else {
		next.addPoint(e.getX(),e.getY());
		visual.xor(next);
	    }
	
	}
    }
    public void mouseMoved (MouseEvent e) {
	if (entree) {
	    visual.xor(next);
	    if (next.npoints > 0) {
		next.xpoints[next.npoints - 1] = e.getX();
		next.ypoints[next.npoints - 1] = e.getY();
	    }
	    visual.xor(next);
	}
    }
    public void mouseDragged (MouseEvent e) {
	mouseMoved(e);
    }
}
    				     
public class Corr_Infographie_TD_4 {
    public static void main(String[] args) {
	Visual visual = new Visual();	
	Controller controller = new Controller(visual);
	visual.addMouseListener(controller);
	visual.addMouseMotionListener(controller);
	JPanel controlPanel = new JPanel ();
	Box box = new Box(BoxLayout.Y_AXIS);
	Button button = new Button("balayer");
	button.addActionListener(controller);
	box.add(button);
	controlPanel.add(box);
	JFrame mainFrame = new JFrame();
	box = new Box(BoxLayout.X_AXIS);
	box.add(visual);
	box.add(controlPanel);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	mainFrame.getContentPane().add(box);
	mainFrame.pack();
	mainFrame.setVisible(true);
    }
}
