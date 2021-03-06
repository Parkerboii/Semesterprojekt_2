package Presentation;

import Business.EKGObserver;
import Business.EkgController;
import Business.EkgControllerImpl;
import Data.PatientDTO;
import javafx.application.Platform;
import Data.EkgDTO;
import Data.Resultset;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;

import java.sql.Timestamp;
import java.util.List;

/*@author  Michael Parker, s204814
 * @version 1.0
 * @since   2022-06-23*/

public class GuiController extends CPRGuiController implements EKGObserver {
    public Polyline polyline;
    ObservableList<Double> current_point;
    double border = 200.0;
    int cycle = 0;

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
        double x = (ekgData.getTime().getTime()-startTime.getTime())/20.0;
        if (x > 300+300*cycle){
            cycle++;
            polyline.getPoints().clear();
        }
        int y = ekgData.getVoltage()/10;
        Platform.runLater(()-> polyline.getPoints().addAll(x-100*cycle, (double) y));

    }

    public void VisPatientData(ActionEvent actionEvent) {


        List<EkgDTO> resultSet = Resultset.getResultSet();
        startTime = resultSet.get(0).getTime();
        for (EkgDTO ekgData : resultSet) {
            polyline.getPoints().addAll((ekgData.getTime().getTime()-startTime.getTime())/250.0, (double) (ekgData.getVoltage()*100));

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
