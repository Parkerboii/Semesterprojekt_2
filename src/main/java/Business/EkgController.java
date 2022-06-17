package Business;

public interface EkgController {
    void startRecording();
    void registerObserver(EKGObserver ekgObserver);

    void setCurrentCpr(String cpr);
}
