package Presentation;

import Business.EKGObserver;
import Business.EkgController;
import Business.EkgControllerImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

import java.sql.Timestamp;

public class GuiController implements EKGObserver {
    public Polyline polyline;
    ObservableList<Double> current_point;
    double border = 200.0;

    EkgController ekgController = new EkgControllerImpl();
    @FXML
    public TextArea ekgView;
    private Timestamp startTime;


    public void startEkg(MouseEvent mouseEvent) {
        ekgController.startRecording();
        this.startTime = new Timestamp(System.currentTimeMillis());
        ekgController.registerObserver(this);
    }

    @Override
    public void handle(Data.EkgData ekgData) {
        ekgView.setText(ekgView.getText()+"\n" + ekgData);
        current_point = polyline.getPoints();
        //Bruger getTime metoden, og bruger så en anden getTime metode, som konvertere fra Timestamp til long, og
        // dividere med 1000 for at få sekunder
        polyline.getPoints().addAll((ekgData.getTime().getTime()-startTime.getTime())/250.0,ekgData.getVoltage()*100);
        if (current_point.size() >= border){
            current_point.clear();
        }
    }

    /*Stage stage;
    public void stopEKG(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Stopper EKG");
        alert.setHeaderText("Du er ved at stoppe EKG");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Du stoppede EKG");
            stage.close();
        }
    }*/

}
