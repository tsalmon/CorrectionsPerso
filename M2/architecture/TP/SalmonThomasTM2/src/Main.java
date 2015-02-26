import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
public class Main {
	
	public static void GetSetersGetGeters(Person p){
		Method[] methods = Person.class.getMethods();
		String view = "";
		String type = "";
		String name = "";
		String para = "";
		String str  = "";
		
		for (int methodNum=0; methodNum < methods.length; ++methodNum) {
			name = methods[methodNum].getName();

			if(name.substring(0, 3).equals("get")){
				view = "geter: ";
			} else if (name.substring(0, 3).equals("set")){
				view = "seter: ";				
			} else {
				continue;
			}
			view += Modifier.toString(methods[methodNum].getModifiers());
			type = methods[methodNum].getReturnType().getName();
			Class[] parameters = methods[methodNum].getParameterTypes();
			for(int paramList=0; paramList<parameters.length;++paramList){
				para += parameters[paramList].getName() + " ";
			}
			str = view + " " + type + " " + name + "(" + para + ")";
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) {
		GetSetersGetGeters(new Person("Thomas", "Salmon"));
    }
}