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
public class CourseListPrint {

    public static void courseListPrint() {

        System.out.println("");
        System.out.println("Courses");
        System.out.println("-------");
        System.out.println("");
        System.out.println("Title / Stream / Type / Start Date / End Date");
        System.out.println("---------------------------------------------");

        boolean notEmpty = notEmptyTableChecker("COURSE");

        String query = "SELECT COURSE_ID, COURSE_TITLE, COURSE_STREAM, COURSE_TYPE, COURSE_START_DATE, COURSE_END_DATE FROM COURSE";

        if (notEmpty == true) {
            tablePrinter(query, 0, 0);
        }

        enterToContinue();

        Menus.QueriesMenu.queriesMenu();

    }

}
