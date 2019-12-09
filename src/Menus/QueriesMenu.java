/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import static Queries.AssignmentListPrint.assignmentListPrint;
import static Queries.AssignmentsPerCourse.assignmentPerCourse;
import static Queries.AssignmentsPerCoursePerStudent.assignmentsPerCoursePerStudent;
import static Queries.CourseListPrint.courseListPrint;
import static Queries.MultipleCourseStudentsPrint.multipleCourseStudentsPrint;
import static Queries.StudentListPrint.studentListPrint;
import static Queries.StudentsPerCourse.studentsPerCourse;
import static Queries.TrainerListPrint.trainerListPrint;
import static Queries.TrainersPerCourse.trainersPerCourse;
import static Tools.IntRangeChecker.intRangeChecker;
import java.util.Scanner;

/**
 *
 * @author omiro
 */
public class QueriesMenu {

    public static void queriesMenu() {

        int queriesMenuInputInt = -1;

        do {
            Scanner queriesMenuInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("");
            System.out.println("QUERIES MENU");
            System.out.println("----------");
            System.out.println("1 - Students List ( Name / Date of Birth / Tuition Fees)");
            System.out.println("2 - Trainers List (Name / Subject)");
            System.out.println("3 - Assignments List (Course / Title / Description / Submission Date)");
            System.out.println("4 - Courses List (Title / Stream / Type / Start Date / End Date)");
            System.out.println("5 - List of Students within a Course");
            System.out.println("6 - List of Trainers of a Course");
            System.out.println("7 - List of Assignments of a Course");
            System.out.println("8 - List of Assignments per Course per Student [with his Marks]");
            System.out.println("9 - List of Students that belong to more than 1 courses");
            System.out.println("");
            System.out.println("0 - Back to Main Menu");
            System.out.println("");
            System.out.print("Please Select (0-9): ");

            queriesMenuInputInt = intRangeChecker(0, 9, queriesMenuInput);

        } while (queriesMenuInputInt < 0 || queriesMenuInputInt > 9);

        switch (queriesMenuInputInt) {
            case 1:
                studentListPrint();
                break;
            case 2:
                trainerListPrint();
                break;
            case 3:
                assignmentListPrint();
                break;
            case 4:
                courseListPrint();
                break;
            case 5:
                studentsPerCourse();
                break;
            case 6:
                trainersPerCourse();
                break;
            case 7:
                assignmentPerCourse();
                break;
            case 8:
                assignmentsPerCoursePerStudent();
                break;
            case 9:
                multipleCourseStudentsPrint();
                break;
            case 0:
                Menus.MainMenu.mainMenu();
                break;
            default:
                System.out.println("");
                System.out.println("Wrong input, try again!");
                System.out.println("");

        }

    }

}
