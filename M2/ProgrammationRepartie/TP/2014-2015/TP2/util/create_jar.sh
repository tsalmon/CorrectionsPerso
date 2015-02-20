#!/bin/sh

cd "TP1ProgRep/src"
mkdir "jar"

javac Exercice1/*.java
javac Exercice2/un/*.java
javac Exercice2/deux/*.java
javac Exercice2/trois/*.java

jar cfe jar/Exercice1.jar Exercice1/Main Exercice1/*.class
jar cfe jar/Exercice2_1.jar Exercice2/un/Main Exercice2/un/*.class
jar cfe jar/Exercice2_2.jar Exercice2/deux/Main Exercice2/deux/*.class
jar cfe jar/Exercice2_3.jar Exercice2/trois/Main Exercice2/trois/*.class

cd -