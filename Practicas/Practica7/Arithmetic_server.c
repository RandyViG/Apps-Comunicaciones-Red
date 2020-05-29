/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "Arithmetic.h"
#include <stdio.h>
#include "math.h"

float * addition_1_svc(operands *argp, struct svc_req *rqstp){
	static float  result;
	result = argp->operand1 + argp->operand2;
	printf("Result: %f + %f = %f\n",argp->operand1,argp->operand2,result);

	return &result;
}

float * subtraction_1_svc(operands *argp, struct svc_req *rqstp){
	static float  result;
	result = argp->operand1 - argp->operand2;
	printf("Result: %f - %f = %f\n",argp->operand1,argp->operand2,result);

	return &result;
}

float * division_1_svc(operands *argp, struct svc_req *rqstp){
	static float  result;
	result = argp->operand1 / argp->operand2;
	printf("Result: %f / %f = %f\n",argp->operand1,argp->operand2,result);

	return &result;
}

float * multiplication_1_svc(operands *argp, struct svc_req *rqstp){
	static float  result;
	result = argp->operand1 * argp->operand2;
	printf("Result: %f * %f = %f\n",argp->operand1,argp->operand2,result);

	return &result;
}

float * exponential_1_svc(operands *argp, struct svc_req *rqstp){
	static float  result;
	result = powf( argp->operand1 , argp->operand2 );
	printf("Result: %f ^ %f = %f\n",argp->operand1,argp->operand2,result);

	return &result;
}