abstract class Document	{
	def num_enregistrement : Int;
	def titre: String;
}

abstract class Book  extends Document {
	def auteur : String;
	def nombre_pages: Int;
}

abstract class Dictionnary  extends Document {
	def langue : String;
	def  nombre_tomes: Int
}

object Library{
	def main (args :Array[String]) = {
		val t = new Book {
			def auteur = "thomas"
			def nombre_pages = 1
			def num_enregistrement = 1;
			def titre = "une longue histoire"
		}
	}
}