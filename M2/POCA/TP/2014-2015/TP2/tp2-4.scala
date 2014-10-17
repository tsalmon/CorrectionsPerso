abstract class Document	(num_enregistrement : Int, titre: String){
}

abstract class Book (auteur : String, nombre_pagee: Int) extends Document {}

abstract class Dictionnary (langue : String,  nombre_tomes: Int) extends Document {}

object Library{
	def main (args :Array[String]) = {
		//val t = new Book(0, "doc"){}
	}
}