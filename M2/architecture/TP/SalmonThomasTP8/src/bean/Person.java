package bean;

public class Person implements java.io.Serializable{
	public String firstName = null;
	public String lastName = null;
	
	public Person(){
		
	}
	
	public String getfistName(){
		System.out.println(firstName);
		return firstName;
	}

	public String getlastName(){
		System.out.println(lastName);
		return lastName;
	}
	
	public  void setFistName(String first){
		System.out.println(first);
		this.firstName = first;
	}

	public  void setLastName(String last){
		System.out.println(last);
		this.lastName = last;
	}
	
}
