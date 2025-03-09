package demon.common.controller;

import demon.entity.Day;
import demon.entity.Program;
import demon.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.Objects;

@Setter
public class DayChooseController {

    @FXML
    private AnchorPane apDayChoose;
    @FXML
    private Button btnDay7;
    @FXML
    private Button btnDay8;
    @FXML
    private Button btnDay9;

    @FXML
    private void initialize() {

    }

    private Program program;
    private String nameProgram;

    public void onBtnDay1() throws IOException {
        Day day1 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "1")).toList().getFirst();;
        showDay(day1);
    }

    public void onBtnDay2() throws IOException {
        Day day2 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "2")).toList().getFirst();;
        showDay(day2);
    }

    public void onBtnDay3() throws IOException {
        Day day3 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "3")).toList().getFirst();;
        showDay(day3);
    }

    public void onBtnDay4() throws IOException {
        Day day4 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "4")).toList().getFirst();;
        showDay(day4);
    }

    public void onBtnDay5() throws IOException {
        Day day5 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "5")).toList().getFirst();;
        showDay(day5);
    }

    public void onBtnDay6() throws IOException {
        Day day6 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "6")).toList().getFirst();;
        showDay(day6);
    }

    public void onBtnDay7() throws IOException {
        Day day7 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "7")).toList().getFirst();;
        showDay(day7);
    }

    public void onBtnDay8() throws IOException {
        Day day8 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "8")).toList().getFirst();;
        showDay(day8);
    }

    public void onBtnDay9() throws IOException {
        Day day9 = program.getDays().stream().filter(day -> Objects.equals(day.getNameDay(), "9")).toList().getFirst();;
        showDay(day9);
    }

    public void onBtnBack() throws IOException {
        AnchorPane apWrap = (AnchorPane) apDayChoose.getParent();
        FXMLLoader loaderDayChooser = new FXMLLoader(ProgramChooseController.class.getResource("/demon/common/view/program-choose-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDayChooser.load());
    }

    public void loadDbProgramByName() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Program where nameProgram = :nameProgram");
            query.setParameter("nameProgram", nameProgram);
            program = (Program) query.uniqueResult();
            session.getTransaction().commit();

            if (program.getDays().size() < 7) {
                btnDay7.setDisable(true);
                btnDay8.setDisable(true);
                btnDay9.setDisable(true);
            }
        }
    }

    private void showDay(Day day) throws IOException {
        AnchorPane apWrap = (AnchorPane) apDayChoose.getParent();
        FXMLLoader loaderDay = new FXMLLoader(DayChooseController.class.getResource("/demon/common/view/day-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDay.load());

        DayController dayController = loaderDay.getController();
        dayController.setTouches(day);
    }
}
