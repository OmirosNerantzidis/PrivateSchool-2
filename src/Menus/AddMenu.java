/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import static Inputs.AssignStudentToCourseInput.assignStudentToCourseInput;
import static Inputs.AssignTrainerToCourseInput.assignTrainerToCourseInput;
import static Inputs.EditStudentAssignmentOralMarkInput.editStudentAssignmentOralMarkInput;
import static Inputs.EditStudentAssignmentTotalMarkInput.editStudentAssignmentTotalMarkInput;
import static Tools.IntRangeChecker.intRangeChecker;
import java.util.Scanner;

/**
 *
 * @author omiro
 */
public class AddMenu {

    public static int addMenuInputInt = -1;

    public static void addMenu() {

        while (addMenuInputInt < 0 || addMenuInputInt > 9) {
            Scanner addMenuInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("");
            System.out.println("INPUT MENU");
            System.out.println("----------");
            System.out.println("1 - Add new Course(s)");
            System.out.println("2 - Add new Trainer(s)");
            System.out.println("3 - Assign a Trainer to a Course");
            System.out.println("4 - Add new Student(s)");
            System.out.println("5 - Assign a Student to a Course");
            System.out.println("6 - Add new Assignment(s) to a Course");
            System.out.println("7 - Assignment Per Course Per Student");
            System.out.println("8 - Edit Oral Mark to a Student's Assignment");
            System.out.println("9 - Edit Total Mark to a Student's Assignment");
            System.out.println("");
            System.out.println("0 - Back to Main Menu");
            System.out.println("");
            System.out.print("Please Select (0-9): ");

            addMenuInputInt = intRangeChecker(0, 9, addMenuInput);

        }

        switch (addMenuInputInt) {
            case 1:
                Inputs.CourseInput.courseInput();
                break;
            case 2:
                Inputs.TrainerInput.trainerInput();
                break;
            case 3:
                assignTrainerToCourseInput();
                break;
            case 4:
                Inputs.StudentInput.studentInput();
                break;
            case 5:
                assignStudentToCourseInput();
                break;
            case 6:
                Inputs.AssignmentInput.assignmentInput();
                break;
            case 7:
                Inputs.AssignmentCourseStudentInput.assignmentCourseStudentInput();
                break;
            case 8:
                editStudentAssignmentOralMarkInput();
                break;
            case 9:
                editStudentAssignmentTotalMarkInput();
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
