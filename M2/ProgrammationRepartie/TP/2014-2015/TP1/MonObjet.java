public class MonObjet {
	ThreadLocal<Integer> last;
	int value;
	int value2;

	public MonObjet(int init){
		value = init;
		value2 = init;
		last = new ThreadLocal<Integer>(){
			protected Integer initialValue(){
				return 0;
			}
		};
	}
	public int read(){
		return value;
	}

	public void add(){
		last.set(new Integer(last.get()+1));
		value = value + 1;
		value2 = value2 + 1;
	}
}