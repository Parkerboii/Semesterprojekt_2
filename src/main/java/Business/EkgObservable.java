package Business;

public interface EkgObservable extends Runnable {
    void register(EKGObserver EkgObserver);
}
