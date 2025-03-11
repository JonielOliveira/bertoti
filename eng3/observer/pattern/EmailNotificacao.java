
package pattern;

public class EmailNotificacao implements PedidoObserver {
    @Override
    public void atualizar(String pedidoId) {
        System.out.println("Enviando e-mail de confirmação para o pedido: " + pedidoId);
    }
}
