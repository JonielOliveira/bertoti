package pattern;

import java.util.*;

public class Teste {
    public static void main(String[] args) {
        InventoryModel model = new InventoryModel();
        InventoryController controller = new InventoryController(model);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            Random rand = new Random();
            public void run() {
                int simulatedQuantity = rand.nextInt(200);
                model.setQuantity(simulatedQuantity);
            }
        }, 0, 15000);
    }
}
