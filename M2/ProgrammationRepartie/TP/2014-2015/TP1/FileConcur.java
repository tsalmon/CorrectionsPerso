public class FileConcur {
	final static int QSIZE = 20000;
	int head = 0;
	int tail = 0;
	int [] cell = new int[QSIZE];
	public void enq(int o){
		cell[(tail)++%QSIZE]=o;
	}
	public int deq(){
		return cell[(head++)%QSIZE];
	}
}
