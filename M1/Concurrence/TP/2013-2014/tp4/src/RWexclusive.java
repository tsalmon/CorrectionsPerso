

/**
 * ex2
 * @author salmon
 *
 */
public class RWexclusive extends RWbasic{

	synchronized int read_exclusive(){
		return read();
	}
	
	synchronized void write_exclusive(){
		write();
	}
}
