/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queries;

import static Tools.EnterToContinue.enterToContinue;
import static Tools.NotEmptyTableChecker.notEmptyTableChecker;
import static Tools.TablePrinter.tablePrinter;

/**
 *
 * @author omiro
 */
public class AssignmentListPrint {

    public static void assignmentListPrint() {

        System.out.println("");
        System.out.println("Assignments");
        System.out.println("-----------");
        System.out.println("");
        System.out.println("Course / Title / Description / Submission Date");
        System.out.println("----------------------------------------------");

        boolean notEmpty = notEmptyTableChecker("ASSIGNMENT");

        String query = "SELECT ASSIGNMENT_ID, C.COURSE_TITLE, ASSIGNMENT_TITLE, ASSIGNMENT_DESCRIPTION, ASSIGNMENT_SUBMISSION_DATE FROM ASSIGNMENT A, COURSE C WHERE C.COURSE_ID = A.COURSE_ID;";

        if (notEmpty == true) {
            tablePrinter(query, 0, 0);
        }

        enterToContinue();

        Menus.QueriesMenu.queriesMenu();

    }

}
