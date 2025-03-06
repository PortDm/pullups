package demon.common.controller;

import demon.entity.Day;
import demon.entity.Program;
import demon.util.HibernateUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    @FXML
    private Label labelExecute;
    @FXML
    private Label labelTouch1;
    @FXML
    private Label labelTouch2;
    @FXML
    private Label labelTouch3;
    @FXML
    private Label labelTouch4;
    @FXML
    private Label labelTouch5;
    @FXML
    private Label labelRestTime;

    @FXML
    private Label labelSecond;
    @FXML
    private Label labelMinute;
    @FXML
    private Button btnStart;
    @FXML
    private ProgressBar pbTimer;


    private int step = 0;
    private final int COUNT_SECOND = 2;
    private final int COUNT_MINUTE = 1;

    private int timeSecond;
    private int timeMinute;
    private boolean isRunning = false;

    public void initialize() {
        loadStartData();
        loadFromDbDay();
        setTouches();
        stepping();
        pbTimer.setProgress(1);

    }

    private Timer timerOneSecond;

    public void onBtnStart() {
        if (!isRunning) {
            resting();
            stepping();
        } else {
            timerOneSecond.cancel();
            isRunning = false;
            btnStart.setText("Выполнил");
            labelExecute.setText("Выполняй");
        }
    }

    private void resting() {
        if (step < 5) {
            timeSecond = 0;
            timeMinute = COUNT_MINUTE + 1;
            timerOneSecond = new Timer();
            isRunning = true;
            btnStart.setText("Стоп");
            labelExecute.setText("Отдыхай");

            timerOneSecond.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        setTimerLabel();
                    });
                }
            }, 0, 1000);
        }
    }

    private void stepping() {
        switch (step++) {
            case 0:
                labelTouch1.setStyle("-fx-font-size: 26");
                break;
            case 1:
                labelTouch2.setStyle("-fx-font-size: 26");
                labelTouch1.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                break;
            case 2:
                labelTouch3.setStyle("-fx-font-size: 26");
                labelTouch2.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                break;
            case 3:
                labelTouch4.setStyle("-fx-font-size: 26");
                labelTouch3.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                break;
            case 4:
                labelTouch5.setStyle("-fx-font-size: 26");
                labelTouch4.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                break;
            case 5:
                labelTouch5.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                labelRestTime.setStyle("-fx-font-size: 26");
                btnStart.setDisable(true);
                labelExecute.setText("Молодец");
        }
    }

    private void setTimerLabel() {
        String timeSecondStr = timeSecond < 10 ? "0" +timeSecond : String.valueOf(timeSecond);
        String timeMinuteStr = timeMinute < 10 ? "0" + timeMinute : String.valueOf(timeMinute);
        labelSecond.setText(timeSecondStr);
        labelMinute.setText(timeMinuteStr);

        setProgressBar();

        if (timeSecond <= 0) {
            if (timeMinute <= 0) {
                timerOneSecond.cancel();
                isRunning = false;
                btnStart.setText("Выполнил");
                labelExecute.setText("Выполняй");
            } else {
                timeMinute--;
                timeSecond = COUNT_SECOND;
            }
        } else {
            timeSecond--;
        }
    }

    private void setProgressBar() {
        int leftSecond = ((timeMinute * (COUNT_SECOND+1)) + timeSecond);
        int countSecondAll = (COUNT_MINUTE + 1) * (COUNT_SECOND + 1);
        pbTimer.setProgress((double) leftSecond / countSecondAll);
    }

    private Day day;

    private void loadFromDbDay() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Program> queryProgram = session.createQuery("from Program where nameProgram = '9-11 подтягиваний'", Program.class);
            Program program = queryProgram.getSingleResult();
            day = program.getDays().getFirst();
            session.getTransaction().commit();
        }
    }

    private void setTouches() {
        labelTouch1.setText("Подход 1:  " + day.getTouch1());
        labelTouch2.setText("Подход 2:  " + day.getTouch2());
        labelTouch3.setText("Подход 3:  " + day.getTouch3());
        labelTouch4.setText("Подход 4:  " + day.getTouch4());
        labelTouch5.setText("Подход 5:  " + day.getTouch5());
        labelRestTime.setText(String.valueOf(day.getRestTime()));
    }

    private static void loadStartData() {
        Day day = new Day();
        day.setNameDay(1);
        day.setRestTime("Минимум 1 день перерыва");
        day.setTouch1("3 раза");
        day.setTouch2("5 раз");
        day.setTouch3("3 раза");
        day.setTouch4("3 раза");
        day.setTouch5("миниму 5 раз");

        List<Day> days = new ArrayList<>();
        days.add(day);

        Program program = new Program();
        program.setNameProgram("9-11 подтягиваний");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(day);
            program.setDays(days);
            session.persist(program);
            session.getTransaction().commit();
        }
    }
}
