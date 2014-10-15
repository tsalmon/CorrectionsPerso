
object HelloWorld{

	def helloFor(args: Array[String]): Unit = {
		var str = "Hello World";
		for( i <- 0 to args.length - 1) {
			str = str + " " + args(i);
		}
		println(str);		
	}

	def helloWhile(args: Array[String]): Unit = {
		var str = "Hello World";
		var i = 0;
		while( i < args.length - 1) {
			str = str + " " + args(i);
			i = i+1;
		}
		println(str);		
	}

	def helloRecFunc(args:Array[String], length:Int): String ={
		if(length == 0){
			return "Hello World " + args(length);
		}
		return helloRecFunc(args, length-1) + " " + args(length);
	}

	def main(args: Array[String]){
		//helloFor(args);
		//helloWhile(args);
		println(helloRecFunc(args, args.length-1));
	}
}


