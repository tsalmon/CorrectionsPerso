abstract class HashTable {
	type K
	type V

	def hash (k :K) :Int
	def add (k:K, v:V)
	def find (k :K) :Option[V]
}

abstract class SimpleHashTable extends HashTable {
	type Bucket = List[(K, V)]
	type Table = Array[Bucket]

	def alloc_data (size :Int) :Table = { 
		var table:Array[Bucket] = new Array[Bucket](size);
		return table;
	}
	
	def initial_size :Int
	var data = alloc_data (initial_size)
	var count = 0
	
	def add_in (data :Table, k :K, v :V) { 
		val hash = k.hashCode() % data.length;
		val l:Bucket = List((k, v));
		data(hash) = l;
	}

	def rehash () {
		var new_data  = alloc_data( (data.length * 2) + 1 );
		for( i <- 0 to data.length - 1) {
			for( j <- 0 to data(i).length - 1) {
				add_in(new_data, data(i)(j)._1, data(i)(j)._2);
			}
		}
		data = new_data;
	}

	def add (k :K, v :V) { 
		val hash = k.hashCode() % data.length;
		val l:Bucket = List((k, v));
		if(data(hash) == null){
			data(hash) = l;
		} else {
			data(hash) :::= l;
		}
		if(count > data.length/2){
			rehash();
		}

	}

	def find (k :K) :Option[V] = { 
		val hash = k.hashCode() % initial_size;
		if(data(hash) != null && data(hash)(0)._1 == k){
			return Option(data(hash)(0)._2)
		}
		return null;
	}
}

object Test{
	def main (args :Array[String]) = {
		val t = new SimpleHashTable {
			type K = String ;
			type V = String ;
			def hash (k :K) = k.hashCode ()
			def initial_size = 31
		}
	
		t.add ("Luke", "Darth") ;
		t.add ("Leila", "Darth") ;
		t.add ("George W", "George") ;
		println (t.find ("Abama")) ;
		println (t.find ("Leila")) ;
	}
}
