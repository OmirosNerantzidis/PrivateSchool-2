/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputs;

import static Menus.AddMenu.addMenuInputInt;
import static Tools.InputRepeater.inputRepeater;

/**
 *
 * @author omiro
 */
public class AssignmentCourseStudentInput {

    static public void assignmentCourseStudentInput() {

        System.out.println("");
        System.out.println("This function is handled automatically");
        System.out.println("--------------------------------------");
        System.out.println("When a Student is assigned to a Course, he automatically");
        System.out.println("gets assigned all current and future Assignments of that Course.");
        System.out.println("");
        System.out.println("Similarly, if a new Assignment gets added to a Course,");
        System.out.println("it will also be assigned to all current and future Students of that Course");
        System.out.println("");

        inputRepeater(addMenuInputInt);

    }

}
