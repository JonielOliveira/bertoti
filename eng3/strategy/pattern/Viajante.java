package pattern;

public class Viajante {

    private Transporte transporte;
    
    public void setTransporte(Transporte transporte){
        this.transporte = transporte;
    }
    
    public void viajar() {
        this.transporte.mover();
    }
}
