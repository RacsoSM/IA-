package ArbolBinario;

public class Principal {
     public static void main(String[] args) {
        Arbol arbol = new Arbol();

     
        arbol.insertar(5);
        arbol.insertar(9);
        arbol.insertar(7);
        arbol.insertar(2);
        arbol.insertar(4);
      

      
        System.out.println("Recorrido inorden:");
        arbol.imprimir();

        // Buscar un nodo
        int buscado = 90;
        Nodo resultado = arbol.buscarNodo(buscado);
        if (resultado != null) {
            System.out.println("Nodo encontrado: " + resultado.valor);
        } else {
            System.out.println("Nodo " + buscado + " no encontrado.");
        }
    }
}
