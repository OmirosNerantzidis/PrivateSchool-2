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
public class StudentsPerCourse {

    static ArrayList<Integer> realIndeces;

    public static void studentsPerCourse() {

        Scanner studentsPerCourseInputFinish = new Scanner(System.in);

        // Print list of Courses
        if (notEmptyTableChecker("COURSE") == false) { // Checks if there are available Courses to choose from
            System.out.println("");
            System.out.println("");
            System.out.println("There are no Courses available to show students for.");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            System.out.println("");
            String studentsPerCourseString = studentsPerCourseInputFinish.nextLine();
            if (studentsPerCourseString.length() == 0) {
                Menus.QueriesMenu.queriesMenu();
            } else {
                Menus.QueriesMenu.queriesMenu();
            }
        }
        int studentsCourseChoiceInputInt = -1;

        do {
            Scanner studentsCourseChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("");
            System.out.println("Available Courses with at least 1 student");
            System.out.println("--------------------------------------------");

            String query = "SELECT CS.COURSE_ID, C.COURSE_TITLE FROM COURSE_STUDENT CS, COURSE C WHERE CS.COURSE_ID = C.COURSE_ID GROUP BY CS.COURSE_ID";

            // Dynamically prints a menu excluding Courses that don't have Students
            // In parallel, it creates an Array with the real indices of the printed options
            realIndeces = tablePrinter(query, 0, 0);

            if (realIndeces.isEmpty()) {
                System.out.println("None of the Courses have students set");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Course you want to see its Students");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            studentsCourseChoiceInputInt = intRangeChecker(0, realIndeces.size(), studentsCourseChoiceInput);
            if (studentsCourseChoiceInputInt == 0) {
                queriesMenu();
            }

        } while (studentsCourseChoiceInputInt < 0 || studentsCourseChoiceInputInt > realIndeces.size());

        int workingCourseRealIndex = realIndeces.get(studentsCourseChoiceInputInt - 1);

        System.out.println("");
        System.out.println("Students of Selected Course: ");
        System.out.println("-----------------------------");
        System.out.println("");
        System.out.println("Student Name");
        System.out.println("------------");

        String query = "SELECT S.STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME FROM STUDENT S, COURSE_STUDENT CS WHERE S.STUDENT_ID = CS.STUDENT_ID AND CS.COURSE_ID = ?";

        tablePrinter(query, workingCourseRealIndex, 0);

        System.out.println("");
        System.out.println("Press Enter to go to the previous menu");
        System.out.println("");
        System.out.println("");
        String studentsPerCourseString = studentsPerCourseInputFinish.nextLine();
        if (studentsPerCourseString.length() == 0) {
            Menus.QueriesMenu.queriesMenu();
        } else {
            Menus.QueriesMenu.queriesMenu();
        }
    }
}
