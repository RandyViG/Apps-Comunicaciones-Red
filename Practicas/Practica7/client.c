#include "Arithmetic.h"
#include "Trigonometric.h"
#include "TADPilaDin.h"
#include <stdlib.h>
#include <string.h>

void infix( char *notation );
void postfix( char *notation , char *host , char *host2 );
void prefix( char *notation , char *host , char *host2 );
void infix_to_postfix( char *notation );
void arithmetic_prg_1(char *host);
int operator_type( elemento * operator );
int parentheses( char *notation );
void add_operator( elemento e , pila * S , elemento *post , int * j , int type );
float trigonometric( char * operator , elemento operand , char *host);
void operators( void );

int main (int argc, char *argv[]){
	char *host , notation[100] , *host2;
	int type;
	if (argc < 3) {
		printf ("usage: %s server_host_Arithmetic server_host_Trignometric\n", argv[0]);
		exit (1);
	}
	operators();
	printf("Ingresa la expresión a evaluar:\n");
	scanf("%s",notation);
	printf("Ingresa el tipo de notacion (Postfija = 1 , Prefija = 2 , Infija = 3):\n");
	scanf("%d",&type);
	host = argv[1];
	host2 = argv[2];
	switch( type ){
		case 1:
			postfix( notation , host , host2);
			break;
		case 2:
			prefix( notation , host , host2);
			break;
		case 3:
			infix_to_postfix( notation );
			printf("Conversion correcta: %s\n",notation);
			postfix( notation , host , host2 );
			break;
		default:
			printf("Tipo de notación invalido!\n");
			break;
	}
	exit (0);
}

void operators( void ){
	printf("\t\t     Operadores           \n");
	printf("\t  suma: +               seno: sin        \n");
	printf("\t  resta: -              coseno: cos      \n");
	printf("\t  Multiplicación: *     tangente: tan    \n");
	printf("\t  División: /           cotangente: ctg   \n");
	printf("\t  Exponencial: ^        secante: sec\n");
	printf("\t  Logaritmo: log        cosecante: csc\n\n");
}

void infix_to_postfix( char *notation ){
	int index = 0 , size, i, type;
	elemento aux[100], post[100];
	size = strlen( notation );
	if( parentheses(notation) == 0 ){
		printf("Error! \nEl numero de parentesis no es correcto\n");
		exit(1);
	}
	pila stack;
	Initialize(&stack);

	for( i = 0; i < size ; i++ )
		aux[i].c = notation[i];
	for( i = 0 ; i < size ; i++){
		type = operator_type( &aux[i] );
		if( type == 6 ){
			Push( &stack , aux[i+2] );
			Push( &stack , aux[i+1] );
			Push( &stack , aux[i] );
			i+=2;
		} 
		else if( type < 5 )
			add_operator( aux[i] , &stack , post , &index , type );
		else
			post[index++] = aux[i];
	}
	while( Empty(&stack) == 0 )
		post[index++] = Pop( &stack );
	post[index].c = '\0';
	memset( notation , 0 , 100 );
	for( i=0 ; post[i].c != '\0'; i++)
		notation[i] = post[i].c;
	notation[i] = '\0';
}

void postfix( char *notation , char * host , char * host2 ){
	CLIENT *clnt;
	float *result;
	operands arg;
	char trig[4];

	#ifndef	DEBUG
		clnt = clnt_create (host, ARITHMETIC_PRG, ARTIHMETIC_VER, "udp");
		if (clnt == NULL) {
			clnt_pcreateerror (host);
			exit (1);
		}
	#endif

	pila stack;
	elemento aux,value,operand1,operand2;
	int i, type;
	Initialize( &stack );
	for( i = 0 ; i < strlen( notation ) ; i++ ){
		aux.c = notation[i];
		type = operator_type( &aux );
		if( type == 6 ){
			sprintf( trig,"%c%c%c\0",notation[i],notation[i+1],notation[i+2] );
			value.f = trigonometric( trig , Pop(&stack) , host2 );
			i += 2;
			Push( &stack , value );
		}
		else if( type == 5 ){
			value.f = (float) atoi( &aux.c );
			Push( &stack , value );
		}
		else{
			operand2 = Pop( &stack );
			operand1 = Pop( &stack );
			arg.operand1 = operand1.f;
			arg.operand2 = operand2.f;
			switch( aux.c ){
				case '+':
					result = addition_1(&arg, clnt);
					if (result == (float *) NULL) 
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '-':
					result = subtraction_1(&arg, clnt);
					if (result == (float *) NULL) 
					clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '*':
					result = multiplication_1(&arg, clnt);
					if (result == (float *) NULL) 
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '/':
					result = division_1(&arg, clnt);
					if (result == (float *) NULL)
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '^':
					result = exponential_1(&arg, clnt);
					if (result == (float *) NULL)
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
			}
			Push(&stack,value);
		}
	}
	if( Size(&stack) > 1 ){
		printf("Error!\nLa expresión tenia un error\n");
		exit(-1);
	}

	value = Pop( &stack );
	printf("El resultado de: %s = %f\n",notation,value.f);
	
	#ifndef	DEBUG
		clnt_destroy (clnt);
	#endif
}

void prefix( char *notation , char * host , char *host2 ){

	CLIENT *clnt;
	float *result;
	operands arg;
	char trig[4];

	#ifndef	DEBUG
		clnt = clnt_create (host, ARITHMETIC_PRG, ARTIHMETIC_VER, "udp");
		if (clnt == NULL) {
			clnt_pcreateerror (host);
			exit (1);
		}
	#endif

	pila stack;
	elemento aux,value,operand1,operand2;
	int i, type;
	Initialize( &stack );
	for( i = strlen( notation ) - 1  ; i >= 0  ; i-- ){
		aux.c = notation[i];
		type = operator_type( &aux );
		if( type == 6 ){
			sprintf( trig,"%c%c%c\0", notation[i-2] , notation[i-1] , notation[i] );
			value.f = trigonometric( trig , Pop(&stack) , host2 );
			i -= 2;
			Push( &stack , value );
		}
		else if( type == 5 ){
			value.f = (float) atoi( &aux.c );
			Push( &stack , value );
		}
		else{
			operand2 = Pop( &stack );
			operand1 = Pop( &stack );
			arg.operand1 = operand1.f;
			arg.operand2 = operand2.f;
			switch( aux.c ){
				case '+':
					result = addition_1(&arg, clnt);
					if (result == (float *) NULL) 
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '-':
					result = subtraction_1(&arg, clnt);
					if (result == (float *) NULL) 
					clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '*':
					result = multiplication_1(&arg, clnt);
					if (result == (float *) NULL) 
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '/':
					result = division_1(&arg, clnt);
					if (result == (float *) NULL)
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
				case '^':
					result = exponential_1(&arg, clnt);
					if (result == (float *) NULL)
						clnt_perror (clnt, "call failed");
					value.f = *result;
				break;
			}
			Push(&stack,value);
		}
	}
	if( Size(&stack) > 1 ){
		printf("Error!\nLa expresión tenia un error\n");
		exit(-1);
	}
	value = Pop( &stack );
	printf("El resultado de: %s = %f\n",notation,value.f);
}

int operator_type( elemento *operator ){

	if( operator->c > 'a' && operator->c < 'z' ) // trigonometric operator
		return 6;
	if( operator->c == '^' ) 
		return 4;
	if( operator->c == '*' || operator->c == '/' ) 
		return 3;
	if( operator->c == '-' || operator->c == '+' ) 
		return 2;
	if( operator->c == ')' ) 
		return 1;
	if( operator->c == '(' )
		return 0;
	return 5; // Is operand
}

int parentheses( char *notation ){
	int i,j,size;
	pila stack;
	elemento aux;
	size = strlen(notation);
	Initialize(&stack);

	for( i = 0; i < size ; i++){
		if( notation[i] == '(' ){
			aux.c = '(';
			Push( &stack , aux );
		}
		else if( notation[i] == ')' ){
			if( Empty(&stack) )
				return 0;
			aux = Pop( &stack );
		}
	}

	if( !Empty(&stack) )
		return 0;

	Destroy(&stack);	
	return 1;
}

void add_operator( elemento e , pila * S , elemento *post , int * j , int type ){
	elemento aux;
	int aux_type;
	switch( type ){
		case 0:
			Push( S, e );
			break;
		case 1:
			aux = Top( S );
			while( operator_type( &aux ) != 0 ){
				post[ (*j)++ ] = Pop( S );
				aux = Top( S );
			}
			Pop( S );
			break;
		default:
			if( Empty(S) )
				Push( S , e );
			else{
				aux = Top( S );
				aux_type = operator_type( &aux );
				if( type > aux_type )
					Push( S , e );
				else{
					//aux = Top(S);
					while( type  <= operator_type( &aux ) ){
						post[ (*j)++ ] = Pop( S );
						if( Empty( S ) )
							break;
						aux = Top(S);
					}
					Push( S , e );
				}
			}
			break;
	}
}

float trigonometric( char * operator , elemento operand , char *host){
	CLIENT *clnt;
	#ifndef	DEBUG
		clnt = clnt_create (host, TRIGONOMETRIC_PRG, TRIGONOMETRIC_VER, "udp");
		if (clnt == NULL) {
			clnt_pcreateerror (host);
			exit (1);
		}
	#endif

	float arg = operand.f;
	float *result,aux;

	if( !strncmp(operator,"sin",3)  ){
		result = sine_1(&arg, clnt);
		if( result == (float *) NULL ) {
			clnt_perror (clnt, "call failed");
		}
	}
	else if( !strncmp(operator,"cos",3) ){
		result = cosine_1(&arg, clnt);
		if( result == (float *) NULL ) {
			clnt_perror (clnt, "call failed");
		}
	}
	else if( !strncmp(operator,"tan",3) ){
		result = tangent_1(&arg, clnt);
		if( result == (float *) NULL ) {
			clnt_perror (clnt, "call failed");
		}
	}
	else if( !strncmp(operator,"cot",3) ){
		result = cotangent_1(&arg, clnt);
		if( result == (float *) NULL ) {
			clnt_perror (clnt, "call failed");
		}

	}
	else if( !strncmp(operator,"sec",3) ){
		result = secant_1(&arg, clnt);
		if( result == (float *) NULL ) {
			clnt_perror (clnt, "call failed");
		}
	}
	else if( !strncmp(operator,"csc",3)){
		result = cosecant_1(&arg, clnt);
		if (result == (float *) NULL) {
			clnt_perror (clnt, "call failed");
		}
	}
	else if( !strncmp(operator,"log",3) ){
		result = logarithm_1(&arg, clnt);
		if (result == (float *) NULL) {
			clnt_perror (clnt, "call failed");
		}
	}
	#ifndef	DEBUG
		clnt_destroy (clnt);
	#endif

	return *result;
}
