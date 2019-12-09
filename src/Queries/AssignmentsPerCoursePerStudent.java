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
public class AssignmentsPerCoursePerStudent {

    static ArrayList<Integer> realIndeces;
    static ArrayList<Integer> realIndeces2;

    public static void assignmentsPerCoursePerStudent() {

        Scanner assignmentsPerCoursePerStudentInputFinish = new Scanner(System.in);

        int assignmentsStudentChoiceInputInt = -1;
        int assignmentsCourseChoiceInputInt = -1;

        // Print list of Students
        if (notEmptyTableChecker("STUDENT") == false) { // Checks if there are available Students to choose from
            System.out.println("");
            System.out.println("");
            System.out.println("There are no Students available to show assignments for.");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            System.out.println("");
            String assignmentsPerCoursePerStudentString = assignmentsPerCoursePerStudentInputFinish.nextLine();
            if (assignmentsPerCoursePerStudentString.length() == 0) {
                Menus.QueriesMenu.queriesMenu();
            } else {
                Menus.QueriesMenu.queriesMenu();
            }
        }

        do {
            Scanner assignmentsStudentChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("");
            System.out.println("Available Students with at least 1 assignment");
            System.out.println("--------------------------------------------");

            String query = "SELECT SA.STUDENT_ID, S.STUDENT_FIRST_NAME, S.STUDENT_LAST_NAME FROM STUDENT_ASSIGNMENT SA, STUDENT S WHERE SA.STUDENT_ID = S.STUDENT_ID GROUP BY SA.STUDENT_ID";

            // Dynamically prints a menu excluding Students that don't have assignments
            // In parallel, it creates an Array with the real indices of the printed options
            realIndeces = tablePrinter(query, 0, 0);

            if (realIndeces.isEmpty()) {
                System.out.println("None of the Students have assignments set");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Student you want to see his/her Assignments");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            assignmentsStudentChoiceInputInt = intRangeChecker(0, realIndeces.size(), assignmentsStudentChoiceInput);
            if (assignmentsStudentChoiceInputInt == 0) {
                queriesMenu();
            }

        } while (assignmentsStudentChoiceInputInt < 0 || assignmentsStudentChoiceInputInt > realIndeces.size());

        int workingStudentRealIndex = realIndeces.get(assignmentsStudentChoiceInputInt - 1);

        do {
            Scanner assignmentsCourseChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Courses of selected Student that have assignments: ");
            System.out.println("---------------------------------------------------");
            System.out.println("");
            System.out.println("Title");
            System.out.println("-----");

            String query = "SELECT A.COURSE_ID, C.COURSE_TITLE FROM STUDENT_ASSIGNMENT SA, ASSIGNMENT A, COURSE C WHERE C.COURSE_ID = A.COURSE_ID AND A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID AND SA.STUDENT_ID = ? GROUP BY A.COURSE_ID";

            realIndeces2 = tablePrinter(query, workingStudentRealIndex, 0);

            if (realIndeces2.isEmpty()) {
                System.out.println("No Courses of this Student have any Assignments");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Course you want to see Assignments/Marks for the selected Student");
            System.out.print("Please Select (0-" + realIndeces2.size() + "): ");

            assignmentsCourseChoiceInputInt = intRangeChecker(0, realIndeces2.size(), assignmentsCourseChoiceInput);
            if (assignmentsCourseChoiceInputInt == 0) {
                queriesMenu();
            }

        } while (assignmentsCourseChoiceInputInt < 0 || assignmentsCourseChoiceInputInt > realIndeces2.size());

        int workingCourseRealIndex = realIndeces2.get(assignmentsCourseChoiceInputInt - 1);

        System.out.println("");
        System.out.println("Assignments of selected Student & Course: ");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.println("Title / Oral Mark / Total Mark");
        System.out.println("--------(Null = No Mark)------");

        String query = "SELECT A.ASSIGNMENT_ID, A.ASSIGNMENT_TITLE, SA.STUDENT_ASSIGNMENT_ORAL_MARK, SA.STUDENT_ASSIGNMENT_TOTAL_MARK FROM STUDENT_ASSIGNMENT SA, ASSIGNMENT A WHERE A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID AND SA.STUDENT_ID = ? AND A.COURSE_ID = ?";

        System.out.println("");
        tablePrinter(query, workingStudentRealIndex, workingCourseRealIndex);

        System.out.println("");
        System.out.println("Press Enter to go to the previous menu");
        System.out.println("");
        System.out.println("");
        String assignmentsPerStudentString = assignmentsPerCoursePerStudentInputFinish.nextLine();
        if (assignmentsPerStudentString.length() == 0) {
            Menus.QueriesMenu.queriesMenu();
        } else {
            Menus.QueriesMenu.queriesMenu();
        }
    }
}
