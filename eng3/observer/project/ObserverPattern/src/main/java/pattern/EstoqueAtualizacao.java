
package pattern;

public class EstoqueAtualizacao implements PedidoObserver {
    @Override
    public void atualizar(String pedidoId) {
        System.out.println("Atualizando estoque para o pedido: " + pedidoId);
    }
}
