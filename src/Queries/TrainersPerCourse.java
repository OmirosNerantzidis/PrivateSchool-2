/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queries;

import static Menus.QueriesMenu.queriesMenu;
import static Tools.IntRangeChecker.intRangeChecker;
import static Tools.NotEmptyTableChecker.notEmptyTableChecker;
import static Tools.TablePrinter.tablePrinter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author omiro
 */
public class TrainersPerCourse {

    static ArrayList<Integer> realIndeces;

    public static void trainersPerCourse() {

        Scanner trainersPerCourseInputFinish = new Scanner(System.in);

        // Print list of Courses
        if (notEmptyTableChecker("COURSE") == false) { // Checks if there are available Courses to choose from
            System.out.println("");
            System.out.println("");
            System.out.println("There are no Courses available to show trainers for.");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            System.out.println("");
            String trainersPerCourseString = trainersPerCourseInputFinish.nextLine();
            if (trainersPerCourseString.length() == 0) {
                Menus.QueriesMenu.queriesMenu();
            } else {
                Menus.QueriesMenu.queriesMenu();
            }
        }
        int trainersCourseChoiceInputInt = -1;

        do {
            Scanner trainersCourseChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("");
            System.out.println("Available Courses with at least 1 trainer");
            System.out.println("--------------------------------------------");

            String query = "SELECT CT.COURSE_ID, C.COURSE_TITLE FROM COURSE_TRAINER CT, COURSE C WHERE CT.COURSE_ID = C.COURSE_ID GROUP BY CT.COURSE_ID";

            // Dynamically prints a menu excluding Courses that don't have trainers
            // In parallel, it creates an Array with the real indices of the printed options
            realIndeces = tablePrinter(query, 0, 0);

            if (realIndeces.isEmpty()) {
                System.out.println("None of the Courses have trainers set");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Course you want to see its Trainers");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            trainersCourseChoiceInputInt = intRangeChecker(0, realIndeces.size(), trainersCourseChoiceInput);
            if (trainersCourseChoiceInputInt == 0) {
                queriesMenu();
            }

        } while (trainersCourseChoiceInputInt < 0 || trainersCourseChoiceInputInt > realIndeces.size());

        int workingCourseRealIndex = realIndeces.get(trainersCourseChoiceInputInt - 1);

        System.out.println("");
        System.out.println("Trainers of selected Course: ");
        System.out.println("-----------------------------");
        System.out.println("");
        System.out.println("Trainer Name");
        System.out.println("------------");

        String query = "SELECT T.TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME FROM TRAINER T, COURSE_TRAINER CT WHERE T.TRAINER_ID = CT.TRAINER_ID AND CT.COURSE_ID = ?";

        tablePrinter(query, workingCourseRealIndex, 0);

        System.out.println("");
        System.out.println("Press Enter to go to the previous menu");
        System.out.println("");
        System.out.println("");
        String trainersPerCourseString = trainersPerCourseInputFinish.nextLine();
        if (trainersPerCourseString.length() == 0) {
            Menus.QueriesMenu.queriesMenu();
        } else {
            Menus.QueriesMenu.queriesMenu();
        }
    }
}
