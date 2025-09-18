import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;

public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    // 1. Búsqueda Primero en Anchura
    public Nodo realizarBusquedaEnAnchura(String objetivo) {
        Queue<Nodo> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        cola.add(raiz);
        visitados.add(raiz.estado);

        Nodo actual = null;
        while (!cola.isEmpty()) {
            actual = cola.poll();
            System.out.println("Procesando - " + actual.estado);

            if (actual.estado.equals(objetivo)) {
                return actual;
            }

            List<String> sucesores = actual.obtenerSucesores();
            for (String sucesor : sucesores) {
                if (!visitados.contains(sucesor)) {
                    System.out.println("Agregando a cola - " + sucesor);
                    visitados.add(sucesor);
                    cola.add(new Nodo(sucesor, actual));
                }
            }
        }
        return null;
    }

    // 2. Búsqueda Primero en Profundidad 
    public Nodo realizarBusquedaEnProfundidad(String objetivo) {
        Stack<Nodo> pila = new Stack<>();
        Set<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);
        
        Nodo actual = null;
        while (!pila.isEmpty()) {
            actual = pila.pop();
            System.out.println("Procesando - " + actual.estado);

            if (actual.estado.equals(objetivo)) {
                return actual;
            }

            // Generamos sucesores y los agregamos a la pila si no han sido visitados
            List<String> sucesores = actual.obtenerSucesores();
            for (int i = sucesores.size() - 1; i >= 0; i--) {
                String sucesor = sucesores.get(i);
                if (!visitados.contains(sucesor)) {
                    System.out.println("Agregando a pila - " + sucesor);
                    visitados.add(sucesor);
                    pila.push(new Nodo(sucesor, actual));
                }
            }
        }
        return null;
    }

    // 3. Búsqueda de Costo Uniforme 
    public Nodo realizarBusquedaCostoUniforme(String objetivo) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(n -> n.costo));
        Set<String> visitados = new HashSet<>();
        
        colaPrioridad.add(raiz);
        visitados.add(raiz.estado);
        
        Nodo actual = null;
        while (!colaPrioridad.isEmpty()) {
            actual = colaPrioridad.poll();
            System.out.println("Procesando - " + actual.estado);
            
            if (actual.estado.equals(objetivo)) {
                return actual;
            }
            
            List<String> sucesores = actual.obtenerSucesores();
            for (String sucesor : sucesores) {
                if (!visitados.contains(sucesor)) {
                    System.out.println("Agregando a cola de prioridad - " + sucesor);
                    visitados.add(sucesor);
                    colaPrioridad.add(new Nodo(sucesor, actual, actual.costo + 1, actual.profundidad + 1));
                }
            }
        }
        return null;
    }
    
    // 4. Búsqueda en Profundidad Iterativa (IDS)
    public Nodo realizarBusquedaIterativa(String objetivo) {
        for (int limite = 0; ; limite++) {
            System.out.println("\nBuscando con límite de profundidad: " + limite);
            
            Nodo resultado = realizarBusquedaConLimite(raiz, objetivo, limite, new HashSet<>());
            if (resultado != null) {
                return resultado;
            }
        }
    }
    
    // 5. Búsqueda en Profundidad con Límite 
    public Nodo realizarBusquedaConLimite(String objetivo, int limite) {
        return realizarBusquedaConLimite(raiz, objetivo, limite, new HashSet<>());
    }
    
    private Nodo realizarBusquedaConLimite(Nodo nodoActual, String objetivo, int limite, Set<String> visitados) {
     
        visitados.add(nodoActual.estado);
        System.out.println("Procesando - " + nodoActual.estado + " (profundidad: " + nodoActual.profundidad + ")");

        if (nodoActual.estado.equals(objetivo)) {
            return nodoActual;
        }

        if (nodoActual.profundidad >= limite) {
            return null;
        }

        List<String> sucesores = nodoActual.obtenerSucesores();
        for (String sucesor : sucesores) {
            // Se comprueba si el sucesor ya fue visitado en esta rama antes de continuar
            if (!visitados.contains(sucesor)) {
                Nodo hijo = new Nodo(sucesor, nodoActual);
                Nodo resultado = realizarBusquedaConLimite(hijo, objetivo, limite, visitados);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }
}