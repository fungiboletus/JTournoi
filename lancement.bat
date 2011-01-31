mkdir bin

javac -d bin -classpath bin;jdom.jar;opencsv-2.2.jar;sqlite-jdbc-3.7.2.jar src/polytech/ihm/*.java src/polytech/jtournoi/*.java src/polytech/personnes/*.java src/polytech/stock/*.java src/polytech/tools/*.java src/polytech/stock/SQL/*.java src/polytech/stock/XML/*.java src/polytech/exception/*.java

java -cp bin;jdom.jar;opencsv-2.2.jar;sqlite-jdbc-3.7.2.jar polytech/ihm/Main
