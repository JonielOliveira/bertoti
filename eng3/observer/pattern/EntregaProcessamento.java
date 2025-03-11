
package pattern;

public class EntregaProcessamento implements PedidoObserver {
    @Override
    public void atualizar(String pedidoId) {
        System.out.println("Iniciando entrega para o pedido: " + pedidoId);
    }
}
