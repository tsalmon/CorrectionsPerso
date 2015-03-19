package salmon;

import javax.ejb.Stateless;

@Stateless
public class HelloBean implements HelloRemote {

	@Override
	public String echo(String s) {
		System.out.println("HelloEcho "+s);
		return s;
	}

}
