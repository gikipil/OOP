mkdir doc
javadoc -d doc -charset utf-8  -sourcepath ./src/main/java -author -subpackages org.example
javac -d bin -sourcepath ./src/main/java/org/example src/main/java/org/example/heapsort.java
jar cvf heapsort.jar -C bin .
java -classpath heapsort.jar org.example.heapsort