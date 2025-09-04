import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }

      public Nodo realizarBusquedaEnAnchura(String objetivo){
       //usara las variables de clase
       Queue <Nodo> cola = new LinkedList<Nodo>();
       HashSet<String> visitados = new HashSet<String>();

       cola.add(raiz);
       visitados.add(raiz.estado);

       boolean encontrado = false;
       Nodo actual = null;
       while(!encontrado && cola.size() > 0 ){

            actual = cola.poll();
            System.out.println("Procesando - " + actual.estado);

            if(actual.estado.equals(objetivo)){
                encontrado = true;
            }
            else{
                List <String> sucesores = actual.obtenerSucesores();
                for (String sucesor : sucesores) {
                    //si ya fue procesado, ignorar el nodo
                    if(visitados.contains(sucesor))
                        continue;
                    System.out.println("Agregando a cola - " + sucesor);
                    cola.add(new Nodo(sucesor,actual));
                    visitados.add(sucesor);
                }
            }
            
       }

       return actual;
   }
}
