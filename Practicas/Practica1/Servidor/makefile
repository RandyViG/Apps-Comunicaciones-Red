run:	Drive.class Server.class Directory.class DriveFiles
	java Drive

Drive.class:	Drive.java Server.class Directory.class
	javac Drive.java

Server.class:	 Server.java Directory.class
	javac Server.java

Directory.class:  Directory.java
	javac Directory.java

DriveFiles:
	mkdir DriveFiles
clean:
	rm -rf *.class DriveFiles
