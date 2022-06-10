package Data;

import Business.EKGObserver;

public interface Simulator {
    void record();
    void setObserver(EKGObserver observer);

}
