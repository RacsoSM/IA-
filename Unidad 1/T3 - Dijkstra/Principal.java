package Dijkstra;

import java.util.Scanner;

public class Principal {

	
	// Método principal
    public static void main(String args[]) {
        int numeroVertices, origen;
        Scanner valores = new Scanner(System.in);

        System.out.print("Ingrese el numero de nodos del grafo: ");
        numeroVertices = valores.nextInt();

        System.out.print("Indique el nodo de origen (1 a " + numeroVertices + "): ");
        origen = valores.nextInt() - 1;

        Dijkstra grafo = new Dijkstra(numeroVertices);

    grafo.leerGrafoPorTeclado(numeroVertices);
    grafo.inicializarInfinito(numeroVertices);
    grafo.ejecutarDijkstra(numeroVertices, origen);
    grafo.imprimirResultados(numeroVertices, origen);
    }
}
