struct operands {
	float operand1;
	float operand2;
};

program ARITHMETIC_PRG {
	version ARTIHMETIC_VER {
		float addition ( operands ) = 1;
		float subtraction ( operands ) = 2;
		float division ( operands ) = 3;
		float multiplication ( operands ) = 4;
		float exponential ( operands ) = 5;
	} = 1;
} = 0x20000001;
