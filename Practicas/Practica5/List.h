#ifndef __LIST_H__
#define __LIST_H__

typedef struct list{
    int value;
    struct list * next;
} List;

List * startList();
List * createNode( int val );
void insertNode( List * l , int val );
int lengthList( List *l);
void printValues( List *l );
List * deleteList( List *l );

#endif