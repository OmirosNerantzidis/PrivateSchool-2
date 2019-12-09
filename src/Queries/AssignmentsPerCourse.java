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
public class AssignmentsPerCourse {

    static ArrayList<Integer> realIndeces;

    public static void assignmentPerCourse() {

        Scanner assignmentsPerCourseInputFinish = new Scanner(System.in);

        // Print list of Courses
        if (notEmptyTableChecker("COURSE") == false) { // Checks if there are available Courses to choose from
            System.out.println("");
            System.out.println("");
            System.out.println("There are no Courses available to show assignments for.");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            System.out.println("");
            String assignmentsPerCourseString = assignmentsPerCourseInputFinish.nextLine();
            if (assignmentsPerCourseString.length() == 0) {
                Menus.QueriesMenu.queriesMenu();
            } else {
                Menus.QueriesMenu.queriesMenu();
            }
        }
        int assignmentCourseChoiceInputInt = -1;

        do {
            Scanner assignmentCourseChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("");
            System.out.println("Available Courses with at least 1 assignment");
            System.out.println("--------------------------------------------");

            String query = "SELECT A.COURSE_ID, COURSE_TITLE FROM ASSIGNMENT A, COURSE C WHERE A.COURSE_ID = C.COURSE_ID GROUP BY A.COURSE_ID";

            // Dynamically prints a menu excluding Courses that don't have assignments
            // In parallel, it creates an Array with the real indices of the printed options
            realIndeces = tablePrinter(query, 0, 0);

            if (realIndeces.isEmpty()) {
                System.out.println("None of the Courses have assignments set");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Course you want to see its Assignments");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            assignmentCourseChoiceInputInt = intRangeChecker(0, realIndeces.size(), assignmentCourseChoiceInput);
            if (assignmentCourseChoiceInputInt == 0) {
                queriesMenu();
            }

        } while (assignmentCourseChoiceInputInt < 0 || assignmentCourseChoiceInputInt > realIndeces.size());

        int workingCourseRealIndex = realIndeces.get(assignmentCourseChoiceInputInt - 1);

        System.out.println("");
        System.out.println("Assignments of selected Course: ");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("Title / Description / Submission Date");
        System.out.println("----------------------------------------------");

        String query = "SELECT ASSIGNMENT_ID, ASSIGNMENT_TITLE, ASSIGNMENT_DESCRIPTION, ASSIGNMENT_SUBMISSION_DATE FROM ASSIGNMENT WHERE COURSE_ID = ?";

        tablePrinter(query, workingCourseRealIndex, 0);

        System.out.println("");
        System.out.println("Press Enter to go to the previous menu");
        System.out.println("");
        System.out.println("");
        String assignmentsPerCourseString = assignmentsPerCourseInputFinish.nextLine();
        if (assignmentsPerCourseString.length() == 0) {
            Menus.QueriesMenu.queriesMenu();
        } else {
            Menus.QueriesMenu.queriesMenu();
        }
    }
}
