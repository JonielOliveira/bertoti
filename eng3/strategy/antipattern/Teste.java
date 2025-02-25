package antipattern;

public class Teste {

    public static void main(String[] args) {
        
        Viajante joao = new ViajanteCarro();
        joao.viajar();
        
        System.out.println("--------------------------------");
        
        joao = new ViajanteBicicleta();
        joao.viajar();
    }
}