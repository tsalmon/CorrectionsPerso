/**
 * TP 7 : Servlet Context, Listener 
 * 
 * Date : 14 fevrier
 * 
 * Nom: Thomas
 * Prenom: Salmon
 * email: th_s@hotmail.fr
 * 
 * remarque:
 */


import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AttConListener
 *
 */
@WebListener
public class AttConListener implements ServletContextListener, ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public AttConListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         System.out.println("Ajout d'un attribut");
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         System.out.println("Supression d'un attribut");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         System.out.println("Context destroyed");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         System.out.println("modification d'un attribut");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         System.out.println("Context initialisé");
    }
	
}
