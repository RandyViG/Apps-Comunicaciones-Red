#include"Connection.h"
#include<stdio.h>

void createSocket( int *cd , struct bucket * b ){
    struct addrinfo hints, *servinfo, *p;
    int rv;
    memset(&hints, 0, sizeof hints);
	hints.ai_family = AF_UNSPEC;    /* Allow IPv4 or IPv6  familia de dir*/
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = 0;
	if (( rv = getaddrinfo( b->hostName , b->ptoServer , &hints, &servinfo) ) != 0) {
	   	fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(rv));
        exit(1);
	}

	for( p = servinfo; p != NULL; p = p->ai_next ) {
	    if ( ( *cd = socket(p->ai_family, p->ai_socktype,p->ai_protocol) ) == -1 ) {
        	perror("client: socket");
        	continue;
    	}
    	if ( connect(*cd, p->ai_addr, p->ai_addrlen) == -1 ) {
    	    close(*cd);
    	    perror("Error en la funcion connect() \n");
    	    continue;
    	}
    	break;
	}
	
	if (p == NULL) {
        perror("Error al conectar con el servidor\n");
        exit(2);
    }
	freeaddrinfo(servinfo);
}

void sendBucket( int *cd , struct bucket * b ){
    if( send( *cd, (char *) b , sizeof(*b) , 0 ) == -1 ){
		fprintf(stderr, "Error en la funcion send(): %d\n", errno);
		exit(1);
	}
}

struct bucket * receiveBucket( int *cd , int len ){
    struct bucket aux,*r;
    if( recv( *cd, (char*)&aux, len , 0 ) == -1 ){
		fprintf(stderr, "Error enla funcion recv() %d\n", errno);
		exit(1);
	}
    r = &aux;
	close( *cd );
    return r;
}

