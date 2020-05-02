run:	client.o List.o Connection.o server.c
	gcc -pthread client.o List.o Connection.o -o client
	gcc -pthread server.c -o server
	./server
client.o:	client.c
	gcc -c client.c
List.o:	List.c
	gcc -c List.c
Connection.o:	Connection.c
	gcc -c Connection.c
clean:
	rm -f *.o client server
