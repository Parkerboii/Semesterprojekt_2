package Business;
import Data.EkgData;

public interface EKGObserver{
    void handle(EkgData ekgData);
}
