import java.awt.Color;
import java.util.Random;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author FernandoHE
 */
public class Sopa extends javax.swing.JFrame {
    /**
     * Creates new form Sopa
     */
    String palabrasS[][];
    String palabrasS_Ana[];
    Final fin;
    JLabel letra[][];
    JLabel palabra[];
    String palabras[];//las palabras en un arreglo de string
    int iniciox[];
    int inicioy[];
    boolean gano; 
    boolean direccion[];
    public Sopa(String[][]pals) {
        palabrasS = pals;
        initComponents();
        palabra = new JLabel[]{p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15};
        for (int i = 0; i < 15; i++)
            palabra[i].setText(palabrasS[0][i]);
        this.setLocationRelativeTo(null);
        cargar();
        palabras = new String[15];
        for (int i = 0; i < letra.length; i++) {
            //palabras[i] = palabra[i].getText();//pasa la palabra del arreglo label al al arreglo de string
            palabras[i] = palabrasS[0][i];//pasa la palabra del arreglo label al al arreglo de string
        }
    }//Sopa

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        escritorio = new javax.swing.JDesktopPane();
        Sopa_de_letra = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        p2 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        p4 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        p6 = new javax.swing.JLabel();
        p5 = new javax.swing.JLabel();
        p8 = new javax.swing.JLabel();
        p7 = new javax.swing.JLabel();
        p9 = new javax.swing.JLabel();
        p10 = new javax.swing.JLabel();
        p11 = new javax.swing.JLabel();
        p12 = new javax.swing.JLabel();
        p13 = new javax.swing.JLabel();
        p14 = new javax.swing.JLabel();
        p15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SOPA DE LETRAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATOS");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        escritorio.setForeground(new java.awt.Color(102, 255, 255));

        Sopa_de_letra.setBackground(new java.awt.Color(102, 102, 102));
        Sopa_de_letra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Sopa_de_letra.setLayout(new java.awt.GridLayout(15, 15));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        p2.setBackground(new java.awt.Color(0, 153, 0));
        p2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p2.setForeground(new java.awt.Color(0, 0, 0));
        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p2.setOpaque(true);

        p1.setBackground(new java.awt.Color(0, 153, 0));
        p1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p1.setForeground(new java.awt.Color(0, 0, 0));
        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p1.setOpaque(true);

        p4.setBackground(new java.awt.Color(0, 153, 0));
        p4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p4.setForeground(new java.awt.Color(0, 0, 0));
        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p4.setOpaque(true);

        p3.setBackground(new java.awt.Color(0, 153, 0));
        p3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p3.setForeground(new java.awt.Color(0, 0, 0));
        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p3.setOpaque(true);

        p6.setBackground(new java.awt.Color(0, 153, 0));
        p6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p6.setForeground(new java.awt.Color(0, 0, 0));
        p6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p6.setOpaque(true);

        p5.setBackground(new java.awt.Color(0, 153, 0));
        p5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p5.setForeground(new java.awt.Color(0, 0, 0));
        p5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p5.setOpaque(true);

        p8.setBackground(new java.awt.Color(0, 153, 0));
        p8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p8.setForeground(new java.awt.Color(0, 0, 0));
        p8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p8.setOpaque(true);

        p7.setBackground(new java.awt.Color(0, 153, 0));
        p7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p7.setForeground(new java.awt.Color(0, 0, 0));
        p7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p7.setOpaque(true);

        p9.setBackground(new java.awt.Color(0, 153, 0));
        p9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p9.setForeground(new java.awt.Color(0, 0, 0));
        p9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p9.setOpaque(true);

        p10.setBackground(new java.awt.Color(0, 153, 0));
        p10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p10.setForeground(new java.awt.Color(0, 0, 0));
        p10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p10.setOpaque(true);

        p11.setBackground(new java.awt.Color(0, 153, 0));
        p11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p11.setForeground(new java.awt.Color(0, 0, 0));
        p11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p11.setOpaque(true);

        p12.setBackground(new java.awt.Color(0, 153, 0));
        p12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p12.setForeground(new java.awt.Color(0, 0, 0));
        p12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p12.setOpaque(true);

        p13.setBackground(new java.awt.Color(0, 153, 0));
        p13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p13.setForeground(new java.awt.Color(0, 0, 0));
        p13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p13.setOpaque(true);

        p14.setBackground(new java.awt.Color(0, 153, 0));
        p14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p14.setForeground(new java.awt.Color(0, 0, 0));
        p14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p14.setOpaque(true);

        p15.setBackground(new java.awt.Color(0, 153, 0));
        p15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p15.setForeground(new java.awt.Color(0, 0, 0));
        p15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p15.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p14, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(p13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(p11)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        escritorio.setLayer(Sopa_de_letra, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addComponent(Sopa_de_letra, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Sopa_de_letra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(302, Short.MAX_VALUE))
        );

        getContentPane().add(escritorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 36, 830, 550));

        jMenuBar1.setForeground(new java.awt.Color(102, 102, 102));
        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu1.setForeground(new java.awt.Color(0, 204, 0));
        jMenu1.setText("JUEGO NUEVO");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenu1.setOpaque(false);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        //esta reinicia el juego
        for (int i = 0; i < letra.length; i++) {
            palabra[i].setText(palabras[i]);//asigna a los label de la derecha las palabras
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Sopa_de_letra.remove(letra[i][j]);//quita el panel  Sopa_de_letra
            }
        }
        cargar();//carga el juego
    }//GEN-LAST:event_jMenu1MouseClicked
    public void cargar() {
        gano = false;
        iniciox = new int[15];//crea un arreglo de enteros para guadar las posiciones de las palabras en x
        inicioy = new int[15];//crea un arreglo de enteros para guadar las posiciones de las palabras en y
        direccion = new boolean[15];//crea un arreglo de enteros para guadar las direcion de las palabras ya sea hacia alante o hacia tras
        celdasDeLetras();
        colocarPalabras();
        llenarEspaciosVacios();
    }//Cargar
    public void celdasDeLetras() {
        letra = new JLabel[15][15];//crea la matriz de celdas donde va cada letra
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                letra[i][j] = new JLabel("", javax.swing.SwingConstants.CENTER);//crea la casilla la vacia y con una alineacion centrada
                letra[i][j].setName("");//le pone un nombre a la casilla en este caso no le pone ninguno
                letra[i][j].setBackground(Color.WHITE);//coloca la casilla de color blanco
                letra[i][j].setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // asigna el tipo y el tamaño de la letra
                letra[i][j].setForeground(new java.awt.Color(0, 5, 2));
                letra[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                letra[i][j].setOpaque(true);//esto es para que se pueda ver el color de la casilla o cajonsito donde va la letra
                letra[i][j].setBorder(new javax.swing.border.LineBorder(Color.BLACK, 1));//pone a la casiilla en borde con una linea negra
                letra[i][j].addMouseListener(new java.awt.event.MouseAdapter() {//pone a la casilla a la escucha del mouse para saber cuando se esta dando clic
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        presioneClic(evt);//llama al metodo que debe ejecutarse cuando se da clic
                    }
                });
                Sopa_de_letra.add(letra[i][j]);//coloca la casilla en el panel Sopa_de_letra
            }
        }
    }//celdasDeLetras
    //este metodo se ejecuta cuando se presiona clic en una casilla
    public void presioneClic(java.awt.event.MouseEvent evt) {
        if (!gano) {//verifica si gano el juego
            if (evt.getComponent().getBackground().equals(Color.WHITE))//verifica si la casilla esta de color blanco 
            {
                evt.getComponent().setBackground(new java.awt.Color(0, 153, 0));//si esta de color blanco la pone de color azul
                tachar();
            }else if(evt.getComponent().getName().equals(""))//pregunta si la casilla no tiene una letra de alguna palabra
            {
                evt.getComponent().setBackground(Color.WHITE);//pone la casilla de color blanco
            }
        }
    }//presioneClic
    public void tachar() {
        for (int i = 0; i < 15; i++) {
            if (!palabra[i].getText().substring(0, 1).equals("<")) {
                if (tacharLetra(iniciox[i],inicioy[i],palabra[i].getText().length(),direccion[i]))//pregunta si hay una palabra encontrada
                {
                    palabra[i].setText("<html><body><s>"+palabra[i].getText()+"</s></body></html>");//tacha la palabra
                    break;
                }
            }
        }
        boolean aux = true;//ayuda para saber si ya todas las palabras estan tachadas
        for (int i = 0; i < letra.length; i++)
        {
            if (!palabra[i].getText().substring(0, 1).equals("<")) 
            {
                aux = false;
                break;
            }
        }
        if (aux) {
            if (!(fin instanceof Final)) 
            { //esto comprueba si la ventana no esta en memoria, entonces la instancia
                fin = new Final();   
                gano = true;
            } 
            CentrarVentanaInterna(fin); //usamos el metodo generico para centrar
        }
    }//tachar
    public void CentrarVentanaInterna(JInternalFrame internalFrame) {
    //pasamos como parametro un objeto de tipo JinternalFrame
        int x = (escritorio.getWidth() / 2) - internalFrame.getWidth() / 2;  //caculas las posiciones x y y 
        int y = (escritorio.getHeight() / 2) - internalFrame.getHeight() / 2;
        if (internalFrame.isShowing()) {// comprobamos si la ventana ya esta ejecutada
            internalFrame.setLocation(x, y); // si es asi solo le colocamos en la mitad
        } 
        else
        {
            escritorio.add(internalFrame); // si no es asi le insertamos dentro del desktoppane
            internalFrame.setLocation(x, y); 
            internalFrame.show(); // y mostramos
        }
    }//CentrarVentanaInterna
//Verifica si se puede tachar la letra
    public boolean  tacharLetra(int x,int y,int tamaño,boolean direccion) {
        boolean respuesta = true;
        if (direccion) {
            for (int i = y; i < tamaño+y; i++) {
                if (letra[x][i].getBackground().equals(Color.WHITE)) {
                    respuesta = false;
                    break;
                }
            }
        }else {
            for (int j = y; j > y-tamaño; j--) {
                if (letra[x][j].getBackground().equals(Color.WHITE)) {
                    respuesta = false;
                    break;
                }
            }
        }
        return respuesta;
    }//tacharLetra
    public void colocarPalabras() {
        String palabra[] = {"","","","","","","","","","","","","","",""};//palabra = palabrasS;
        for (int i = 0; i < 15; i++) {
            palabra[i] = palabrasS[0][i];//pasa la palabra del arreglo label al al arreglo de string
        }
        
        Random random = new Random();//estemetodo ayuda a crear numeros aleatorios
        int iniciax = 0;//posicion x donde inicia la palabra
        int iniciay;//posicion y donde inicia la palabra
        int unico[] = NumerosSinRepeticiones(15);//evita que en una fila se generen mas de una vez
        for (int i = 0; i < 15; i++) {
            System.out.println("Soy unico[" + i + "] " + unico[i]);
        }
        iniciox = unico;//Contiene el orden en que las FILAS se llenaran
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i].length()<15) {
                iniciax = unico[i];
                iniciay = (int)(random.nextDouble()*15-1);
                int estrae = 0;//ayuda para estraer las letras de la palabra
                if (iniciay+palabra[i].length()<15) {
                    System.out.println("ENTRE AL if");
                    for (int j = iniciay; j < iniciay+palabra[i].length(); j++) {
                        letra[iniciax][j].setText(palabra[i].substring(estrae, estrae+1));//estrae una letra de la palabra
                        letra[iniciax][j].setName("1");//pone el nombre a la casilla para que se sepa que hay va una letra de una palabra
                        estrae++;//esto es para que se estraiga la siguiente letra de la palabra
                        inicioy[i] = iniciay;
                        direccion[i] = true;
                    }
                } else if (iniciay-palabra[i].length()>0) {
                    System.out.println("ENTRE AL elseeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    for (int j = iniciay; j >iniciay-palabra[i].length() ; j--) {
                        letra[iniciax][j].setText(palabra[i].substring(estrae, estrae+1));
                        letra[iniciax][j].setName("1");
                        estrae++;
                        inicioy[i] = iniciay;
                        direccion[i] = false;
                    }
                }
                else {//Ponerla desde la primer columna
                    iniciay = 0;
                    for (int j = iniciay; j < iniciay+palabra[i].length(); j++) {
                        letra[iniciax][j].setText(palabra[i].substring(estrae, estrae+1));//estrae una letra de la palabra
                        letra[iniciax][j].setName("1");//pone el nombre a la casilla para que se sepa que hay va una letra de una palabra
                        estrae++;//esto es para que se estraiga la siguiente letra de la palabra
                        inicioy[i] = iniciay;
                        direccion[i] = true;
                    }
                }
            }
            System.out.println("Soy iniciox[" + i + "] " + iniciox[i] + "\n" + "Soy inicioy[" + i + "] " + inicioy[i]);
            System.out.println("Soy direccion[" + i + "] " + direccion[i]);
        }
    }//colocarPalabras
    public int[] NumerosSinRepeticiones(int repeticiones) {
        int numeros[] = new int[repeticiones];
        for (int i = 0; i < repeticiones; i++) {
            numeros[i] = -1;
            System.out.println("Soy numeros[" + i + "] " + numeros[i]);
        }
        Random random = new Random();
        boolean aux ;//informa si la fila esta o no repetida
        int numero = 0;
        for (int x = 0; x < repeticiones; x++) 
        {
            aux = true;
            while (aux) {  
                aux = false;
                numero = (int)(random.nextDouble()*16-1);
                for (int j = 0; j < numeros.length; j++) {
                    if (numeros[j] == numero) {
                        aux = true;
                        break;
                    }
                }
            }
            numeros[x] = numero;
            System.out.println("Soy numeros[" + x + "] " + numeros[x]);
        }
        return numeros;
    }//NumerosSinRepeticiones
    public void llenarEspaciosVacios() {
        //este arreglo ayuda a poner las letras del avecedario
        String abc[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (letra[i][j].getText().equals("")) {//si la casilla esta vacia pongale una letra del arreglo abc
                    letra[i][j].setText(abc[(int)(random.nextDouble()*abc.length-1)]);//aqui pone la letra
                }
            }
        }
    }//LlenarEspaciosVacios
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sopa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sopa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sopa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sopa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                //new Sopa().setVisible(true);
            }
        });
    }//main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel Sopa_de_letra;
    javax.swing.JDesktopPane escritorio;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JMenu jMenu1;
    javax.swing.JMenuBar jMenuBar1;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JLabel p1;
    javax.swing.JLabel p10;
    javax.swing.JLabel p11;
    javax.swing.JLabel p12;
    javax.swing.JLabel p13;
    javax.swing.JLabel p14;
    javax.swing.JLabel p15;
    javax.swing.JLabel p2;
    javax.swing.JLabel p3;
    javax.swing.JLabel p4;
    javax.swing.JLabel p5;
    javax.swing.JLabel p6;
    javax.swing.JLabel p7;
    javax.swing.JLabel p8;
    javax.swing.JLabel p9;
    // End of variables declaration//GEN-END:variables
}
