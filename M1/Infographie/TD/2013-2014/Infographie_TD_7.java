import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.Math;

/*****************************
 * CLASSES A NE PAS MODIFIER *
 *****************************/
/* classe des points et vecteurs - reprise
   de l'algorithme de Roberts
 */                                               
class Coord3D {
    double x, y, z;
    Coord3D(){}
    Coord3D(double x, double y, double z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }    

    // methodes d'acces
    double getX() { return x; }
    double getY() { return y; }
    double getZ() { return z; }


    /* operations usuelles sur les vecteurs  : 
     * moins, plus, multiplication par un reel, 
     * forme normalisee, produit vectoriel, 
     * produit scalaire
     */
    Coord3D moins(Coord3D p) { return new Coord3D(x - p.x, y - p.y, z - p.z); }
    Coord3D plus (Coord3D p) { return new Coord3D(x + p.x, y + p.y, z + p.z); }
    Coord3D fois(double k)   { return new Coord3D(x * k, y * k, z * k); }
    Coord3D normaliser()     { return this.fois(1/norme()); }
    Coord3D vectoriel(Coord3D p)  {
	return new Coord3D
	    (y * p.z - z * p.y, 
	     z * p.x - x * p.z,
	     x * p.y - y * p.x);
    }
    double  scalaire(Coord3D p) { return x*p.x + y*p.y + z*p.z; }

    // norme d'un vecteur, norme au carre, 
    double  norme()  { return Math.sqrt(x * x + y * y + z * z); }
    double  sqNorme(){ return x * x + y * y + z * z; }

    // reflexion, relativement a un vecteur normal donne.
    Coord3D reflexion(Coord3D normal) {
	return moins(normal.fois(2*scalaire(normal)));
    }
    // utile pour le debuggage
    public String toString() {
	return ("x : " + x + " y : " + y + " z : " + z);
    }
}

/* classe d'un rayon - noter que la reflexion 
 * est deja implementee.
 */
class Rayon3D {
    Coord3D position;    
    Coord3D direction;
    Texture texture;     // texture accumulee par le rayon

    Rayon3D() {}
    Rayon3D(Coord3D position, Coord3D direction, Texture texture) {
	this.position  = position;
	this.direction = direction;
	this.texture   = texture;
    }
    // methodes d'acces
    Coord3D getPosition () { return position;  }
    Coord3D getDirection() { return direction; }
    Texture getTexture  () { return texture;   }

    /* calcul de la reflexion d'un rayon en un point 
     * d'impact. la texture du rayon reflechi est
     * obtenue par melange de la texture courante 
     * du rayon avec celle du point d'impact
     */
    Rayon3D reflexion(Impact impact) {
	return new Rayon3D
	    (impact.getPosition(),
	     direction.reflexion(impact.getNormale()), 
	     texture.mixer(impact.getTexture()));
    }
    // distance au carre entre pos et la position de imp
    double sqDistance(Impact imp) {
	return position.moins(imp.getPosition()).sqNorme();
    }
}

/* classe d'un point d'impact */
class Impact {
    Coord3D position;   // position de l'impact
    Coord3D normale;    // normale au point d'impact
    Texture texture;    // texture au point d'imapct
    Impact(Coord3D position, Coord3D normale, Texture texture) {
	this.position = position;
	this.normale  = normale;
	this.texture  = texture;
    }
    Coord3D getPosition() {
	return position;
    }
    Coord3D getNormale() {
	return normale;
    }
    Texture getTexture() {
	return texture;
    }
}

/* classe d'une texture d'objet, determinant les proprietes
 * de sa surface (couleur, coefficient de reflexion *)
 */
class Texture {
    // texture par defaut.
    static final Texture DEFAULT = new Texture();
    static final double ZERO = 0.001;   
    // kR         : coefficient de reflexion de <= ZERO (mat) a 1 (miroir)
    // cr, cg, cb : composantes de la couleur de la texture (0 a 1)
    double kR;
    double cr, cg, cb;
    Texture() { 
	this(1, 1, 1, 1); 
    }
    Texture(double kR, double cr, double cg, double cb) {
	this.kR = kR;
	this.cr = cr;
	this.cg = cg;
	this.cb = cb;
    }
    // determine si la surface est ou non reflechissante :
    // permet d'arreter immediatement l'algorithme du ray-tracing
    boolean estMate() {return kR <= ZERO; }
    // melange de deux textures. formule empirique, on pourrait
    // en trouver d'autres (addition, soustraction, xor, etc.)
    Texture mixer(Texture tex) {
	return new Texture 
	    (kR * tex.kR, 
	     (1 - kR)*cr + kR * cr * tex.cr,
	     (1 - kR)*cg + kR * cg * tex.cg,
	     (1 - kR)*cb + kR * cb * tex.cb);
    }
    // conversion en couleur des coefficients r, g, b
    Color toColor() {
	return new Color((int) (cr * 255), (int) (cg * 255), (int) (cb * 255));
    }
    // methodes uniquement destinees a etre reimplementees. la seconde permet 
    // par exemple de faire varier les caracteristiques d'une texture en 
    // fonction des coordonnees d'un point dans un repere local.
    Texture getTexture()                   { return this; }
    Texture getTexture(double x, double y) { return this; }
}

/* interface implementee par tout objet graphique ayant une surface
 * munie d'une texture. La methode est supposee renvoyer le point 
 * d'impact du rayon sur la surface, avec les champs de l'impact 
 * initialises comme suit :
 *
 * - position : coordonnees du point d'impact
 * - normal   : vecteur normal au point d'impact
 * - texture  : texture au point d'impact
 */
interface Surface {
    Impact getImpact(Rayon3D rayon); 
}



/***********************
 * CLASSES A COMPLETER *
 ***********************/

/*
class Sphere3D implements Surface { ... }
*/

/*
class Rectangle3D implements Surface { ... }
*/

/* .... autres classes ? .... */

// classe d'une scene 3D
class Scene3D {
    // scene et ciel par defaut
    static final Scene3D SAMPLE;
    static final Texture CIEL;
    static { // CODE D'INITIALISATION STATIQUE
	
	CIEL = new Texture(0, 0.8, 0.4, 1);
	SAMPLE = new Scene3D();

	/* AJOUTER ICI LES ELEMENTS DE LA SCENE
	 * via SAMPLE.ajouter(Surface surface)
	 *
	 * tenir compte dans le placement des objets
	 * de la position d'observation : le centre
	 * du plan de projection est en 
	 * x = 0, y = 100, z = 600,
	 * l'observateur est en 
	 * x = 0, y = 100, z = 1000,
	 * et regarde dans la direction des z negatifs.

	 * Exemple de scene, correspondant a l'image du cours :
	 *
	 * - une sphere de centre (-210, 140, 0), de rayon 140
	 * - une sphere de centre ( 210, 140, 0), de rayon 140
	 * - un rectangle horizontal 
	 *   de centre en (0,0,0), de taille 600 * 400
	 *
	 * les deux spheres sont de texture (1,0.9,0.9,0.9) et
	 * le rectangle est muni d'une texture en damier, mais
	 * vous pouvez dans un premier temps choisir des spheres
	 * colorees mates, et un rectangle non borne (i.e. un plan)
	 * colore mat. 
	 */
	
    } 
    Texture ciel;    
    // contenu de la scene
    Vector <Surface> contenu;
    Scene3D() {
	ciel = CIEL;
	contenu = new Vector <Surface>();
    }
    void setCiel(Texture ciel) {
	this.ciel = ciel;
    }
    void ajouter(Surface surface) {
	contenu.add(surface);
    }
    // IMPLEMENTER ICI L'ALGORITHME DU RAY_TRACING
    // les rayons fournis a cette methode seront ceux
    // allant de l'observateur aux pixels.
    Texture getTexture(Rayon3D rayon) {
	return CIEL;
    }
}

/*****************************************************/
/*            PARTIE A NE PAS MODIFIER               */
/*****************************************************/

// classe d'un observateur
class Observateur {
    Coord3D position; // position de l'observateur
    Coord3D uniteX;   // composante X du repere du plan de projection
    Coord3D uniteY;   // composante Y du repere du plan de projection
    Coord3D normale;  // normale au plan de projection (deduite)
    double  distance; // distance entre l'observateur et le plan
    
    Observateur(Coord3D position, 
	     Coord3D uniteX, Coord3D uniteY, double distance) {
	this.position = position;
	this.uniteX = uniteX.normaliser();
	this.uniteY = uniteY.normaliser();
	this.distance = distance;
	this.normale = uniteY.vectoriel(uniteX);
    }
    /* calcul du rayon allant de l'observateur
     * au point de coordonnees (i,j) dans le repere
     * local du plan de projection. cette methode
     * est appelee automatiquement par la methode
     * calculant le rendu de la scene
     */
    Rayon3D getRayon(int i, int j) {
	// calcul du rayon allant de l'observateur au point
	// de coordonnees (i, j) dans le plan de projection
	return new Rayon3D
	    (position, 
	     normale.fois(distance).
	     plus(uniteX.fois(i)).
	     plus(uniteY.fois(j)),
	     Texture.DEFAULT);
    }
}

/* classe d'une fenetre graphique munie d'un buffer */
class Visual extends JPanel {
    static final int WIDTH  = 600;
    static final int HEIGHT = 400;
    Graphics2D gBuffer;
    BufferedImage buffer;
    Visual() {
	buffer = new BufferedImage 
	    (WIDTH, HEIGHT, 
	     BufferedImage.TYPE_INT_RGB);
	gBuffer = (Graphics2D) buffer.getGraphics();
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g); 
	g.drawImage(buffer, 0, 0, this);
    }
    void render(Scene3D scene, Observateur o) {
	System.out.println("calcul du rendu...");
	int w = WIDTH/2, h = HEIGHT/2;
	for (int i = -w; i < w; i++)
	    for (int j = -h + 1; j <= h; j++) {
		Color c = scene.getTexture(o.getRayon(i,j)).toColor();
		buffer.setRGB(i + w, h - j, c.getRGB());
	    };
    }
    void clear() {
	gBuffer.clearRect(0,0,WIDTH, HEIGHT);
    }
}

public class Infographie_TD_7 {
    public static void main(String[] args) {
	// composants principaux
	final Visual visual = new Visual();


	// creation de l'observateur
	final Observateur o = 
	    new Observateur(new Coord3D(0,100,600),
			    new Coord3D(1,0,0),
			    new Coord3D(0,1,0), 
			    400);
	// construction de la scene
	final Scene3D scene =  Scene3D.SAMPLE;
	// listener
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    visual.clear();
		    /* calcul du rendu de la scene 
		     * vue par l'observateur
		     */
		    visual.render(scene, o);
		    visual.repaint();
		}
	    };
	// boitier de controle
	JPanel controlPanel = new JPanel ();
	Box box = new Box(BoxLayout.Y_AXIS);
	Button button = new Button("rendu");
	button.setActionCommand("ACT_0");
	button.addActionListener(listener);
	box.add(button);
	controlPanel.add(box);
	// assemblage
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
