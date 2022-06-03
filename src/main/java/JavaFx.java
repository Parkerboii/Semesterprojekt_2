import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;



public class JavaFx extends Application {
    public static void run() {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui.fxml"));
        FlowPane pane = loader.load();
        Scene scene = new Scene(pane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
