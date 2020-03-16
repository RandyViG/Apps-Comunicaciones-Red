/**
 *
 * @author FernandoHE
 */
public class aplisopaletras {
    
    public static void muestraSopa(char[][] mSopa, int mABandera [][]) {
	for( int i = 0; i < 15; i++){
            System.out.print(i + "|\t");
            for( int j = 0; j < 15; j++){
    		System.out.print(mSopa[i][j] + "\t");
		//System.out.print(mSopa[i][j] + "" + mABandera[i][j] + "\t");
            }
            System.out.print("\n\n");
	}
    }//muestraSopa
    public static void inicializaMatri(char[][] mSopa, int mABandera [][], int cooF [][]) {
	char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
						'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	for( int i = 0; i < 15; i++){
            for( int j = 0; j < 15; j++){
                mSopa[i][j] = alfabeto[ (int) (Math.random() * 26)];
                mABandera[i][j] = 0;
            }
            cooF[i][0] = 0;cooF[i][1] = 0;
	}
    }//inicializaMatri
    public static void muestra_cooF(int cooF [][]) {
	for( int i = 0; i < 15; i++){
            for( int j = 0; j < 2; j++){
                System.out.print(cooF[i][j] + "\t");
            }
            System.out.print("\n\n");
	}
    }//muestra_cooF
    private static int caminoLimpio(int mABandera [][], int a, int b, int tam, int op) {
	if (op == 0) {//Horizontal
            if (tam <= (15 - b)) {
                for (int i = 0; i < tam; i++) {
                    if (mABandera[a][b + i] == 1) {
                        return 2;
                    }
                }
                return 0;
            }
            else if (tam > (15 - b)) {
                return 2;
            }
            else {
                for (int i = tam; i > 0; i--) {
                    if (mABandera[a][b - i] == 1){
                        return 2;
                    }
                }
                return 0;
            }	
	} else if (op == 1) {//Vertical
            if (tam <= (15 - a)) {
                for (int i = 0; i < tam; i++) {
                    if (mABandera[a + i][b] == 1) {
                        return 2;
                    }
                }
                return 1;
            }
            else if (tam > (15 - a)) {
                return 2;
            }
            else {
                for (int i = tam; i > 0; i--) {
                    if (mABandera[a - i][b] == 1) {
                        return 2;
                    }
                }
                return 1;
            }
	}
	return 3;
    }//caminoLimpio
	
    public static void main(String[] args) {
	String[] palabras = {"maestro", "alumno", "lapiz", "lapicero", "cuaderno", "crayon", "computadora", 
			"goma", "sacapuntas", "regla", "compas", "libro", "colores", "tijeras", "pizarron"};
	String pAux, sopaCompleta = "matriz:";
	char[][] mSopa = new char[15][15];
	int a = 0, b = 0, op = -2, c = 0, climpio = -2;
	int mABandera [][] = new int[15][15], cooF [][] = new int[15][2];
			
    //Llena matriz con puras letras y 0's
	inicializaMatri(mSopa, mABandera, cooF);
    //Muestra sopa sin palabras
	//muestraSopa(mSopa, mABandera);
    //Meter palabras en la sopa
	for( int k = 0; k < 15; k++){
            pAux = palabras[k];
            //System.out.println(pAux);
	//Genera op
            op = (int) (Math.random() * 2);
            while (climpio != -1) {
		a = (int) (Math.random() * 15);
		b = (int) (Math.random() * 15);
		//System.out.println("Soy a = " + a + "\tSoy b = " + b);
		climpio = caminoLimpio(mABandera, a, b, pAux.length(), op);
		if (climpio == 0 || climpio == 1) {
                    op = climpio;
                    climpio = -1;
                }
            }
            climpio = -2;
	
	//Metiendo palabras a la SOPA
            while (op != -1) {
                if (op == 0) {//Horizontal
                    //System.out.println("HORIZONTAL");
                    if (pAux.length() <= (15 - b)) {//La Palabra se debera leer de derecha a izquierda
                        for (int i = 0; i < pAux.length(); i++) {
                            mSopa[a][b + i] = pAux.charAt(i);
                            mABandera[a][b + i] = 1;
			}
                    }
                    else {//La Palabra se debera leer de izquierda a derecha
                        for (int i = pAux.length(); i > 0; i--) {
                            mSopa[a][b - i] = pAux.charAt(i-1);
                            mABandera[a][b - i] = 1;
			}
                    }
                    op = -1;//Termine de meter
		}
		if (op == 1) {//Vertical
                    //System.out.println("VERTICAL");
                    if (pAux.length() <= (15 - a)) {//La Palabra se debera leer de arriba para abajo
                        for (int i = 0; i < pAux.length(); i++) {
                            mSopa[a + i][b] = pAux.charAt(i);
                            mABandera[a + i][b] = 1;
			}
                    }
                    else {//La Palabra se debera leer de abajo para arriba
                        for (int i = pAux.length(); i > 0; i--) {
                            mSopa[a - i][b] = pAux.charAt(i-1);
                            mABandera[a - i][b] = 1;
			}
                    }
                    op = -1;//Termine
		}
            }
            op = -2;
            cooF[c][0] = a;
            cooF[c][1] = b;
            c++;
        }
    //Muestra sopa con palabras
        muestraSopa(mSopa, mABandera);
    //Muestra coordenadas
	muestra_cooF(cooF);

        StringBuffer cadena = new StringBuffer();
        for (int x=0; x < 15;x++){
            for (int y = 0; y < 15; y++) {
                cadena =cadena.append(mSopa[x][y]);
            }
        }
        sopaCompleta = sopaCompleta + cadena;
        System.out.println(cadena);
        System.out.println(sopaCompleta);
	//new Sopa(String[]pals).setVisible(true);
    }//main
}
