package pattern;

public interface ProductSubject {
    void registerObserver(ProductObserver o);
    void removeObserver(ProductObserver o);
    void notifyObservers();  
}
