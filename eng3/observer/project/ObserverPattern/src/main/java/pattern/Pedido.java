
package pattern;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<PedidoObserver> observadores = new ArrayList<>();

    public void adicionarObservador(PedidoObserver observer) {
        observadores.add(observer);
    }

    public void removerObservador(PedidoObserver observer) {
        observadores.remove(observer);
    }

    public void novoPedido(String pedidoId) {
        System.out.println("Novo pedido criado: " + pedidoId);
        notificarObservadores(pedidoId);
    }

    private void notificarObservadores(String pedidoId) {
        for (PedidoObserver observer : observadores) {
            observer.atualizar(pedidoId);
        }
    }
}
