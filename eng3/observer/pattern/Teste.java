
package pattern;

public class Teste {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();

        // Criamos os observadores
        EmailNotificacao email = new EmailNotificacao();
        EstoqueAtualizacao estoque = new EstoqueAtualizacao();
        EntregaProcessamento entrega = new EntregaProcessamento();

        // Adicionamos os observadores ao Pedido
        pedido.adicionarObservador(email);
        pedido.adicionarObservador(estoque);
        pedido.adicionarObservador(entrega);

        // Criamos um novo pedido
        pedido.novoPedido("12345");
    }
}