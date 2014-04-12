import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Classe representant un point ou un vecteur en dimension 3,
 * similaire a la classe Point2D.Double avec une composantes
 * z supplementaire, plus quelques operations specifiques
 * (produits scalaire et vectoriel, etc.)
 */ 
class Coord3D {
    /**
     * constante necessaire, a cause de l'imprecision des 
     * calculs : si les trois composantes d'un vecteur ou d'un
     * point sont en valeur absolue plus petites que cette 
     * constante, alors elles sont considerees comme toutes 
     * trois nulles par la methode nul().
     */
    static final double ZERO = 0.000000001;
    
    /**
     * composantes d'un point ou d'un vecteur
     */
    double x, y, z;
    /**
     * constructeurs
     */
    Coord3D() {}
    Coord3D(Coord3D p) {
	setLocation(p);
    }
    Coord3D(double x, double y, double z) {
	setLocation(x, y, z);
    }
    /**
     * get/set
     */
    double getX() {
	return x;
    }
    double getY() {
	return y;
    }
    double getZ() {
	return z;
    }
    void setLocation (Coord3D p) {
	setLocation(p.x, p.y, p.z);
    }
    void setLocation (double x, double y, double z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    /**
     * produits vectoriel et scalaire
     */
    Coord3D vectoriel(Coord3D p) {
	return vectoriel (p.x, p.y, p.z);
    }   
    Coord3D vectoriel(double x, double y, double z) {
	return
	    new Coord3D
	    (this.y * z - this.z * y,
	     this.z * x - this.x * z,
	     this.x * y - this.y * x);
    }	
    double scalaire(Coord3D p) {
	return scalaire(p.x, p.y, p.z);
    }
    double scalaire(double x, double y, double z) {
	return this.x * x + this.y * y + this.z * z;
    }

    /**
     * addition, soustraction, multiplication par un reel
     */
    Coord3D plus(Coord3D p) {
	return plus(p.x, p.y, p.z);
    }
    Coord3D moins(Coord3D p) {
	return plus(-p.x, -p.y, -p.z);
    }
    Coord3D plus(double x, double y, double z) {
	return new Coord3D
	    (this.x + x, 
	     this.y + y, 
	     this.z + z);
    } 
    Coord3D fois(double a) {
	return new Coord3D
	    (a * x, 
	     a * y, 
	     a * z);
    }
    /**
     * test de (quasi) nullite 
     */
    boolean nul() {
	return  
	    Math.abs(x) < ZERO &&
	    Math.abs(y) < ZERO &&
	    Math.abs(z) < ZERO;
    }
    /**
     * conversion en chaine
     */
    public String toString() {
	return "(" + x + "," + y + "," + z + ")";
    }


    /*** ELEMENTS INUTILES POUR L'ALGO ***/
    Coord3D transformation(Transformation3D t) {
	double[] in = {x, y, z, 1}, out = new double[4];
	t.appliquer(in, out);
	return new Coord3D(out[0], out[1], out[2]);
    }
    Coord3D normaliser() {
	double n = norme();
	return new Coord3D (x/n, y/n, z/n);
    } 
    double norme() {
	return  Math.sqrt(x * x + y * y + z * z);
    }
    Point2D projectionXY() {
    	return new Point2D.Double(x,y);
    }
 
}

/**
 * Classe d'un polygone en dimension 3, similaire a 
 * Polygon2D avec des composantes de type double 
 * (et non int) et un tableau de composantes en plus 
 * (zpoints). On supposera ici que (1) le polygone est 
 * convexe, (2) trois points successifs du bord
 * ne sont jamais alignes, et (3) tous les points sont 
 * dans un meme plan. 
 */

class Polygon3D {
    /**
     * composantes des sommets du polygone
     */
    double[] xpoints, ypoints, zpoints;
    /**
     * nombre de points du polygone
     */
    int npoints;
 
    /**
     * methode renvoyant le sommet du polygone de rang 
     * (i % npoints)
     */
    Coord3D point(int i) {
    	i = i % npoints;
    	return new Coord3D 
    	(xpoints[i], ypoints[i], zpoints[i]);
    }
    /**
     * methode une normale au plan contenant ce
     * polygone. les points sont ordonnes de 
     * maniere a ce que cette normale aille 
     * vers l'interieur du polyedre dont ce
     * polygone est une face.
     * 
     */
    Coord3D getNormale() {
	Coord3D 
	    p0 = point(0), 
	    p1 = point(1), 
	    p2 = point(2);
	return p1.moins(p0).vectoriel(p2.moins(p0));
    }


    /**
     * ALGORITHME DE ROBERTS
     * 
     * [ab] : le segment a traiter,
     *  u   : la direction du regard de l'observateur
     * 
     * cette methode n'est appelee que si la face est avant.
     * elle doit renvoyer : 
     * (1) un vecteur vide si le polygone masque totalement l, 
     * (2) un vecteur contenant deux points P1 P2 (Coord3D) 
     *     ou [P1P2] est la partie visible du segment si 
     *     si celle-ci est en un seul morceau
     * (3) un vecteur contenant quatre points P1 P2 P3 P4
     *     ou [P1P2] et [P3P4] sont les parties visibles
     *     du segment si elles sont en deux morceaux.
     */
 
    Vector<Coord3D> partiesVisibles(Coord3D a, Coord3D b, Coord3D u)
	throws TestNonImplemente {
	// le vecteur auquel on doit ajouter les 
	// parties visibles de l
	Vector<Coord3D> v = new Vector<Coord3D>();
	// la normale a la face.
	Coord3D n = getNormale();

	// TEST 1

	// TEST 2
	
	// TEST 3

	// TEST 4
	
        // laisser ce lancement d'exception en place 
        // tant  que tous les tests n'auront pas
	// ete implementes
	throw new TestNonImplemente();

    }
    
    
  
    
    /* PARTIES INUTILES POUR L'ALGORITHME */
    boolean estFaceAvant(Coord3D u) {
	return getNormale().scalaire(u) > 0;
    }
    Polygon3D() {}
    Polygon3D(double[] xpoints, 
	      double[] ypoints, 
	      double[] zpoints, int npoints){	    
	this.xpoints = xpoints;
	this.ypoints = ypoints;
	this.zpoints = zpoints;
	this.npoints = npoints;
    }
    void addPoint(Coord3D p) {
	addPoint(p.getX(), p.getY(), p.getZ());
    }
    void addPoint(double x, double y, double z) {
	double[]
	    xpoints = new double[npoints + 1], 
	    ypoints = new double[npoints + 1],
	    zpoints = new double[npoints + 1];
	for (int i = 0; i < npoints; i++) {
	    xpoints[i] = this.xpoints[i]; 
	    ypoints[i] = this.ypoints[i]; 
	    zpoints[i] = this.zpoints[i];
	}
	xpoints[npoints] = x;
	ypoints[npoints] = y;
	zpoints[npoints] = z;
	npoints++;
	this.xpoints = xpoints;
	this.ypoints = ypoints;
	this.zpoints = zpoints;
    }
    Polygon3D transformation(Transformation3D t) {
	double[] 
	    xpoints = new double[npoints],
	    ypoints = new double[npoints],
	    zpoints = new double[npoints];
	Coord3D p;
	for (int i = 0; i < npoints; i++) {
	    p = point(i).transformation(t);
	    xpoints[i] = p.getX();
	    ypoints[i] = p.getY();
	    zpoints[i] = p.getZ();
	}
	return new Polygon3D
	    (xpoints, 
	     ypoints, 
	     zpoints, npoints);
    }
    Polygon projectionXY() {
	int[] 
	    xpoints = new int[npoints], 
	    ypoints = new int[npoints];
	for (int i = 0; i < npoints; i++) {
	    xpoints[i] = (int) this.xpoints[i];
	    ypoints[i] = (int) this.ypoints[i];
	}
	return new Polygon
	    (xpoints, 
	     ypoints, npoints);
    }
    public String toString() {
	String s = super.toString() + " : [";
	for (int i = 0; i < npoints; i ++) 
	    s += point(i).toString();
	return s + "]";
    }
}

/***** PARTIE A NE PAS MODIFIER *****/

class TestNonImplemente extends Exception {}

class Transformation3D {
    double[][] matrice;
    static Transformation3D rotationXY
	(double rotX, double rotY, 
	 double scale, int tx, int ty) {
	double 
	    cx = Math.cos(rotX),
	    cy = Math.cos(rotY),
	    sx = Math.sin(rotX),
	    sy = Math.sin(rotY);
	double[][] m =
	    {{cx * scale, 0, sx * scale, tx},
	     {sx * sy * scale, 
	      cy * scale, -(cx * sy * scale), ty},
	     {-(sx * cy * scale), sy * scale, 
	      cx * cy *scale, 0},
	     {0,0,0,1}};
	return new Transformation3D(m);
    }
    Transformation3D(double[][] matrice) {
	this.matrice = matrice;
    }
    void appliquer(double[] inPoint, double[] outPoint) {
	for (int i = 0; i < 4; i++) {
	    double s = 0;
	    for (int j = 0; j < 4; j++) 
		s+= matrice[i][j]*inPoint[j];
	    outPoint[i] = s;
	}
    }
}



public class Infographie_TD_6 {
  
    public static void main(String[] args) {
	Coord3D 
	    a = new Coord3D(0,-1,1),
	    b = new Coord3D(1,-1,-1),
	    c = new Coord3D(-1,-1,-1),
	    d = new Coord3D(0,1,0);
	final Polygon3D[]
	    facesP = {new Polygon3D(), 
		     new Polygon3D(), 
		     new Polygon3D(),
		     new Polygon3D()};
	facesP[0].addPoint(a);
	facesP[0].addPoint(d);
	facesP[0].addPoint(b);
	facesP[1].addPoint(a);
	facesP[1].addPoint(c);
	facesP[1].addPoint(d);
	facesP[2].addPoint(b);
	facesP[2].addPoint(d);
	facesP[2].addPoint(c);
	facesP[3].addPoint(a);
	facesP[3].addPoint(b);
	facesP[3].addPoint(c);

	final Coord3D 
	    sa = new Coord3D(2, 0, 0.5), 
	    sb = new Coord3D(-1, 1, 2.5),
	    u  = new Coord3D(0, 0, -1);
	JPanel view = new JPanel() {
		BufferedImage buffer;
		Graphics2D gBuffer;
		{
		    buffer = new BufferedImage 
			(600, 400, BufferedImage.TYPE_INT_RGB);
		    gBuffer = (Graphics2D) buffer.getGraphics();
		    MouseInputAdapter adapter = new MouseInputAdapter () {
			    public void mouseMoved(MouseEvent e) {
				Point2D p = e.getPoint();
				double 
				    rotX = ((p.getX() - 300)* Math.PI)/150, 
				    rotY = ((p.getY() - 200)* Math.PI)/100;
				affichage(rotX, rotY);
			    }
			};
		    addMouseMotionListener(adapter);
		    setPreferredSize(new Dimension(600, 400));
		    affichage(0,0);
		}
		public void affichage(double rotX, double rotY) {
		    gBuffer.clearRect(0,0,600,400);
		    Transformation3D t = Transformation3D.rotationXY
			(rotX, rotY, 100, 300, 200);
		    Vector<Coord3D> v = new Vector<Coord3D>();
		    v.add(sa.transformation(t));
		    v.add(sb.transformation(t));
		    for (int i = 0; i < facesP.length; i++) {
			Polygon3D face = facesP[i].transformation(t);  
			if (face.estFaceAvant(u)) {
			    gBuffer.draw(face.projectionXY());
			    for (int j = v.size() - 1; j >= 0; j -=2) {
			    	Coord3D lb = v.remove(j);
				Coord3D la = v.remove(j-1);
				try {
				    v.addAll(face.partiesVisibles(la,lb,u));
				}
				catch (TestNonImplemente e) {
				    gBuffer.setColor(Color.red);
				    Point2D 
					pa = la.projectionXY(),
					pb = lb.projectionXY();
				    gBuffer.drawLine
					((int) pa.getX(), (int) pa.getY(),
					 (int) pb.getX(), (int) pb.getY());
				    gBuffer.setColor(Color.white);
				}
			    }
			}	
		    }
		    for (int j = v.size() - 1; j >= 0; j -=2) {
			Point2D 
			    pa = v.get(j).projectionXY(),
			    pb = v.get(j-1).projectionXY();
			gBuffer.drawLine
			    ((int) pa.getX(), (int) pa.getY(),
			     (int) pb.getX(), (int) pb.getY());
		    }
		    repaint();
		}
		public void paintComponent(Graphics g) {
		    super.paintComponent(g); 
		    g.drawImage(buffer, 0, 0, this);
		}
	    };	
	JFrame frame = new JFrame("Roberts");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	Container content = frame.getContentPane();
	content.add(view);
	frame.pack();
	frame.setVisible(true);
    }   
}
