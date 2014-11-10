

class Formule
case class Negation(n: Formule) extends Formule
case class Or(o1: Formule, o2: Formule) extends Formule
case class And(a1: Formule, a2: Formule) extends Formule
case class Var(variable: String) extends Formule
case class Const(c: Boolean) extends Formule

object Application {
  type Environment = String => Boolean


  def calcul(f: Formule, env: Environment) : Boolean =
    f match {
      case Negation(n) => !(calcul(n, env))
      case Or(o1, o2) => calcul(o1, env) || calcul(o2, env)
      case And(a1, a2) => calcul(a1, env) && calcul(a2, env)
      case Var(v) => env(v)
      case Const(c) => c
    }

  def main(args:Array[String]){
    val f: Formule = Or(Var("x"), Var("y"))
    val env: Environment = {
      case "x" => false
      case "y" => false
    }
    println(calcul(f, env))
  }
}
