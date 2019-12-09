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
public class StudentListPrint {

    public static void studentListPrint() {

        System.out.println("");
        System.out.println("Students");
        System.out.println("--------");
        System.out.println("");
        System.out.println("Name / Date of Birth / Tuition Fees");
        System.out.println("------------------------------------");
        System.out.println();

        boolean notEmpty = notEmptyTableChecker("STUDENT");

        String query = "SELECT STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME, STUDENT_DATE_OF_BIRTH, STUDENT_TUITION_FEES FROM STUDENT";

        if (notEmpty == true) {
            tablePrinter(query, 0, 0);
        }

        enterToContinue();

    }

}
