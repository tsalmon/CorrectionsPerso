package salmon;

import javax.ejb.Remote;

@Remote
public interface HelloRemote {
	public String echo(String s);
}
