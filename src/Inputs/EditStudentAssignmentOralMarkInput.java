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
public class EditStudentAssignmentOralMarkInput {

    static ArrayList<Integer> realIndeces;
    static ArrayList<Integer> realIndeces2;
    static int studentID = -1;
    static int assignmentID = -1;

    static public void editStudentAssignmentOralMarkInput() {

        Scanner editStudentAssignmentOralMarkInputFinish = new Scanner(System.in);

        if (notEmptyTableChecker("STUDENT_ASSIGNMENT") == false) { // Checks if there are available Students with Assignments to choose from
            System.out.println("");
            System.out.println("Currently there are not any Students with Assignments");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            String editStudentAssignmentOralMarkInputFinishString = editStudentAssignmentOralMarkInputFinish.nextLine();
            if (editStudentAssignmentOralMarkInputFinishString.length() == 0) {
                addMenuInputInt = -1;
                addMenu();
            }
        }

        // Print list of Students
        int editStudentAssignmentOralMarkStudentChoiceInputInt = -1;

        do {
            Scanner editStudentAssignmentOralMarkStudentChoiceInput = new Scanner(System.in);

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
            System.out.println("Choose the number of the Student you want to edit his Oral Mark in an Assignment: ");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            editStudentAssignmentOralMarkStudentChoiceInputInt = intRangeChecker(0, realIndeces.size(), editStudentAssignmentOralMarkStudentChoiceInput);
            if (editStudentAssignmentOralMarkStudentChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (editStudentAssignmentOralMarkStudentChoiceInputInt < 0 || editStudentAssignmentOralMarkStudentChoiceInputInt > realIndeces.size());

        studentID = realIndeces.get(editStudentAssignmentOralMarkStudentChoiceInputInt - 1);

        // Prints a menu with the the Student's Assignments along their current Oral Mark       
        int editStudentAssignmentOralMarkAssignmentChoiceInputInt = -1;
        int maxMenuValue;
        do {
            Scanner editStudentAssignmentOralMarkAssignmentChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Assignments for this Student");
            System.out.println("--------------------------------------");
            System.out.println("");
            System.out.println("No / Course Title / Assignment Title / Oral Mark (Null = No Mark)");
            System.out.println("------------------------------------------------------------- ");

            String query = "SELECT A.ASSIGNMENT_ID, C.COURSE_TITLE, A.ASSIGNMENT_TITLE, SA.STUDENT_ASSIGNMENT_ORAL_MARK FROM STUDENT_ASSIGNMENT SA, ASSIGNMENT A, COURSE C WHERE A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID AND C.COURSE_ID = A.COURSE_ID AND STUDENT_ID = ?";

            realIndeces2 = tablePrinter(query, studentID, 0);

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Assignment you want to edit his Oral Mark in an Assignment: ");
            maxMenuValue = realIndeces2.size();
            System.out.print("Please Select (0-" + maxMenuValue + "): ");

            editStudentAssignmentOralMarkAssignmentChoiceInputInt = intRangeChecker(0, maxMenuValue, editStudentAssignmentOralMarkAssignmentChoiceInput);
            if (editStudentAssignmentOralMarkAssignmentChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (editStudentAssignmentOralMarkAssignmentChoiceInputInt < 0 || editStudentAssignmentOralMarkAssignmentChoiceInputInt > maxMenuValue);

        int workingAssignmentRealIndex = editStudentAssignmentOralMarkAssignmentChoiceInputInt - 1;

        assignmentID = realIndeces2.get(workingAssignmentRealIndex);

// Request new Oral Mark
        double editStudentAssignmentOralMarkNewOralMarkInputDouble = -1;

        do {
            Scanner editStudentAssignmentOralMarkNewOralMarkInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Input new Oral Mark");
            System.out.print("Please Select (0-10.0): ");

            editStudentAssignmentOralMarkNewOralMarkInputDouble = doubleRangeChecker(0, 10, editStudentAssignmentOralMarkNewOralMarkInput);

        } while (editStudentAssignmentOralMarkNewOralMarkInputDouble < 0 || editStudentAssignmentOralMarkNewOralMarkInputDouble > 10);

        // Set the new Oral Mark  
        // Add info to database
        try {
            con1.checkConnection();
            PreparedStatement pstm = null;
            String updateStudentAssignmentOralMarkQr = "UPDATE STUDENT_ASSIGNMENT SET STUDENT_ASSIGNMENT_ORAL_MARK = ? WHERE STUDENT_ID = ? AND ASSIGNMENT_ID = ?";
            pstm = con.prepareStatement(updateStudentAssignmentOralMarkQr);
            pstm.setDouble(1, editStudentAssignmentOralMarkNewOralMarkInputDouble);
            pstm.setInt(2, studentID);
            pstm.setInt(3, assignmentID);

            pstm.executeUpdate();

            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.out.println("The Student's Oral Mark for this assignment has been updated!");

        // Ask if users wants to repeat last action
        inputRepeater(addMenuInputInt);

    }

}
