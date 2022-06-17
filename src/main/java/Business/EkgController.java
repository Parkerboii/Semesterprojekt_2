package Business;

public interface EkgController {
    void startRecording();
    void registerObserver(EKGObserver ekgObserver);

    void setCurrentCpr(String cpr);

    void setCurrentFirstName(String first);

    void setCurrentLastName(String last);
}
