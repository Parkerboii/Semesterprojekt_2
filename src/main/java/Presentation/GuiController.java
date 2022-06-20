package Presentation;

import Business.EKGObserver;
import Business.EkgController;
import Business.EkgControllerImpl;
import Data.PatientDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;

public class GuiController extends CPRGuiController implements EKGObserver {
    public Polyline polyline;
    ObservableList<Double> current_point;
    double border = 200.0;

    EkgController ekgController = new EkgControllerImpl();
    @FXML
    public TextArea ekgView;
    private Timestamp startTime;

    @FXML
    Label actualCPR;
    private String CPRnumber;

    public void displayCpr(String currentCpr){
        actualCPR.setText("CPR: " + currentCpr);
    }

    public void setCurrentCPR(String cpr) {
         CPRnumber = cpr;
         ekgController.setCurrentCpr(cpr);
    }


    public void startEkg(MouseEvent mouseEvent) {
        if (CPRnumber != null){
            ekgController.startRecording();
            this.startTime = new Timestamp(System.currentTimeMillis());
            ekgController.registerObserver(this);
        }
    }


    @Override
    public void handle(Data.EkgData ekgData) {
        ekgView.setText(ekgView.getText()+"\n" + ekgData);
        current_point = polyline.getPoints();
        //Bruger getTime metoden, og bruger så en anden getTime metode, som konvertere fra Timestamp til long, og
        // dividere med 1000 for at få sekunder
        polyline.getPoints().addAll((ekgData.getTime().getTime()-startTime.getTime())/250.0, (double) (ekgData.getVoltage()*100));
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
