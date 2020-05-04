
public class GestionProductosServidor {
    public GestionProductosServidor(Producto[] misProductos) {
        this.misProductos = misProductos;
    }

    public Producto[] generarProductos() {
        for(int i = 0; i < misProductos.length; i += 1) 
            misProductos[i] = new Producto(id[i], nombre[i], precio[i], generarExistencias(), descripcion[i], false, imagen[i]);
        
        return misProductos;
    }

    public int generarExistencias() {
        return (int)((Math.random() * ((100 - 50) + 1)) + 50);
    }

    private Producto[] misProductos;
    private int[] id = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private String[] nombre = {"Etanol", "Cloro", "Pino", "Jabon tipo 1", "Jabon tipo 2", 
                                "Desinfectante 1", "Desinfectante 2", "Atomizador", "Guantes Latex", "Guantes Tela",
                                "Cubre Boca tipo 1", "Cubre Boca tipo 2", "Toallas tipo 1","Toallas tipo 2", "Toallas tipo 3",
                                "Pepel higienico", "Servilletas", "Platos", "Basos", "Cubiertos"};
    private double[] precio = {60.50, 50.0, 90.0, 27.90, 27.95,
                                52.50, 70.0, 20.79, 15.58, 40.0, 
                                4.0, 7.50, 100.0, 50.0, 30.25, 
                                27.50, 36.50, 200.56, 60.58, 29.99
                            };
    private String[] descripcion = {
        "Alcohol etilico",
        "Producto liquido fuerte para desinfectar, 5.8 litros",
        "Limpiador liquido multiusos, 5.1 litros",
        "Jabon de barra para manos, incluye 3 barras",
        "Jabon liquido para manos, 255 mililitros",
        "Desinfectante liquido",
        "Desinfectante en aerosol",
        "Utensilio para producir una fina pulverizacion de un liquido",
        "Guantes de latex",
        "Guantes de tela lavables",
        "Cubre bocas desechable",
        "Cubre bocas lavable",
        "Toallas con cloro",
        "Toallas con alcohol",
        "Toallas humedas para la cara y manos",
        "Rollo de cuadritos de papel higienico",
        "Pieza rectangular de palel",
        "Recipiente para servir comida",
        "Recipiente para beber agua",
        "Instrumentos empleados de forma manual ingerir alimentos"
    };
    private String[] imagen = {"Etanol", "Cloro", "Pino", "Jabon tipo 1", "Jabon tipo 2", 
                                "Desinfectante 1", "Desinfectante 2", "Atomizador", "Guantes Latex", "Guantes Tela",
                                "Cubre Boca tipo 1", "Cubre Boca tipo 2", "Toallas tipo 1","Toallas tipo 2", "Toallas tipo 3",
                                "Pepel higienico", "Servilletas", "Platos", "Basos", "Cubiertos"
    };
}