abstract class Document	(_titre: String, _num_enregistrement : Int) {
	val num_enregistrement : Int = _num_enregistrement;
	val titre: String = _titre;
}

class Book (_auteur : String, _nombre_pages: Int, _titre: String, _num_enregistrement : Int) extends Document (_titre: String, _num_enregistrement : Int){
	val auteur = _auteur;
	val nombre_pages = _nombre_pages;
}

class Dictionnary(_langue : String, _nombre_tomes : Int, _titre: String, _num_enregistrement : Int)  extends Document (_titre: String, _num_enregistrement : Int) {
	val langue : String = _langue;
	val  nombre_tomes: Int = _nombre_tomes;
}

object Library{
	def main (args :Array[String]) = {
		val t = new Book(_auteur = "Thomas", _nombre_pages = 13, _num_enregistrement =  1000, _titre = "rush zergling");
		println(t.auteur + " " + t.nombre_pages + " " + t.num_enregistrement + " " + t.titre);
	}
}
