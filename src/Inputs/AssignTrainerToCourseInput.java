/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputs;

import static Inputs.AssignStudentToCourseInput.courseID;
import static Inputs.AssignStudentToCourseInput.notEmpty;
import static Menus.AddMenu.addMenu;
import static Menus.AddMenu.addMenuInputInt;
import static Tools.DatabaseConnector.con;
import static Tools.InputRepeater.inputRepeater;
import static Tools.IntRangeChecker.intRangeChecker;
import static Tools.NotEmptyTableChecker.notEmptyTableChecker;
import static Tools.TablePrinter.tablePrinter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static privateschool.PrivateSchool.con1;

/**
 *
 * @author omiro
 */
public class AssignTrainerToCourseInput {

    static ArrayList<Integer> realIndeces;
    static ArrayList<Integer> realIndeces2;

    static public void assignTrainerToCourseInput() {

        Scanner assignTrainerToCourseInputFinish = new Scanner(System.in);

        if (notEmptyTableChecker("COURSE") == false || notEmptyTableChecker("TRAINER") == false) { // Checks if there are available Courses and Trainers to choose from
            System.out.println("");
            System.out.println("In order to assign a Trainer to a Course,");
            System.out.println("you need to create at least one from each!");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            String assignTrainerToCourseInputFinishString = assignTrainerToCourseInputFinish.nextLine();
            if (assignTrainerToCourseInputFinishString.length() == 0) {
                addMenuInputInt = -1;
                addMenu();
            } else {
                addMenuInputInt = -1;
                addMenu();
            }
        } else {
            notEmpty = true;

        }

        // Print list of Courses
        int assignTrainerToCourse_CourseChoiceInputInt = -1;

        do {
            Scanner assignTrainerToCourse_CourseChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Courses");
            System.out.println("-----------------");

            String query = "SELECT COURSE_ID, COURSE_TITLE, COURSE_STREAM, COURSE_TYPE, COURSE_START_DATE, COURSE_END_DATE FROM COURSE";

            if (notEmpty == true) {
                realIndeces = tablePrinter(query, 0, 0);
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Course you want to assign a Trainer to");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            assignTrainerToCourse_CourseChoiceInputInt = intRangeChecker(0, realIndeces.size(), assignTrainerToCourse_CourseChoiceInput);
            if (assignTrainerToCourse_CourseChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (assignTrainerToCourse_CourseChoiceInputInt < 0 || assignTrainerToCourse_CourseChoiceInputInt > realIndeces.size());

        int workingCourseRealIndex = assignTrainerToCourse_CourseChoiceInputInt - 1;

        courseID = realIndeces.get(workingCourseRealIndex);

        // Print list of Trainers
        int assignTrainerToCourse_TrainerChoiceInputInt = -1;

        do {
            Scanner assignTrainerToCourse_TrainerChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Trainers");
            System.out.println("---------------------------------------------------------------");
            System.out.println("(Trainers who are already assigned to this course are excluded)");
            System.out.println("");
            /////////////
            // Dynamically prints a menu excluding Trainers that are already assigned to the chosen Course
            // In parallel, it creates an Array with the real indices of the printed options
            realIndeces2 = new ArrayList<>();

            String query = "SELECT T.TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME FROM TRAINER T WHERE T.TRAINER_ID NOT IN (SELECT T.TRAINER_ID FROM TRAINER T, COURSE_TRAINER CT WHERE T.TRAINER_ID = CT.TRAINER_ID AND T.TRAINER_ID IS NOT NULL AND CT.COURSE_ID = ?) GROUP BY T.TRAINER_ID";

            realIndeces2 = tablePrinter(query, courseID, 0);

            if (realIndeces2.isEmpty()) {
                System.out.println("No more available Trainers for this Course");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Trainer you want to assign to the selected Course: ");
            System.out.print("Please Select (0-" + realIndeces2.size() + "): ");

            assignTrainerToCourse_TrainerChoiceInputInt = intRangeChecker(0, realIndeces2.size(), assignTrainerToCourse_TrainerChoiceInput);
            if (assignTrainerToCourse_TrainerChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (assignTrainerToCourse_TrainerChoiceInputInt < 0 || assignTrainerToCourse_TrainerChoiceInputInt > realIndeces2.size());

        int trainerID = realIndeces2.get(assignTrainerToCourse_TrainerChoiceInputInt - 1);

        // Add Trainer to the Course
        try {

            con1.checkConnection();
            PreparedStatement pstm = null;
            String addTrainerCourseQr = "INSERT INTO COURSE_TRAINER VALUES (?, ?)";
            pstm = con.prepareStatement(addTrainerCourseQr);
            pstm.setInt(1, courseID);
            pstm.setInt(2, trainerID);

            pstm.executeUpdate();

            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ask if users wants to repeat last action
        inputRepeater(addMenuInputInt);

    }

}
