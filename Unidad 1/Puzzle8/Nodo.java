import java.util.ArrayList;
import java.util.List;

public class Nodo {
    String estado;
    Nodo padre;
    int costo;
    int profundidad;
    
    public Nodo(String estado, Nodo padre) {
        this.estado = estado;
        this.padre = padre;
        this.costo = (padre != null) ? padre.costo + 1 : 0;
        this.profundidad = (padre != null) ? padre.profundidad + 1 : 0;
    }
    
    
    public Nodo(String estado, Nodo padre, int costo, int profundidad) {
        this.estado = estado;
        this.padre = padre;
        this.costo = costo;
        this.profundidad = profundidad;
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
}