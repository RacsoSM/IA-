package Dijkstra;
import java.util.*;


public class Dijkstra {

    int costo[];
    int camino[];
    boolean visitado[];
    int[][] graf;

    // Constructor
    public Dijkstra(int nver) {
        this.costo = new int[nver];
        this.camino = new int[nver];
        this.visitado = new boolean[nver];
        this.graf = new int[nver][nver];
    }

    // Método para leer las conexiones y pesos
    public void leetecl(int nver) {
        int nady, ady, peso, contador=0;
        Scanner valor = new Scanner(System.in);
        for (int i = 0; i < nver; i++) {
        	contador =0;
            System.out.print("\nIngrese el numero de arcos del nodo " + (i + 1) + " :");
            nady = valor.nextInt();
            while (nady != 0) {
            	contador++;
                System.out.print("\n\tDireccion del arco numero " + contador+ ": ");
                ady = valor.nextInt() - 1;
                System.out.print("\tIngrese el peso de esta arista: ");
                peso = valor.nextInt();
                graf[i][ady] = peso;
                nady--;
            }
        }
    }

    // Método para inicializar valores infinitos en la matriz de adyacencia
    public void infin(int nver) {
        for (int i = 0; i < nver; i++)
            for (int j = 0; j < nver; j++)
                if (graf[i][j] == 0)
                    graf[i][j] = 10000;
    }

    // Método para encontrar el índice del vértice con el costo mínimo no visitado
    public int indi(int nver) {
        int aux = 10000;
        int in = 0;
        for (int i = 1; i < nver; i++) {
            if (visitado[i] == false)
                if (costo[i] < aux) {
                    aux = costo[i];
                    in = i;
                }
        }
        return in;
    }

    // Método principal para ejecutar el algoritmo de Dijkstra
    public void dijks1(int nver, int origen) {
        int i;
        int j = 0;
        int ind = 0;
        for (i = 0; i < nver; i++) {
            visitado[i] = false;
            costo[i] = graf[origen][i];
            camino[i] = origen;
        }

        visitado[origen] = true;

        for (i = 0; i < nver; i++) {
            ind = indi(nver);
            if (ind != 0) {
                visitado[ind] = true;
                for (j = 0; j < nver; j++) {
                    if (graf[ind][j] != 10000)
                        if (visitado[j] == false)
                            if ((costo[ind] + graf[ind][j]) < costo[j]) {
                                costo[j] = costo[ind] + graf[ind][j];
                                camino[j] = ind;
                            }
                }
            }
        }
    }

    // Método recursivo para imprimir el camino mínimo
    public void camin(int ver) {
        if (ver != camino[ver])
            camin(camino[ver]);

        System.out.print(" " + (ver + 1) + ",");
    }

    // Método para imprimir los caminos mínimos y sus costos
    public void Imprime(int nver, int origen) {
        System.out.println("\n\n  * Direccion y costo de caminos minimos desde el nodo " + (origen + 1) + " * \n\n");
        for (int i = 0; i < nver; i++) {
            if (i != origen) {
            	
            	 if(costo[i] == 10000) {
                 	System.out.println(" \t Desde " + (origen + 1) + " a " + (i + 1)+ " No existe una ruta hacia este nodo");
                 	continue;
                 }
            	
                System.out.print("\t Desde " + (origen + 1) + " a " + (i + 1) + " =");
                camin(i);
                System.out.print(" = " + costo[i] + "\n");
            }
        }
    }

}