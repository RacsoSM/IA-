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
    
    // variables para métricas
    private int nodosExpandidos;
    private int nodosVisitados;
    private long tiempoEjecucion;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    // Método para obtener métricas
    public String obtenerMetricas() {
        return "Nodos expandidos: " + nodosExpandidos + 
               ", Nodos visitados: " + nodosVisitados + 
               ", Tiempo: " + tiempoEjecucion + " ms";
    }

    // 1. Búsqueda Primero en Anchura
    public Nodo realizarBusquedaEnAnchura(String objetivo) {
        long inicio = System.currentTimeMillis();
        nodosExpandidos = 0;
        nodosVisitados = 0;
        
        Queue<Nodo> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        cola.add(raiz);
        visitados.add(raiz.estado);
        nodosVisitados++;

        Nodo actual = null;
        while (!cola.isEmpty()) {
            actual = cola.poll();
            nodosExpandidos++;
            System.out.println("Procesando - " + actual.estado);

            if (actual.estado.equals(objetivo)) {
                tiempoEjecucion = System.currentTimeMillis() - inicio;
                return actual;
            }

            List<String> sucesores = actual.obtenerSucesores();
            for (String sucesor : sucesores) {
                if (!visitados.contains(sucesor)) {
                    System.out.println("Agregando a cola - " + sucesor);
                    visitados.add(sucesor);
                    nodosVisitados++;
                    cola.add(new Nodo(sucesor, actual));
                }
            }
        }
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return null;
    }

    // 2. Búsqueda Primero en Profundidad
    public Nodo realizarBusquedaEnProfundidad(String objetivo) {
        long inicio = System.currentTimeMillis();
        nodosExpandidos = 0;
        nodosVisitados = 0;
        
        Stack<Nodo> pila = new Stack<>();
        Set<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);
        nodosVisitados++;
        
        Nodo actual = null;
        while (!pila.isEmpty()) {
            actual = pila.pop();
            nodosExpandidos++;
            System.out.println("Procesando - " + actual.estado);

            if (actual.estado.equals(objetivo)) {
                tiempoEjecucion = System.currentTimeMillis() - inicio;
                return actual;
            }

            List<String> sucesores = actual.obtenerSucesores();
            for (int i = sucesores.size() - 1; i >= 0; i--) {
                String sucesor = sucesores.get(i);
                if (!visitados.contains(sucesor)) {
                    System.out.println("Agregando a pila - " + sucesor);
                    visitados.add(sucesor);
                    nodosVisitados++;
                    pila.push(new Nodo(sucesor, actual));
                }
            }
        }
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return null;
    }

    // 3. Búsqueda de Costo Uniforme
    public Nodo realizarBusquedaCostoUniforme(String objetivo) {
        long inicio = System.currentTimeMillis();
        nodosExpandidos = 0;
        nodosVisitados = 0;
        
           PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
                return Integer.compare(n1.costo, n2.costo);
            }
        });

        Set<String> visitados = new HashSet<>();
        
        colaPrioridad.add(raiz);
        visitados.add(raiz.estado);
        nodosVisitados++;
        
        Nodo actual = null;
        while (!colaPrioridad.isEmpty()) {
            actual = colaPrioridad.poll();
            nodosExpandidos++;
            System.out.println("Procesando - " + actual.estado);
            
            if (actual.estado.equals(objetivo)) {
                tiempoEjecucion = System.currentTimeMillis() - inicio;
                return actual;
            }
            
            List<String> sucesores = actual.obtenerSucesores();
            for (String sucesor : sucesores) {
                if (!visitados.contains(sucesor)) {
                    System.out.println("Agregando a cola de prioridad - " + sucesor);
                    visitados.add(sucesor);
                    nodosVisitados++;
                    colaPrioridad.add(new Nodo(sucesor, actual, actual.costo + 1, actual.profundidad + 1));
                }
            }
        }
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return null;
    }
    
    // 4. Búsqueda en Profundidad Iterativa (IDS) - Solución simple
public Nodo realizarBusquedaIterativa(String objetivo) {
    long inicio = System.currentTimeMillis();
    
    for (int limite = 0; ; limite++) {
        System.out.println("\nBuscando con límite de profundidad: " + limite);
        
        // Reiniciar contadores para cada iteración
        nodosExpandidos = 0;
        nodosVisitados = 0;
        
        Set<String> visitados = new HashSet<>();
        Nodo resultado = realizarBusquedaConLimite(raiz, objetivo, limite, visitados);
        
        System.out.println("Resultado con límite " + limite + 
                         ": Expandidos = " + nodosExpandidos + 
                         ", Visitados = " + nodosVisitados);
        
        if (resultado != null) {
            tiempoEjecucion = System.currentTimeMillis() - inicio;
            return resultado;
        }
    }
}

private Nodo realizarBusquedaConLimite(Nodo nodoActual, String objetivo, int limite, Set<String> visitados) {
    visitados.add(nodoActual.estado);
    nodosVisitados++;
    nodosExpandidos++;
    
    System.out.println("Procesando - " + nodoActual.estado + " (profundidad: " + nodoActual.profundidad + ")");

    if (nodoActual.estado.equals(objetivo)) {
        return nodoActual;
    }

    if (nodoActual.profundidad >= limite) {
        return null;
    }

    List<String> sucesores = nodoActual.obtenerSucesores();
    for (String sucesor : sucesores) {
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
    
    // 6. Búsqueda A* con heurística de bloques 2x2
    public Nodo realizarBusquedaAEstrellaBloques(String objetivo) {
        long inicio = System.currentTimeMillis();
        nodosExpandidos = 0;
        nodosVisitados = 0;
        
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
                int f1 = n1.costo + n1.heuristica;
                int f2 = n2.costo + n2.heuristica;
                return Integer.compare(f1, f2);
            }
        });
        Set<String> visitados = new HashSet<>();
        
        raiz.heuristica = raiz.calcularHeuristicaBloques(objetivo);
        colaPrioridad.add(raiz);
        visitados.add(raiz.estado);
        nodosVisitados++;

        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();
            nodosExpandidos++;
            System.out.println("Procesando - " + actual.estado + " (costo: " + actual.costo + ", heurística: " + actual.heuristica + ")");

            if (actual.estado.equals(objetivo)) {
                tiempoEjecucion = System.currentTimeMillis() - inicio;
                return actual;
            }

            for (String sucesorStr : actual.obtenerSucesores()) {
                if (!visitados.contains(sucesorStr)) {
                    visitados.add(sucesorStr);
                    nodosVisitados++;
                    Nodo sucesor = new Nodo(sucesorStr, actual);
                    sucesor.heuristica = sucesor.calcularHeuristicaBloques(objetivo);
                    colaPrioridad.add(sucesor);
                }
            }
        }
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return null;
    }
}