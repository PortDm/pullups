package demon.common.controller;

import demon.entity.Day;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;


import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DayController {

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
    @FXML
    private Label labelDay;
    @FXML
    private Label labelCount;
    @FXML
    private Label labelStep;
    @FXML
    private Label labelStepLabel;
    @FXML
    private AnchorPane apDay;

    private final int COUNT_MINUTE = 1;
    private final int COUNT_SECOND = 59;

    private int step = 0;
    private int timeSecond;
    private int timeMinute;
    private boolean isRunning = false;
    private String nameProgram;

    public void initialize() {
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
            labelStep.setVisible(true);
            labelStepLabel.setVisible(true);
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
            labelStep.setVisible(false);
            labelStepLabel.setVisible(false);

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
                labelStep.setText(String.valueOf(step));
                break;
            case 1:
                labelTouch2.setStyle("-fx-font-size: 26");
                labelTouch1.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                labelStep.setText(String.valueOf(step));
                break;
            case 2:
                labelTouch3.setStyle("-fx-font-size: 26");
                labelTouch2.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                labelStep.setText(String.valueOf(step));
                break;
            case 3:
                labelTouch4.setStyle("-fx-font-size: 26");
                labelTouch3.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                labelStep.setText(String.valueOf(step));
                break;
            case 4:
                labelTouch5.setStyle("-fx-font-size: 26");
                labelTouch4.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                labelStep.setText(String.valueOf(step));
                break;
            case 5:
                labelTouch5.setStyle("-fx-font-size: 13; -fx-text-fill: green;");
                labelRestTime.setStyle("-fx-font-size: 26");
                btnStart.setDisable(true);
                labelExecute.setText("Молодец");
                labelStep.setVisible(false);
                labelStepLabel.setVisible(false);
        }
    }

    private void setTimerLabel() {
        String timeSecondStr = timeSecond < 10 ? "0" + timeSecond : String.valueOf(timeSecond);
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
                labelStep.setVisible(true);
                labelStepLabel.setVisible(true);
                Toolkit.getDefaultToolkit().beep();
            } else {
                timeMinute--;
                timeSecond = COUNT_SECOND;
            }
        } else {
            timeSecond--;
        }
    }

    private void setProgressBar() {
        int leftSecond = ((timeMinute * (COUNT_SECOND + 1)) + timeSecond);
        int countSecondAll = (COUNT_MINUTE + 1) * (COUNT_SECOND + 1);
        pbTimer.setProgress((double) leftSecond / countSecondAll);
    }


    public void setTouches(Day day) {
        labelDay.setText(day.getNameDay());
        labelTouch1.setText(day.getTouch1());
        labelTouch2.setText(day.getTouch2());
        labelTouch3.setText(day.getTouch3());
        labelTouch4.setText(day.getTouch4());
        labelTouch5.setText(day.getTouch5());
        labelRestTime.setText(String.valueOf(day.getRestTime()));
        calcCount(day);
        nameProgram = day.getProgram().getNameProgram();
    }

    private void calcCount(Day day) {
        int count = Integer.parseInt(day.getTouch1()) +
                Integer.parseInt(day.getTouch2()) +
                Integer.parseInt(day.getTouch3()) +
                Integer.parseInt(day.getTouch4()) +
                Integer.parseInt(day.getTouch5());
        labelCount.setText(String.valueOf(count));
    }

    public void onBtnBack() throws IOException {
        AnchorPane apWrap = (AnchorPane) apDay.getParent();
        FXMLLoader loaderDayChooser = new FXMLLoader(ProgramChooseController.class.getResource("/demon/common/view/day-choose-view.fxml"));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderDayChooser.load());

        DayChooseController dayChooserController = loaderDayChooser.getController();
        dayChooserController.setNameProgram(nameProgram);
        dayChooserController.loadDbProgramByName();
    }
}
