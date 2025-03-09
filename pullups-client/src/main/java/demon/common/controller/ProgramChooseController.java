package demon.common.controller;

import demon.util.LoadDataToDb;
import demon.entity.Program;
import demon.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import java.io.IOException;

public class ProgramChooseController {

    @FXML
    private AnchorPane apProgram;

    @FXML
    private void initialize() {
        if(!existDb()) {
            LoadDataToDb loadDataToDb = new LoadDataToDb();
            loadDataToDb.load();
        }
    }

    private boolean existDb() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Program result = session.get(Program.class, 1);
            return result != null;
        }
    }

    public void onBtn_9_11() throws IOException {
        showProgram("9_11");
    }

    public void onBtn_12_15() throws IOException {
        showProgram("12_15");
    }

    public void onBtn_16_20() throws IOException {
        showProgram("16_20");
    }

    public void onBtn_21_25() throws IOException {
        showProgram("21_25");
    }

    public void onBtn_26_30() throws IOException {
        showProgram("26_30");
    }

    public void onBtn_31_35() throws IOException {
        showProgram("31_35");
    }

    private void showProgram(String nameProgram) throws IOException {
        AnchorPane apWrap = (AnchorPane) apProgram.getParent();
        FXMLLoader loaderDayChooser = new FXMLLoader(ProgramChooseController.class.getResource("/demon/common/view/day-choose-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDayChooser.load());

        DayChooseController dayChooserController = loaderDayChooser.getController();
        dayChooserController.setNameProgram(nameProgram);
        dayChooserController.loadDbProgramByName();
    }

    public void onBtnRules() throws IOException {
        AnchorPane apWrap = (AnchorPane) apProgram.getParent();
        FXMLLoader loaderDayChooser = new FXMLLoader(ProgramChooseController.class.getResource("/demon/common/view/rules-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDayChooser.load());
    }


    public void onBtnRulesTest() throws IOException {
        AnchorPane apWrap = (AnchorPane) apProgram.getParent();
        FXMLLoader loaderDayChooser = new FXMLLoader(ProgramChooseController.class.getResource("/demon/common/view/rules-test-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDayChooser.load());
    }


    public void onBtnAbout() throws IOException {
        AnchorPane apWrap = (AnchorPane) apProgram.getParent();
        FXMLLoader loaderDayChooser = new FXMLLoader(ProgramChooseController.class.getResource("/demon/common/view/about-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDayChooser.load());
    }



}
