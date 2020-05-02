#ifndef __CONNECTION_H__
#define __CONNECTION_H__

#define _GNU_SOURCE
#include <string.h>
#include <stdlib.h> //exit()
#include <errno.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netdb.h> //getaddrinfo() getnameinfo() freeaddrinfo()
#include <sys/socket.h>
#include <unistd.h> //read
#include"bucket.h"

void createSocket( int *cd , struct bucket * b );
void sendBucket( int *cd , struct bucket * b );
struct bucket * receiveBucket( int *cd , int len );

#endif