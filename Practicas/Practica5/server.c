#define _GNU_SOURCE
#include<netdb.h>
#include<string.h>
#include<stdlib.h>
#include<stdio.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<unistd.h>
#include<pthread.h>
#include"bucket.h"

#define host_port "1101"

void* SocketHandler( void* );
void order( int * elements , int tam );
void quicksort( int *lista , int limite_izq , int limite_der );

int main( int argc , char*argv[] ){
	char hbuf[NI_MAXHOST], sbuf[NI_MAXSERV];
	int sd,err,rv,n,v=1,op=0;
	socklen_t ctam = 0;
	int *cd;
	pthread_t thread_id = 0;
	struct addrinfo hints, *servinfo, *p;
	struct sockaddr_storage their_addr; // connector's address 
 	ctam = sizeof(their_addr);
	memset(&hints, 0, sizeof (hints));
	hints.ai_family = AF_INET6;    /* Allow IPv4 or IPv6  familia de dir*/
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_flags = AI_PASSIVE; // use my IP
	hints.ai_protocol = 0;       /* Any protocol */
	hints.ai_canonname = NULL;
	hints.ai_addr = NULL;
	hints.ai_next = NULL;
	if( ( rv = getaddrinfo(NULL, host_port, &hints, &servinfo) ) != 0 ) {
	    fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(rv));
	    return 1;
	}
	for( p = servinfo; p != NULL; p = p->ai_next ) {
        if ((sd = socket(p->ai_family, p->ai_socktype,p->ai_protocol)) == -1) {
           perror("Error en funcion socket()\n");
           continue;
        }
        if (setsockopt(sd, SOL_SOCKET, SO_REUSEADDR, &v,sizeof(int)) == -1) {
           perror("error en opcion SO_REUSEADDR \n");
           exit(1);
        }
	    if (setsockopt(sd, IPPROTO_IPV6, IPV6_V6ONLY, (void *)&op, sizeof(op)) == -1) {
            perror("setsockopt   no soporta IPv6");
            exit(1);
        }
        if (bind(sd, p->ai_addr, p->ai_addrlen) == -1) {
            close(sd);
            perror("El puerto ya esta en uso \n");
            continue;
        }
        break;
    }
	freeaddrinfo(servinfo);
	if (p == NULL)  {
        fprintf(stderr, "servidor: error en bind\n");
        exit(1);
    }
	listen(sd,5);
	printf("Servidor listo.. Esperando clientes \n");
	
	while(1){
		printf("\nEsperando un cliente..\n");
		cd = (int*)malloc(sizeof(int));
		if((*cd = accept( sd, (struct sockaddr*)&their_addr, &ctam))!= -1){
		    if (getnameinfo((struct sockaddr *)&their_addr, sizeof(their_addr), hbuf, sizeof(hbuf), sbuf,sizeof(sbuf), NI_NUMERICHOST | NI_NUMERICSERV) == 0)
	            printf("cliente conectado desde %s:%s\n", hbuf,sbuf);
		    pthread_create( &thread_id,0,&SocketHandler, (void*)cd );
		    pthread_detach( thread_id );
		}
        else{
		    perror("Error en funcion accept()\n");
		    free(cd);
		    continue;
		}
	}
	free(cd);
	return 0;
}

void* SocketHandler( void* lp ){
    int *cd = (int*) lp , *orderElements,i;
    struct bucket b;
	if( recv( *cd , &b , sizeof( b ) , 0 ) == -1 ){
		perror("Error en funcion recv()\n");
		free(cd);
		exit(1);
	}

    order( b.elements , b.len );
    
	if( send( *cd , &b , sizeof(b) , 0) == -1 ){
	    perror("Error en la funcion send()\n");
	    free(cd);
	    exit(1);
	}
}

void order( int * elements , int tam ){
    quicksort( elements , 0 , tam );
}

void quicksort( int *lista , int limite_izq , int limite_der){
	int izq,der,temporal,pivote;
	izq = limite_izq;
	der = limite_der;
	pivote = lista[(izq+der)/2];
	do{
	    while(lista[izq]<pivote && izq<limite_der)
            izq++;
	    while(pivote<lista[der] && der > limite_izq)
            der--;
	    if( izq <=der ){
	        temporal= lista[izq];
	        lista[izq]=lista[der];
	        lista[der]=temporal;
	        izq++;
	        der--;
	    }
	}while(izq<=der);
	if(limite_izq<der)
        quicksort(lista,limite_izq,der);
	if(limite_der>izq)
        quicksort(lista,izq,limite_der);
}