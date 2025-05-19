package pattern;

import java.util.Timer;
import java.util.TimerTask;

public class InventoryController implements ControllerInterface {
    private ProductSubject model;
    private InventoryView view;

    public InventoryController(ProductSubject model) {
        this.model = model;
        this.view = new InventoryView(model, this);
        this.view.createView();
    }

    public void subscribe() {
        model.registerObserver(view);
        System.out.println("Inscrito no monitoramento de estoque");
    }

    public void unsubscribe() {
        model.removeObserver(view);
        System.out.println("Cancelada a inscrição no estoque");
    }
}
