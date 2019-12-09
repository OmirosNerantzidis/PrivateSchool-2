/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputs;

import static Menus.AddMenu.addMenu;
import static Menus.AddMenu.addMenuInputInt;
import static Tools.DatabaseConnector.con;
import static Tools.DoubleRangeChecker.doubleRangeChecker;
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
public class EditStudentAssignmentTotalMarkInput {

    static ArrayList<Integer> realIndeces;
    static ArrayList<Integer> realIndeces2;
    static int studentID = -1;
    static int assignmentID = -1;

    static public void editStudentAssignmentTotalMarkInput() {

        Scanner editStudentAssignmentTotalMarkInputFinish = new Scanner(System.in);

        if (notEmptyTableChecker("STUDENT_ASSIGNMENT") == false) { // Checks if there are available Students with Assignments to choose from
            System.out.println("");
            System.out.println("Currently there are not any Students with Assignments");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            String editStudentAssignmentTotalMarkInputFinishString = editStudentAssignmentTotalMarkInputFinish.nextLine();
            if (editStudentAssignmentTotalMarkInputFinishString.length() == 0) {
                addMenuInputInt = -1;
                addMenu();
            }
        }

        // Print list of Students
        int editStudentAssignmentTotalMarkStudentChoiceInputInt = -1;

        do {
            Scanner editStudentAssignmentTotalMarkStudentChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Students");
            System.out.println("-----------------------------------------  ");
            System.out.println("(Students with no Assignments are excluded)");

            String query = "SELECT SA.STUDENT_ID, S.STUDENT_FIRST_NAME, S.STUDENT_LAST_NAME FROM STUDENT_ASSIGNMENT SA, STUDENT S WHERE SA.STUDENT_ID = S.STUDENT_ID GROUP BY SA.STUDENT_ID";
            /////////////
            // Dynamically prints a menu excluding Students with no assignments
            // In parallel, it creates an Array with the real indices of the printed options
            realIndeces = tablePrinter(query, 0, 0);

            if (realIndeces.isEmpty()) {
                System.out.println("None of the Students have assignments set");
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Student you want to edit his Total Mark in an Assignment: ");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            editStudentAssignmentTotalMarkStudentChoiceInputInt = intRangeChecker(0, realIndeces.size(), editStudentAssignmentTotalMarkStudentChoiceInput);
            if (editStudentAssignmentTotalMarkStudentChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (editStudentAssignmentTotalMarkStudentChoiceInputInt < 0 || editStudentAssignmentTotalMarkStudentChoiceInputInt > realIndeces.size());

        studentID = realIndeces.get(editStudentAssignmentTotalMarkStudentChoiceInputInt - 1);

        // Prints a menu with the the Student's Assignments along their current Total Mark       
        int editStudentAssignmentTotalMarkAssignmentChoiceInputInt = -1;
        int maxMenuValue;
        do {
            Scanner editStudentAssignmentTotalMarkAssignmentChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Assignments for this Student");
            System.out.println("--------------------------------------");
            System.out.println("");
            System.out.println("No / Course Title / Assignment Title / Total Mark (Null = No Mark)");
            System.out.println("------------------------------------------------------------- ");

            String query = "SELECT A.ASSIGNMENT_ID, C.COURSE_TITLE, A.ASSIGNMENT_TITLE, SA.STUDENT_ASSIGNMENT_TOTAL_MARK FROM STUDENT_ASSIGNMENT SA, ASSIGNMENT A, COURSE C WHERE A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID AND C.COURSE_ID = A.COURSE_ID AND STUDENT_ID = ?";

            realIndeces2 = tablePrinter(query, studentID, 0);

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Assignment you want to edit his Total Mark in an Assignment: ");
            maxMenuValue = realIndeces2.size();
            System.out.print("Please Select (0-" + maxMenuValue + "): ");

            editStudentAssignmentTotalMarkAssignmentChoiceInputInt = intRangeChecker(0, maxMenuValue, editStudentAssignmentTotalMarkAssignmentChoiceInput);
            if (editStudentAssignmentTotalMarkAssignmentChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (editStudentAssignmentTotalMarkAssignmentChoiceInputInt < 0 || editStudentAssignmentTotalMarkAssignmentChoiceInputInt > maxMenuValue);

        int workingAssignmentRealIndex = editStudentAssignmentTotalMarkAssignmentChoiceInputInt - 1;

        assignmentID = realIndeces2.get(workingAssignmentRealIndex);

// Request new Total Mark
        double editStudentAssignmentTotalMarkNewTotalMarkInputDouble = -1;

        do {
            Scanner editStudentAssignmentTotalMarkNewTotalMarkInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Input new Total Mark");
            System.out.print("Please Select (0-10.0): ");

            editStudentAssignmentTotalMarkNewTotalMarkInputDouble = doubleRangeChecker(0, 10, editStudentAssignmentTotalMarkNewTotalMarkInput);

        } while (editStudentAssignmentTotalMarkNewTotalMarkInputDouble < 0 || editStudentAssignmentTotalMarkNewTotalMarkInputDouble > 10);

        // Set the new Total Mark  
        // Add info to database
        try {
            con1.checkConnection();
            PreparedStatement pstm = null;
            String updateStudentAssignmentTotalMarkQr = "UPDATE STUDENT_ASSIGNMENT SET STUDENT_ASSIGNMENT_TOTAL_MARK = ? WHERE STUDENT_ID = ? AND ASSIGNMENT_ID = ?";
            pstm = con.prepareStatement(updateStudentAssignmentTotalMarkQr);
            pstm.setDouble(1, editStudentAssignmentTotalMarkNewTotalMarkInputDouble);
            pstm.setInt(2, studentID);
            pstm.setInt(3, assignmentID);

            pstm.executeUpdate();

            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.out.println("The Student's Total Mark for this assignment has been updated!");

        // Ask if users wants to repeat last action
        inputRepeater(addMenuInputInt);

    }

}
