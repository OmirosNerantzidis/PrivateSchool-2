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
public class TrainerListPrint {

    public static void trainerListPrint() {

        System.out.println("");
        System.out.println("Trainers");
        System.out.println("-----------");
        System.out.println("");
        System.out.println("Name / Subject");
        System.out.println("-----------------------------------------------------");

        boolean notEmpty = notEmptyTableChecker("TRAINER");

        String query = "SELECT TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_SUBJECT FROM TRAINER";

        if (notEmpty == true) {
            tablePrinter(query, 0, 0);
        }

        enterToContinue();

        Menus.QueriesMenu.queriesMenu();

    }

}
