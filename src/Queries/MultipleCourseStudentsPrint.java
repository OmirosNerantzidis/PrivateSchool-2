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
public class MultipleCourseStudentsPrint {

    public static void multipleCourseStudentsPrint() {

        System.out.println("");
        System.out.println("Students that follow more than 1 courses");
        System.out.println("----------------------------------------");
        System.out.println("");
        System.out.println("Name / Number of Courses");
        System.out.println("------------------------");

        boolean notEmpty = notEmptyTableChecker("MULTIPLE_COURSE_STUDENTS");

        String query = "SELECT * FROM MULTIPLE_COURSE_STUDENTS";

        if (notEmpty == true) {
            tablePrinter(query, 0, 0);
        }

        enterToContinue();
        Menus.QueriesMenu.queriesMenu();

    }

}
