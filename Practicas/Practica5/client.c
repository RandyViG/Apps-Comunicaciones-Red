#include<pthread.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h> 
#include<malloc.h>
#include<time.h>
#include"List.h"
#include"bucket.h"
#include"Connection.h"

#define MAX_BUCKET 3500
#define MAX_VALUE 999
#define host_port "1101"
#define host_name "127.0.0.1"

void * sendB( void* args );

int main( int argc , char *argv[] ) {
    if( argc != 2 || atoi(argv[1]) > 3500 ){
        printf("Uso: %s NumCubetas\n",argv[0]);
        printf("Nota: Numero maximo de cubetas 3500\n");
        exit(0);
    }
    pthread_t *thread;
    List *numbers;
    struct bucket *buckets;
    int resultado, *array,i,range,NumBuckets,start,j,auxRange;
    NumBuckets = atoi( argv[1] );
    range = (int) (MAX_VALUE / NumBuckets);
    start = 0;
    auxRange = range;

    array = ( int* ) malloc( MAX_BUCKET * sizeof(int) );
    buckets = ( struct bucket *) malloc( NumBuckets * sizeof( struct bucket ) );
    thread = ( pthread_t * ) malloc( NumBuckets * sizeof( pthread_t ) );

    srand(time(NULL));
    for( i = 0 ; i < MAX_BUCKET ; i++ )
        array[i] = rand() % MAX_VALUE;

    for( i = 0; i < NumBuckets ; i++){
        numbers = startList();
        for( j = 0 ; j < MAX_BUCKET ; j++)
            if( array[j] >= start && array[j] <= range )
                insertNode( numbers , array[j] );
        buckets[i].len = lengthList( numbers );
        List *aux;
        aux = numbers;
        for( j = 0 ; j < buckets[i].len ; j++ ){
            buckets[i].elements[j] = aux->value;
            aux = aux->next;
        }
        numbers = deleteList(numbers);
        start = ++range;
        range += auxRange;
    }
    free(array);

    for( i=0 ; i < NumBuckets ; i++ )
        pthread_create( &thread[i] , NULL, &sendB , &buckets[i] );

    for( i=0 ; i < NumBuckets ; i++ )
        pthread_join( thread[i], NULL );
    
    free(thread);

    for( i=0; i < NumBuckets ; i++)
        for( j=1; j <= buckets[i].len ; j++ )
            printf("%d\n", buckets[i].elements[j]);

    free(buckets);
    return 0;
}

void * sendB( void* args ){
    int i,cd,j;
    struct bucket *aux = ( struct bucket *)args;
    aux->hostName = (char *) malloc( sizeof(host_name) );
    memcpy( aux->hostName, host_name , sizeof(host_name) );
    aux->ptoServer = (char *) malloc( sizeof(host_port) );
    memcpy( aux->ptoServer, host_port , sizeof(host_port) );
    createSocket( &cd , aux );
    sendBucket( &cd , aux );
    memcpy( args , receiveBucket( &cd , sizeof(*aux) ) , sizeof(*aux) );
}