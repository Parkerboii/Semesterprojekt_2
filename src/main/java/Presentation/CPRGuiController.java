package Presentation;

import Data.PatientDAO;
import Data.PatientDTO;
import Data.SQLImplementation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CPRGuiController {
    @FXML
    public javafx.scene.control.TextField CPRinput;
    public javafx.scene.control.TextField firstNameText;
    public javafx.scene.control.TextField lastNameText;
    @FXML
    javafx.scene.control.Label fillAll;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private PatientDAO patientDAO = (PatientDAO) new SQLImplementation();
    private boolean checkEmpty(javafx.scene.control.TextField toCheck){
        return !toCheck.getText().trim().isEmpty();
    }

    public void recordCPR(javafx.event.ActionEvent actionEvent) throws IOException {
        if (checkEmpty(CPRinput) && checkEmpty(firstNameText) && checkEmpty(lastNameText)) {
            String CPRnumber = CPRinput.getText();
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();

            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setCPR(CPRnumber);
            patientDTO.setFirstName(firstName);
            patientDTO.setLastName(lastName);
            patientDAO.save(patientDTO);



            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui.fxml"));
            root = loader.load();
            GuiController guiController = loader.getController();
            guiController.setCurrentCPR(CPRnumber);
            guiController.displayCpr(CPRnumber);


            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            fillAll.setText("All fields are required");
        }
    }

}


