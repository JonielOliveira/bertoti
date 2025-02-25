package pattern;

public class Teste {

    public static void main(String[] args) {
        
        Viajante joao = new Viajante();
        
        joao.setTransporte(new Carro());
        joao.viajar();
        
        System.out.println("--------------------------------");
        
        joao.setTransporte(new Bicicleta());
        joao.viajar();
    }
}
