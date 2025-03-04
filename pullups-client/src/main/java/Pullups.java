import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pullups extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loaderMain = new FXMLLoader(Pullups.class.getResource("demon/common/view/main-view.fxml"));
        Scene sceneMain = new Scene(loaderMain.load());
        primaryStage.setTitle("Pullups");
        primaryStage.setScene(sceneMain);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
