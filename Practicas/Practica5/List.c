#include"List.h"
#include<stdio.h>
#include<stdlib.h>

List * startList(){
    List *aux = (List *) malloc( sizeof( List ) );
    aux->value = -1;
    return aux;
}

List * createNode( int val ){
    List * aux = (List *) malloc( sizeof( List ) );
    aux->value = val;
    aux->next = NULL;
    return aux;
}

void insertNode( List * l , int val ){
    List * aux = createNode( val );
    List * i = l;
    if( l->value == -1 ){
        l->value = val;
    }
    else{
        while ( i->next != NULL)
            i = i->next;
        i->next = aux;
    }
}

int lengthList( List *l ){
    List *aux = l;
    int length = 0;
    while( aux->next != NULL ){
        length++;
        aux = aux->next;
    }
    return ++length;
}

void printValues( List *l ){
    List *aux = l;
    int i,len;
    len = lengthList( aux );
    for( i = 0 ; i < len ; i++ ){
        printf("Valor: %d\n", aux->value );
        aux = aux->next;
    }    
}

List * deleteList( List *l ){
    List *save,*remove;
    remove = l;
    while ( remove->next != NULL ){
        save = remove->next;
        free( remove );
        remove = save;
    }
    l = NULL;
    return NULL;
}