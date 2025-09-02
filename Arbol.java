package ArbolBinario;
public class Arbol {

    //Declaramos la raiz del arbol
    Nodo raiz;

    //Constructor
    public Arbol() {
        this.raiz = null;
    }

    //Comprobamos si el arbol esta vacio
    public boolean vacio() {
        return this.raiz == null;
    }

    //Buscamos un nodo por su valor
     public Nodo buscarNodo(int valor) {
        return buscarNodoRec(raiz, valor);
    }

    //Metodo auxiliar recursivo para buscar los nodos
    private Nodo buscarNodoRec(Nodo actual, int valor) {
        if (actual == null) {
            return null; // No encontrado
        }
        if (actual.valor == valor) {
            return actual; // Encontrado
        }

        // Buscar en subárbol izquierdo
        Nodo encontrado = buscarNodoRec(actual.izquierdo, valor);
        if (encontrado != null) {
            return encontrado;
        }

        // Buscar en subárbol derecho
        return buscarNodoRec(actual.derecho, valor);
    }


    //Metodo para insertar
     public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    //Metodo auxiliar recursivo para insertar los nodos
    private Nodo insertarRec(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRec(actual.derecho, valor);
        }
        //evitamos duplicados
        return actual;
    }

    // Imprimir inorden 
    public void imprimir() {
        imprimirRec(raiz);
        System.out.println();
    }

    private void imprimirRec(Nodo actual) {
        if (actual != null) {
            imprimirRec(actual.izquierdo);
            System.out.print(actual.valor + " ");
            imprimirRec(actual.derecho);
        }
    }



 
}
