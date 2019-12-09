/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputs;

import Classes.Assignment;
import static Menus.AddMenu.addMenu;
import static Menus.AddMenu.addMenuInputInt;
import static Tools.DatabaseConnector.con;
import static Tools.DateFormatter.dateFormatter;
import static Tools.InputRepeater.inputRepeater;
import static Tools.IntRangeChecker.intRangeChecker;
import static Tools.NotEmptyTableChecker.notEmptyTableChecker;
import static Tools.TablePrinter.tablePrinter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static privateschool.PrivateSchool.con1;

/**
 *
 * @author omiro
 */
public class AssignmentInput {

    static ArrayList<Integer> realIndeces;
    static ArrayList<Integer> studentIDs;
    static java.util.Date courseStartDate = new java.util.Date();
    static int assignmentID = -1;

    static String[] randomAssignmentTitleList = new String[]{"Individual Project A", "Individual Project B", "Individual Project C", "Individual Project D", "Individual Project E"};
    static String[] randomAssignmentDescriptionList = new String[]{"This is an assignment description A", "This is an assignment description B", "This is an assignment description C", "This is an assignment description D", "This is an assignment description E"};

    static public void assignmentInput() {

        Scanner assignmentTitleInput = new Scanner(System.in);
        Scanner assignmentDescriptionInput = new Scanner(System.in);
        Scanner assignmentSubDateInput = new Scanner(System.in);
        Scanner assignmentInputFinish = new Scanner(System.in);

// Print list of Courses
        boolean notEmpty = notEmptyTableChecker("COURSE");

        if (notEmpty == false) { // Checks if there are available Courses to choose from
            System.out.println("");
            System.out.println("There are no Courses available to add assignments to.");
            System.out.println("");
            System.out.println("Press Enter to go to the previous menu");
            System.out.println("");
            String assignmentInputFinishString = assignmentInputFinish.nextLine();
            if (assignmentInputFinishString.length() == 0) {
                addMenuInputInt = -1;
                addMenu();
            }
        }
        int assignmentCourseChoiceInputInt = -1;

        do {
            Scanner assignmentCourseChoiceInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("Available Courses");
            System.out.println("-----------------");

            String query = "SELECT COURSE_ID, COURSE_TITLE FROM COURSE";

            if (notEmpty == true) {
                realIndeces = tablePrinter(query, 0, 0);
            }

            System.out.println("");
            System.out.println("0 - Back to previous menu");
            System.out.println("");
            System.out.println("Choose the number of the Course you want to create an Assignment for");
            System.out.print("Please Select (0-" + realIndeces.size() + "): ");

            assignmentCourseChoiceInputInt = intRangeChecker(0, realIndeces.size(), assignmentCourseChoiceInput);
            if (assignmentCourseChoiceInputInt == 0) {
                addMenuInputInt = -1;
                addMenu();
            }

        } while (assignmentCourseChoiceInputInt < 0 || assignmentCourseChoiceInputInt > realIndeces.size());

        int workingCourseRealIndex = assignmentCourseChoiceInputInt - 1;

        // Assignment Title Input
        System.out.println("");
        System.out.println("Enter Assignment's Title");
        System.out.print("(or press Enter for a random input): ");

        String assignmentTitleInputString = assignmentTitleInput.nextLine();
        if (assignmentTitleInputString.length() == 0) {
            String randomAssignmentTitle = Tools.Randomizer.stringRandomizer(randomAssignmentTitleList);
            System.out.println("");
            System.out.println("Assignment's Title: " + randomAssignmentTitle);
            assignmentTitleInputString = randomAssignmentTitle;
        }

        // Assignment Description Input
        System.out.println("");
        System.out.println("Enter Assignment's Description");
        System.out.print("(or press Enter for a random input): ");

        String assignmentDescriptionInputString = assignmentDescriptionInput.nextLine();
        if (assignmentDescriptionInputString.length() == 0) {
            String randomAssignmentDescription = Tools.Randomizer.stringRandomizer(randomAssignmentDescriptionList);
            System.out.println("");
            System.out.println("Assignment's Description: " + randomAssignmentDescription);
            assignmentDescriptionInputString = randomAssignmentDescription;
        }

        // Get selected course's Start Date from database
        try {
            con1.checkConnection();
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String getCourseStartDateQr = "SELECT COURSE_START_DATE FROM COURSE WHERE COURSE_ID = ?";
            pstm = con.prepareStatement(getCourseStartDateQr);
            pstm.setInt(1, realIndeces.get(workingCourseRealIndex));

            rs = pstm.executeQuery();
            rs.next();

            courseStartDate = rs.getDate(1);

            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Assignment Sub Date Input
        boolean correctFormat = false;
        String assignmentSubDateInputString = null;
        Date assignmentSubDateInputDate = null;

        while (correctFormat == false) {

            try { // the exception checks for correct date format input which can be parsed as a Date
                System.out.println("");
                System.out.println("Enter Assignment Submission Date (DD-MM-YYYY)");
                System.out.println("(It must be after the Course's Start Date: " + dateFormatter(courseStartDate) + ")");
                System.out.print("(or press Enter for a random date 1 to 180 days after Course's Start Date): ");

                assignmentSubDateInputString = assignmentSubDateInput.nextLine();

                if (assignmentSubDateInputString.length() == 0) {
                    assignmentSubDateInputDate = Tools.Randomizer.assignmentSubDateRandomizer(courseStartDate);
                    System.out.println("");
                    System.out.println("Assignment Submission Date: " + dateFormatter(assignmentSubDateInputDate));

                } else {
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    assignmentSubDateInputDate = format.parse(assignmentSubDateInputString);
                }

                if (courseStartDate.compareTo(assignmentSubDateInputDate) > 0) {
                    // this checks if the date input is before the Course Start Date (not accepted)
                    System.out.println("");
                    System.out.println("Assignment Submission Date cannot be before the Course Start Date! Try again!");
                    System.out.println("");
                } else {
                    correctFormat = true;
                }

            } catch (ParseException ex) {
                System.out.println("");
                System.out.println("Wrong format! Please try again!");
                System.out.println("");
            }

        }

        Assignment assignment1 = new Assignment(assignmentTitleInputString,
                assignmentDescriptionInputString,
                assignmentSubDateInputDate,
                0,
                0
        );

        java.sql.Date sqlDate = new java.sql.Date(assignment1.getSubDateTime().getTime());

        // Add info to database
        // ADD new Assignment to the Assignment Table
        try {
            con1.checkConnection();
            PreparedStatement pstm = null;
            String addAssignmentQr = "INSERT INTO ASSIGNMENT VALUES (NULL, ? , ? , ? , ?)";
            pstm = con.prepareStatement(addAssignmentQr);
            pstm.setString(1, assignment1.getTitle());
            pstm.setString(2, assignment1.getDescription());
            pstm.setDate(3, sqlDate);
            pstm.setInt(4, realIndeces.get(workingCourseRealIndex));

            pstm.executeUpdate();

            pstm.close();

            // Get the ID of the newly created assignment
            pstm = null;
            ResultSet rs = null;
            String query = "SELECT LAST_INSERT_ID()";

            pstm = con.prepareStatement(query);

            rs = pstm.executeQuery();

            rs.next();
            assignmentID = rs.getInt(1);

            pstm.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentInput.class.getName()).log(Level.SEVERE, null, ex);
        }

// Gets the IDs of the current Students that follow the Course that we add a new Assignment into
        System.out.println("");
        System.out.println("The newly created Assignment has been added to the Course Students:");
        System.out.println("-------------------------------------------------------------------");

        String query = "SELECT S.STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME FROM STUDENT S, COURSE_STUDENT CS WHERE S.STUDENT_ID = CS.STUDENT_ID AND CS.COURSE_ID = ?";

        studentIDs = tablePrinter(query, realIndeces.get(workingCourseRealIndex), 0);

        if (studentIDs.isEmpty()) {
            System.out.println("Currently, the Course does not have any Students for the Assignment to be assigned,");
            System.out.println("but any future Course Students will automatically get this");
            System.out.println("");

        } else {

            for (int studentID : studentIDs) {

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
