package demon.common.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RulesTestController {
    @FXML
    private AnchorPane apRulesTest;

    public void onBtnBack() throws IOException {
        AnchorPane apWrap = (AnchorPane) apRulesTest.getParent();
        FXMLLoader loaderDay = new FXMLLoader(DayChooseController.class.getResource("/demon/common/view/program-choose-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDay.load());
    }
}
