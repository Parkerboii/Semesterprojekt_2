package Business;

public interface EkgController {
    void startRecording();
    void registerObserver(EKGObserver ekgObserver);
}
