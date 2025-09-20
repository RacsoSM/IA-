import java.util.ArrayList;
import java.util.List;

public class Nodo {
    String estado;
    Nodo padre;
    int costo;
    int profundidad;
    int heuristica; 
    
    public Nodo(String estado, Nodo padre) {
        this.estado = estado;
        this.padre = padre;
        this.costo = (padre != null) ? padre.costo + 1 : 0;
        this.profundidad = (padre != null) ? padre.profundidad + 1 : 0;
        this.heuristica = 0; 
    }
    
    
    public Nodo(String estado, Nodo padre, int costo, int profundidad) {
        this.estado = estado;
        this.padre = padre;
        this.costo = costo;
        this.profundidad = profundidad;
        this.heuristica = 0;
    }

    public List<String> obtenerSucesores() {
        List<String> successors = new ArrayList<>();
        int posEspacio = this.estado.indexOf(" ");

        switch (posEspacio) {
            case 0:
                successors.add(this.estado.replace(this.estado.charAt(0), '*').replace(this.estado.charAt(1), this.estado.charAt(0)).replace('*', this.estado.charAt(1)));
                successors.add(this.estado.replace(this.estado.charAt(0), '*').replace(this.estado.charAt(3), this.estado.charAt(0)).replace('*', this.estado.charAt(3)));
                break;
            case 1:
                successors.add(this.estado.replace(this.estado.charAt(1), '*').replace(this.estado.charAt(0), this.estado.charAt(1)).replace('*', this.estado.charAt(0)));
                successors.add(this.estado.replace(this.estado.charAt(1), '*').replace(this.estado.charAt(2), this.estado.charAt(1)).replace('*', this.estado.charAt(2)));
                successors.add(this.estado.replace(this.estado.charAt(1), '*').replace(this.estado.charAt(4), this.estado.charAt(1)).replace('*', this.estado.charAt(4)));
                break;
            case 2:
                successors.add(this.estado.replace(this.estado.charAt(2), '*').replace(this.estado.charAt(1), this.estado.charAt(2)).replace('*', this.estado.charAt(1)));
                successors.add(this.estado.replace(this.estado.charAt(2), '*').replace(this.estado.charAt(5), this.estado.charAt(2)).replace('*', this.estado.charAt(5)));
                break;
            case 3:
                successors.add(this.estado.replace(this.estado.charAt(3), '*').replace(this.estado.charAt(0), this.estado.charAt(3)).replace('*', this.estado.charAt(0)));
                successors.add(this.estado.replace(this.estado.charAt(3), '*').replace(this.estado.charAt(4), this.estado.charAt(3)).replace('*', this.estado.charAt(4)));
                successors.add(this.estado.replace(this.estado.charAt(3), '*').replace(this.estado.charAt(6), this.estado.charAt(3)).replace('*', this.estado.charAt(6)));
                break;
            case 4:
                successors.add(this.estado.replace(this.estado.charAt(4), '*').replace(this.estado.charAt(1), this.estado.charAt(4)).replace('*', this.estado.charAt(1)));
                successors.add(this.estado.replace(this.estado.charAt(4), '*').replace(this.estado.charAt(3), this.estado.charAt(4)).replace('*', this.estado.charAt(3)));
                successors.add(this.estado.replace(this.estado.charAt(4), '*').replace(this.estado.charAt(5), this.estado.charAt(4)).replace('*', this.estado.charAt(5)));
                successors.add(this.estado.replace(this.estado.charAt(4), '*').replace(this.estado.charAt(7), this.estado.charAt(4)).replace('*', this.estado.charAt(7)));
                break;
            case 5:
                successors.add(this.estado.replace(this.estado.charAt(5), '*').replace(this.estado.charAt(2), this.estado.charAt(5)).replace('*', this.estado.charAt(2)));
                successors.add(this.estado.replace(this.estado.charAt(5), '*').replace(this.estado.charAt(4), this.estado.charAt(5)).replace('*', this.estado.charAt(4)));
                successors.add(this.estado.replace(this.estado.charAt(5), '*').replace(this.estado.charAt(8), this.estado.charAt(5)).replace('*', this.estado.charAt(8)));
                break;
            case 6:
                successors.add(this.estado.replace(this.estado.charAt(6), '*').replace(this.estado.charAt(3), this.estado.charAt(6)).replace('*', this.estado.charAt(3)));
                successors.add(this.estado.replace(this.estado.charAt(6), '*').replace(this.estado.charAt(7), this.estado.charAt(6)).replace('*', this.estado.charAt(7)));
                break;
            case 7:
                successors.add(this.estado.replace(this.estado.charAt(7), '*').replace(this.estado.charAt(4), this.estado.charAt(7)).replace('*', this.estado.charAt(4)));
                successors.add(this.estado.replace(this.estado.charAt(7), '*').replace(this.estado.charAt(6), this.estado.charAt(7)).replace('*', this.estado.charAt(6)));
                successors.add(this.estado.replace(this.estado.charAt(7), '*').replace(this.estado.charAt(8), this.estado.charAt(7)).replace('*', this.estado.charAt(8)));
                break;
            case 8:
                successors.add(this.estado.replace(this.estado.charAt(8), '*').replace(this.estado.charAt(5), this.estado.charAt(8)).replace('*', this.estado.charAt(5)));
                successors.add(this.estado.replace(this.estado.charAt(8), '*').replace(this.estado.charAt(7), this.estado.charAt(8)).replace('*', this.estado.charAt(7)));
                break;
        }
        return successors;
    }


    //Heuristica
    //Esta heuristica funciona contando bloques de 2x2 dentro de la matriz que supone el estado y verificando si estan correctos o no
     public int calcularHeuristicaBloques(String objetivo) {
           int bloquesIncorrectos = 0;
        
        // bloque superior izquierdo (posiciones 0,1,3,4)
        if (!(estado.charAt(0) == objetivo.charAt(0) && 
              estado.charAt(1) == objetivo.charAt(1) &&
              estado.charAt(3) == objetivo.charAt(3) && 
              estado.charAt(4) == objetivo.charAt(4))) {
            bloquesIncorrectos++;
        }
        
        // bloque superior derecho (posiciones 1,2,4,5)
        if (!(estado.charAt(1) == objetivo.charAt(1) && 
              estado.charAt(2) == objetivo.charAt(2) &&
              estado.charAt(4) == objetivo.charAt(4) && 
              estado.charAt(5) == objetivo.charAt(5))) {
            bloquesIncorrectos++;
        }
        
        // bloque inferior izquierdo (posiciones 3,4,6,7)
        if (!(estado.charAt(3) == objetivo.charAt(3) && 
              estado.charAt(4) == objetivo.charAt(4) &&
              estado.charAt(6) == objetivo.charAt(6) && 
              estado.charAt(7) == objetivo.charAt(7))) {
            bloquesIncorrectos++;
        }
        
        // bloque inferior derecho (posiciones 4,5,7,8)
        if (!(estado.charAt(4) == objetivo.charAt(4) && 
              estado.charAt(5) == objetivo.charAt(5) &&
              estado.charAt(7) == objetivo.charAt(7) && 
              estado.charAt(8) == objetivo.charAt(8))) {
            bloquesIncorrectos++;
        }
        
        return bloquesIncorrectos;
    }
}