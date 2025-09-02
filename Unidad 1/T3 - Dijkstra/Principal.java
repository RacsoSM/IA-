package Dijkstra;

import java.util.Scanner;

public class Principal {

	
	// Método principal
    public static void main(String args[]) {
        int nver, origen;
        Scanner valores = new Scanner(System.in);

        System.out.print("Ingrese el numero de nodos del grafo: ");
        nver = valores.nextInt();

        System.out.print("Indique el nodo de origen (1 a " + nver + "): ");
        origen = valores.nextInt() - 1;

        Dijkstra grafo = new Dijkstra(nver);

        grafo.leetecl(nver);
        grafo.infin(nver);
        grafo.dijks1(nver, origen);
        grafo.Imprime(nver, origen);
    }
}
