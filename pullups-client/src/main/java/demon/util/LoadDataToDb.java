package demon.util;

import demon.entity.Day;
import demon.entity.Program;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class LoadDataToDb {
    public void load() {
        loadProgram("9_11", program_9_11);
        loadProgram("12_15", program_12_15);
        loadProgram("16_20", program_16_20);
        loadProgram("21_25", program_21_25);
        loadProgram("26_30", program_26_30);
        loadProgram("31_35", program_31_35);
    }

    private final String[][] program_9_11 = {
            {"1", "3", "5", "3", "3", "5", "1"},
            {"2", "4", "6", "4", "4", "6", "1"},
            {"3", "5", "7", "5", "5", "6", "2"},
            {"4", "5", "8", "5", "5", "8", "1"},
            {"5", "6", "9", "6", "6", "8", "1"},
            {"6", "6", "9", "6", "6", "10", "2"}
    };

    private final String[][] program_12_15 = {
            {"1", "6", "8", "6", "6", "8", "1"},
            {"2", "6", "9", "6", "6", "9", "1"},
            {"3", "7", "10", "6", "6", "9", "2"},
            {"4", "7", "10", "7", "7", "10", "1"},
            {"5", "8", "11", "8", "8", "10", "1"},
            {"6", "9", "11", "9", "9", "11", "2"}
    };

    private final String[][] program_16_20 = {
            {"1", "8", "11", "8", "8", "10", "1"},
            {"2", "9", "12", "9", "9", "11", "1"},
            {"3", "9", "13", "9", "9", "12", "2"},
            {"4", "10", "14", "10", "10", "13", "1"},
            {"5", "11", "15", "10", "10", "13", "1"},
            {"6", "11", "15", "11", "11", "13", "2"},
            {"7", "12", "16", "11", "11", "15", "1"},
            {"8", "12", "16", "12", "12", "16", "1"},
            {"9", "13", "17", "13", "13", "16", "2"}
    };

    private final String[][] program_21_25 = {
            {"1", "12", "16", "12", "12", "15", "1"},
            {"2", "13", "16", "12", "12", "16", "1"},
            {"3", "13", "17", "13", "13", "16", "2"},
            {"4", "14", "19", "13", "13", "18", "1"},
            {"5", "14", "19", "14", "14", "19", "1"},
            {"6", "15", "20", "14", "14", "20", "2"},
            {"7", "16", "20", "16", "16", "20", "1"},
            {"8", "16", "21", "16", "16", "20", "1"},
            {"9", "17", "22", "16", "16", "21", "2"}
    };

    private final String[][] program_26_30 = {
            {"1", "16", "18", "15", "15", "17", "1"},
            {"2", "16", "20", "16", "16", "19", "1"},
            {"3", "17", "21", "16", "16", "20", "2"},
            {"4", "17", "22", "17", "17", "22", "1"},
            {"5", "18", "23", "18", "18", "22", "1"},
            {"6", "19", "25", "18", "18", "24", "2"},
            {"7", "19", "26", "18", "18", "25", "1"},
            {"8", "19", "27", "19", "19", "26", "1"},
            {"9", "20", "28", "20", "20", "28", "2"}
    };

    private final String[][] program_31_35 = {
            {"1", "20", "25", "19", "19", "23", "1"},
            {"2", "22", "25", "21", "21", "25", "1"},
            {"3", "23", "26", "23", "23", "25", "2"},
            {"4", "24", "27", "24", "24", "26", "1"},
            {"5", "25", "28", "24", "24", "27", "1"},
            {"6", "25", "29", "25", "25", "28", "2"},
            {"7", "26", "29", "25", "25", "29", "1"},
            {"8", "26", "30", "26", "26", "30", "1"},
            {"9", "26", "32", "26", "26", "32", "2"}
    };

    private void loadProgram(String nameProgram, String[][] daysLoad) {
        List<Day> days = new ArrayList<>();
        for (String[] dayLoad : daysLoad) {
            Day day = new Day();
            day.setNameDay(dayLoad[0]);
            day.setTouch1(dayLoad[1]);
            day.setTouch2(dayLoad[2]);
            day.setTouch3(dayLoad[3]);
            day.setTouch4(dayLoad[4]);
            day.setTouch5(dayLoad[5]);
            day.setRestTime(dayLoad[6]);

            days.add(day);
        }

        Program program = new Program();
        program.setNameProgram(nameProgram);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            for (Day day : days) {
                session.persist(day);
            }
            program.setDays(days);
            session.persist(program);
            session.getTransaction().commit();
        }
    }

//    private static void loadStartData() {
//        Day day = new Day();
//        day.setNameDay(1);
//        day.setRestTime("Минимум 1 день перерыва");
//        day.setTouch1("3 раза");
//        day.setTouch2("5 раз");
//        day.setTouch3("3 раза");
//        day.setTouch4("3 раза");
//        day.setTouch5("Макс (миниму 5 раз)");
//
//        List<Day> days = new ArrayList<>();
//        days.add(day);
//
//        Program program = new Program();
//        program.setNameProgram("9-11");
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            session.persist(day);
//            program.setDays(days);
//            session.persist(program);
//            session.getTransaction().commit();
//        }
//    }
}
