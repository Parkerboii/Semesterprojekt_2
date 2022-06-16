package Presentation;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class CPRGuiController {
    public String CPRnumber;
    @FXML
    private TextField textField;
//TODO: Få GUI til at virke
    public void recordCPR(MouseEvent mouseEvent) {
        if (textField != null){
            CPRnumber = textField.getText();
            textField = null;
        }
    }
}
