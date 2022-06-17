package Presentation;

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
    private Stage stage;
    private Scene scene;
    private Parent root;


//TODO: Få GUI til at virke
        public void recordCPR(javafx.event.ActionEvent actionEvent) throws IOException {
            if (CPRinput != null) {
                String CPRnumber = CPRinput.getText();
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui.fxml"));
                root = loader.load();
                GuiController guiController = loader.getController();
                guiController.setCurrentCPR(CPRnumber);
                guiController.displayCpr(CPRnumber);
                guiController.setCurrentFirstName(firstName);
                guiController.setCurrentLastName(lastName);


                stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
    }
}


