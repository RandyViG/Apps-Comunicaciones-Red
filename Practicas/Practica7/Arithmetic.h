/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _ARITHMETIC_H_RPCGEN
#define _ARITHMETIC_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct operands {
	float operand1;
	float operand2;
};
typedef struct operands operands;

#define ARITHMETIC_PRG 0x20000001
#define ARTIHMETIC_VER 1

#if defined(__STDC__) || defined(__cplusplus)
#define addition 1
extern  float * addition_1(operands *, CLIENT *);
extern  float * addition_1_svc(operands *, struct svc_req *);
#define subtraction 2
extern  float * subtraction_1(operands *, CLIENT *);
extern  float * subtraction_1_svc(operands *, struct svc_req *);
#define division 3
extern  float * division_1(operands *, CLIENT *);
extern  float * division_1_svc(operands *, struct svc_req *);
#define multiplication 4
extern  float * multiplication_1(operands *, CLIENT *);
extern  float * multiplication_1_svc(operands *, struct svc_req *);
#define exponential 5
extern  float * exponential_1(operands *, CLIENT *);
extern  float * exponential_1_svc(operands *, struct svc_req *);
extern int arithmetic_prg_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define addition 1
extern  float * addition_1();
extern  float * addition_1_svc();
#define subtraction 2
extern  float * subtraction_1();
extern  float * subtraction_1_svc();
#define division 3
extern  float * division_1();
extern  float * division_1_svc();
#define multiplication 4
extern  float * multiplication_1();
extern  float * multiplication_1_svc();
#define exponential 5
extern  float * exponential_1();
extern  float * exponential_1_svc();
extern int arithmetic_prg_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_operands (XDR *, operands*);

#else /* K&R C */
extern bool_t xdr_operands ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_ARITHMETIC_H_RPCGEN */
