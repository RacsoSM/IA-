import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Puzzle {


    public static void main(String a[]){
          String estadoInicial ="7245 6831";
          String estadoFinal =" 12345678";
         Scanner scanner = new Scanner(System.in);
        int opcion;

       
        do {
            System.out.println("--- Menú de Algoritmos de Búsqueda ---");
            System.out.println("1. Búsqueda Primero en Anchura (BFS)");
            System.out.println("2. Búsqueda Primero en Profundidad (DFS)");
            System.out.println("3. Búsqueda de Costo Uniforme (UCS)");
            System.out.println("4. Búsqueda en Profundidad Iterativa (IDS)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            Nodo resultado = null;
            Arbol arbol;

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Búsqueda Primero en Anchura (BFS) ---");
                    arbol = new Arbol(new Nodo(estadoInicial, null));
                    resultado = arbol.realizarBusquedaEnAnchura(estadoFinal);
                    break;
                case 2:
                    System.out.println("\n--- Búsqueda Primero en Profundidad (DFS) ---");
                    arbol = new Arbol(new Nodo(estadoInicial, null));
                    resultado = arbol.realizarBusquedaEnProfundidad(estadoFinal);
                    break;
                case 3:
                    System.out.println("\n--- Búsqueda de Costo Uniforme (UCS) ---");
                    arbol = new Arbol(new Nodo(estadoInicial, null));
                    resultado = arbol.realizarBusquedaCostoUniforme(estadoFinal);
                    break;
                case 4:
                    System.out.println("\n--- Búsqueda en Profundidad Iterativa (IDS) ---");
                    arbol = new Arbol(new Nodo(estadoInicial, null));
                    resultado = arbol.realizarBusquedaIterativa(estadoFinal);
                    break;
                
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            if (opcion != 0) {
                mostrarResultado(resultado, estadoInicial);
            }

        } while (opcion != 0);

        scanner.close();
    }

    public static void imprimirMovimiento(String movimiento) {
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

    public static void mostrarResultado(Nodo resultado, String estadoInicial) {
        if (resultado != null) {
            Stack<String> pilaMovimientos = new Stack<>();
            while (resultado.padre != null) {
                pilaMovimientos.push(resultado.estado);
                resultado = resultado.padre;
            }

            String estadoAnterior = estadoInicial;
            int contadorMovimientos = 0;

            while (!pilaMovimientos.isEmpty()) {
                String estadoActual = pilaMovimientos.pop();
                contadorMovimientos++;
                detectarMovimiento(estadoAnterior, estadoActual, contadorMovimientos);
                imprimirMovimiento(estadoActual);
                estadoAnterior = estadoActual;
            }
            System.out.println("Solución encontrada en " + contadorMovimientos + " movimientos.");
            System.out.println("----------------------------------------\n");
        } else {
            System.out.println("No se encontró una solución.");
            System.out.println("----------------------------------------\n");
        }
    }
}