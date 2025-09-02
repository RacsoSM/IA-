package Dijkstra;
import java.util.Scanner;

public class Dijkstra {

    int[] distanciaMinima;
    int[] predecesor;
    boolean[] nodoVisitado;
    int[][] matrizAdyacencia;

    // Constructor
    public Dijkstra(int numeroVertices) {
        this.distanciaMinima = new int[numeroVertices];
        this.predecesor = new int[numeroVertices];
        this.nodoVisitado = new boolean[numeroVertices];
        this.matrizAdyacencia = new int[numeroVertices][numeroVertices];
    }

    // Método para leer las conexiones y pesos
    public void leerGrafoPorTeclado(int numeroVertices) {
        int numeroArcos, verticeAdyacente, pesoArista, contadorArcos;
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < numeroVertices; i++) {
            contadorArcos = 0;
            System.out.print("\nIngrese el número de arcos del nodo " + (i + 1) + ": ");
            numeroArcos = entrada.nextInt();
            while (numeroArcos != 0) {
                contadorArcos++;
                System.out.print("\n\tDirección del arco número " + contadorArcos + ": ");
                verticeAdyacente = entrada.nextInt() - 1;
                System.out.print("\tIngrese el peso de esta arista: ");
                pesoArista = entrada.nextInt();
                matrizAdyacencia[i][verticeAdyacente] = pesoArista;
                numeroArcos--;
            }
        }
    }

    // Método para inicializar valores infinitos en la matriz de adyacencia
    public void inicializarInfinito(int numeroVertices) {
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (matrizAdyacencia[i][j] == 0) {
                    matrizAdyacencia[i][j] = 10000;
                }
            }
        }
    }

    // Método para encontrar el índice del vértice con el costo mínimo no visitado
    public int obtenerIndiceMinimo(int numeroVertices) {
        int distanciaTemporal = 10000;
        int indice = 0;
        for (int i = 1; i < numeroVertices; i++) {
            if (!nodoVisitado[i] && distanciaMinima[i] < distanciaTemporal) {
                distanciaTemporal = distanciaMinima[i];
                indice = i;
            }
        }
        return indice;
    }

    // Método principal para ejecutar el algoritmo de Dijkstra
    public void ejecutarDijkstra(int numeroVertices, int origen) {
        int i, j;
        int indiceMinimo;

        for (i = 0; i < numeroVertices; i++) {
            nodoVisitado[i] = false;
            distanciaMinima[i] = matrizAdyacencia[origen][i];
            predecesor[i] = origen;
        }

        nodoVisitado[origen] = true;

        for (i = 0; i < numeroVertices; i++) {
            indiceMinimo = obtenerIndiceMinimo(numeroVertices);
            if (indiceMinimo != 0) {
                nodoVisitado[indiceMinimo] = true;
                for (j = 0; j < numeroVertices; j++) {
                    if (matrizAdyacencia[indiceMinimo][j] != 10000 && !nodoVisitado[j]) {
                        if ((distanciaMinima[indiceMinimo] + matrizAdyacencia[indiceMinimo][j]) < distanciaMinima[j]) {
                            distanciaMinima[j] = distanciaMinima[indiceMinimo] + matrizAdyacencia[indiceMinimo][j];
                            predecesor[j] = indiceMinimo;
                        }
                    }
                }
            }
        }
    }

    // Método recursivo para imprimir el camino mínimo
    public void imprimirCamino(int vertice) {
        if (vertice != predecesor[vertice]) {
            imprimirCamino(predecesor[vertice]);
        }
        System.out.print(" " + (vertice + 1) + ",");
    }

    // Método para imprimir los caminos mínimos y sus costos
    public void imprimirResultados(int numeroVertices, int origen) {
        System.out.println("\n\n  * Dirección y costo de caminos mínimos desde el nodo " + (origen + 1) + " * \n\n");
        for (int i = 0; i < numeroVertices; i++) {
            if (i != origen) {

                if (distanciaMinima[i] == 10000) {
                    System.out.println("\tDesde " + (origen + 1) + " a " + (i + 1) + " no existe una ruta hacia este nodo");
                    continue;
                }

                System.out.print("\tDesde " + (origen + 1) + " a " + (i + 1) + " =");
                imprimirCamino(i);
                System.out.print(" = " + distanciaMinima[i] + "\n");
            }
        }
    }
}
