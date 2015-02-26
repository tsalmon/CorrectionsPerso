import java.io.Serializable;

/**
 *  TM2 
 *  
 * Java Bean Intropection
 * 
 * Nom: salmon
 * Prenom: thomas
 * emalm: th_s@hotmail.fr
 *
 */

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fistName;
	private String lastName;
 
   public Person(String firstName, String lastName) {
	   this.fistName = firstName;
	   this.lastName = lastName;
   }
 
    public String getFirstName() {
    	return (this.fistName);
    }
    
    public String getLastName() {
    	return (this.fistName);
    }

    public void setFirstName(String fn) {
    	this.fistName = fn;
    }

    public void setLastName(String ln) {
    	this.lastName = ln;
    }

}