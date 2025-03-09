package demon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Pullups extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loaderWrap = new FXMLLoader(Pullups.class.getResource("/demon/common/view/wrap-view.fxml"));
        AnchorPane pane = (AnchorPane) loaderWrap.load();
        Scene sceneWrap = new Scene(pane);
        primaryStage.setTitle("35 подтягиваний");
        primaryStage.setResizable(false);
        primaryStage.setScene(sceneWrap);

        AnchorPane apWrap = (AnchorPane) pane.getChildren()
                .filtered(node -> Objects.equals(node.getId(), "apWrap"))
                .getFirst();
        FXMLLoader loaderProgram = new FXMLLoader(Pullups.class.getResource("/demon/common/view/program-choose-view.fxml"));

        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderProgram.load());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }


}
