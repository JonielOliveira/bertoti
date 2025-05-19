package pattern;

import java.util.*;

public class InventoryModel implements ProductSubject {
    private List<ProductObserver> observers = new ArrayList<>();
    private String productName = "Notebook";
    private int quantity = 100;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyObservers();
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void registerObserver(ProductObserver o) {
        observers.add(o);
    }

    public void removeObserver(ProductObserver o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (ProductObserver o : observers) {
            o.update(productName, quantity);
        }
    }
}
