/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputs;

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
public class AssignStudentToCourseInput {

    static ArrayList<Integer> realIndeces;
    static ArrayList<Integer> realIndeces2;
    static ArrayList<Integer> assignmentIDs;
    static int courseID = -1;
    static boolean notEmpty;

    static public void assignStudentToCourseInput() {

        Scanner assignStudentToCourseInputFinish = new Scanner(System.in);

        if (notEmptyTableChecker("COURSE") == false || notEmptyTableChecker("STUDENT") == false) { // Checks if there are available Courses and Students to choose from
            System.out.println("");
            System.out.println("In order to assign a Student to a Course,");
            System.out.println("you need to create at least one from each!");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            String assignStudentToCourseInputFinishString = assignStudentToCourseInputFinish.nextLine();
            if (assignStudentToCourseInputFinishString.length() == 0) {
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
        int assignStudentToCourse_CourseChoiceInputInt = -1;

        do {

            Scanner assignStudentToCourse_CourseChoiceInput = new Scanner(System.in);

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
            System.out.println("Choose the number of the Course you want to assign a Student to");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            assignStudentToCourse_CourseChoiceInputInt = intRangeChecker(0, realIndeces.size(), assignStudentToCourse_CourseChoiceInput);

            if (assignStudentToCourse_CourseChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (assignStudentToCourse_CourseChoiceInputInt < 0 || assignStudentToCourse_CourseChoiceInputInt > realIndeces.size());

        int workingCourseRealIndex = assignStudentToCourse_CourseChoiceInputInt - 1;

        courseID = realIndeces.get(workingCourseRealIndex);

        // Print list of Students
        int assignStudentToCourse_StudentChoiceInputInt = -1;

        do {

            Scanner assignStudentToCourse_StudentChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Students");
            System.out.println("---------------------------------------------------------------");
            System.out.println("(Students who are already assigned to this course are excluded)");
            System.out.println("");
            /////////////
            // Dynamically prints a menu excluding Students that are already assigned to the chosen Course
            // In parallel, it creates an Array with the real indices of the printed options

            String query = "SELECT S.STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME FROM STUDENT S WHERE S.STUDENT_ID NOT IN (SELECT S.STUDENT_ID FROM STUDENT S, COURSE_STUDENT CS WHERE S.STUDENT_ID = CS.STUDENT_ID AND CS.COURSE_ID = ?) GROUP BY S.STUDENT_ID";

            realIndeces2 = tablePrinter(query, courseID, 0);

            if (realIndeces2.isEmpty()) {
                System.out.println("");
                System.out.println("No more available Students for this Course");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Student you want to assign to the course: ");
            System.out.print("Please Select (0-" + realIndeces2.size() + "): ");

            assignStudentToCourse_StudentChoiceInputInt = intRangeChecker(0, realIndeces2.size(), assignStudentToCourse_StudentChoiceInput);

            if (assignStudentToCourse_StudentChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (assignStudentToCourse_StudentChoiceInputInt < 0 || assignStudentToCourse_StudentChoiceInputInt > realIndeces2.size());

        int studentID = realIndeces2.get(assignStudentToCourse_StudentChoiceInputInt - 1);

        // Add Student to the Course
        try {

            con1.checkConnection();
            PreparedStatement pstm = null;
            String addStudentCourseQr = "INSERT INTO COURSE_STUDENT VALUES (?, ?)";
            pstm = con.prepareStatement(addStudentCourseQr);
            pstm.setInt(1, courseID);
            pstm.setInt(2, studentID);

            pstm.executeUpdate();

            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Add the current Assignments of the Course to the Student
        // Get IDs of the Course's Assignments and Print those Assignments
        System.out.println("");
        System.out.println("The following Course's Assignments have been added to the newly assigned Student");
        System.out.println("--------------------------------------------------------------------------------");

        String query = "SELECT ASSIGNMENT_ID, ASSIGNMENT_TITLE FROM ASSIGNMENT WHERE COURSE_ID = ?";

        assignmentIDs = tablePrinter(query, courseID, 0);

        if (assignmentIDs.isEmpty()) {
            System.out.println("Currently, the Course does not have any Assignments to be assigned to the Student.");
            System.out.println("Any future Course Assignments will automatically be added to this Student also.");
        } else {

            for (int assignmentID : assignmentIDs) {

                try {

                    con1.checkConnection();
                    PreparedStatement pstm = null;
                    String addStudentAssignmentQr = "INSERT INTO STUDENT_ASSIGNMENT VALUES (?, ? , NULL , NULL)";
                    pstm = con.prepareStatement(addStudentAssignmentQr);
                    pstm.setInt(1, studentID);
                    pstm.setInt(2, assignmentID);

                    pstm.executeUpdate();

                    pstm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        // Ask if users wants to repeat last action
        inputRepeater(addMenuInputInt);

    }

}
