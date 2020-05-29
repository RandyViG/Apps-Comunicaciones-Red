/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _TRIGONOMETRIC_H_RPCGEN
#define _TRIGONOMETRIC_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


#define TRIGONOMETRIC_PRG 0x20000002
#define TRIGONOMETRIC_VER 1

#if defined(__STDC__) || defined(__cplusplus)
#define sine 1
extern  float * sine_1(float *, CLIENT *);
extern  float * sine_1_svc(float *, struct svc_req *);
#define cosine 2
extern  float * cosine_1(float *, CLIENT *);
extern  float * cosine_1_svc(float *, struct svc_req *);
#define tangent 3
extern  float * tangent_1(float *, CLIENT *);
extern  float * tangent_1_svc(float *, struct svc_req *);
#define cotangent 4
extern  float * cotangent_1(float *, CLIENT *);
extern  float * cotangent_1_svc(float *, struct svc_req *);
#define secant 5
extern  float * secant_1(float *, CLIENT *);
extern  float * secant_1_svc(float *, struct svc_req *);
#define cosecant 6
extern  float * cosecant_1(float *, CLIENT *);
extern  float * cosecant_1_svc(float *, struct svc_req *);
#define logarithm 7
extern  float * logarithm_1(float *, CLIENT *);
extern  float * logarithm_1_svc(float *, struct svc_req *);
extern int trigonometric_prg_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define sine 1
extern  float * sine_1();
extern  float * sine_1_svc();
#define cosine 2
extern  float * cosine_1();
extern  float * cosine_1_svc();
#define tangent 3
extern  float * tangent_1();
extern  float * tangent_1_svc();
#define cotangent 4
extern  float * cotangent_1();
extern  float * cotangent_1_svc();
#define secant 5
extern  float * secant_1();
extern  float * secant_1_svc();
#define cosecant 6
extern  float * cosecant_1();
extern  float * cosecant_1_svc();
#define logarithm 7
extern  float * logarithm_1();
extern  float * logarithm_1_svc();
extern int trigonometric_prg_1_freeresult ();
#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_TRIGONOMETRIC_H_RPCGEN */