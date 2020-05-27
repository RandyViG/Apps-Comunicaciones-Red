
program TRIGONOMETRIC_PRG {
	version TRIGONOMETRIC_VER {
		float sine ( int operand ) = 1;
		float cosine ( int operand ) = 2;
		float tangent ( int operand ) = 3;
		float cotangent ( int operand ) = 4;
		float secant ( int operand ) = 5;
		float cosecant ( int operand ) = 6;
	} = 1;
} = 0x20000002;

#Archlinux -ltirpc
#Debian -lnsl
LDLIBS += -ltirpc