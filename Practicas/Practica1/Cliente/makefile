run:	UserInterface.class Client.class Directory.class
	java UserInterface
UserInterface.class:	UserInterface.java Client.class Directory.class
	javac UserInterface.java
Client.class:	Client.java Directory.class
	javac Client.java

Directory.class:  Directory.java
	javac Directory.java

clean:
	rm -f *.class
