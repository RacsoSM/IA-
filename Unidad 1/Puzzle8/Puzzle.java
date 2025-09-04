import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Puzzle {


    public static void main(String a[]){
          String estadoInicial ="7245 6831";
          String estadoFinal =" 12345678";
          int contadorMovimientos = 0;

        Arbol arbol = new Arbol(new Nodo(estadoInicial, null));
        Nodo resultado = arbol.realizarBusquedaEnAnchura(estadoFinal);
        //iterar para llegar al nodo raiz y apartir de alli mostrar el resultado (serie de pasos)
        Stack<String> pilaMovimientos = new Stack<>();

        //metemos los resultados a la pila para obtener el orden correcto y iteramos hasta llegar a la raiz
        while (resultado.padre != null){
            pilaMovimientos.push(resultado.estado);
            resultado = resultado.padre;
        }

        //lo utilizamos para comparar el primer movimiento
        String estadoAnterior = estadoInicial;

        //mostramos los pasos en orden sacandolos de la pila
        while (!pilaMovimientos.isEmpty() ){
            String estadoActual = pilaMovimientos.pop();
            contadorMovimientos++;
            detectarMovimiento(estadoAnterior, estadoActual, contadorMovimientos);
            imprimirMovimiento(estadoActual);
            estadoAnterior = estadoActual;
        }
       

    }

    //metodo para dar pequeño formato de salida
    public static void imprimirMovimiento(String movimiento){
        // Línea superior
        System.out.println("-------------");

        for (int i = 0; i < movimiento.length(); i++) {
            System.out.print("| " + movimiento.charAt(i) + " ");

            
            if ((i + 1) % 3 == 0) {
                System.out.println("|");
                System.out.println("-------------");
            }
        }
         System.out.println("\n");
    }
   
     public static void detectarMovimiento(String antes, String despues, int numeroMovimiento) {
        int posEspacioAntes = antes.indexOf(' ');
        int posEspacioDespues = despues.indexOf(' ');

        char piezaMovida = despues.charAt(posEspacioAntes);
        String direccion = obtenerDireccion(posEspacioAntes, posEspacioDespues);

        System.out.println("Movimiento " + numeroMovimiento + ":\t La ficha " + piezaMovida + " se movió hacia " + direccion);

        }
        

     public static String obtenerDireccion(int antes, int despues) {
        //hacemos como si fuera una matriz 3x3
        int filaAntes = antes / 3;
        int colAntes = antes % 3;

        int filaDespues = despues / 3;
        int colDespues = despues % 3;

        if (filaDespues < filaAntes) return "ABAJO";
        if (filaDespues > filaAntes) return "ARRIBA";
        if (colDespues < colAntes) return "DERECHA";
        if (colDespues > colAntes) return "IZQUIERDA";

        return "SIN MOVIMIENTO";
    }



 
}