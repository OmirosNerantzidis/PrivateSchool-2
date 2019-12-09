/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import static Menus.AddMenu.addMenu;
import static Menus.AddMenu.addMenuInputInt;
import static Tools.IntRangeChecker.intRangeChecker;
import java.util.Scanner;
import static privateschool.PrivateSchool.con1;

/**
 *
 * @author omiro
 */
public class MainMenu {

    static int mainMenuInputInt = -1;

    public static void mainMenu() {
        mainMenuInputInt = -1;

        while (mainMenuInputInt < 0 || mainMenuInputInt > 2) {

            Scanner mainMenuInput = new Scanner(System.in);

            System.out.println("");
            System.out.println("MAIN MENU");
            System.out.println("---------");
            System.out.println("1 - Add new Course(s) / Trainer(s) / Student(s) / Assignment(s)");
            System.out.println("2 - Information Queries");
            System.out.println("");
            System.out.println("0 - Exit Program & Close Database Connection");
            System.out.println("");
            System.out.print("Please Select (0-2): ");

            mainMenuInputInt = intRangeChecker(0, 2, mainMenuInput);

        }

        switch (mainMenuInputInt) {
            case 1:
                addMenuInputInt = -1;
                addMenu();
                break;
            case 2:
                Menus.QueriesMenu.queriesMenu();
                break;
            case 0:
                //Close Database Connection

                con1.closeConnection();

                System.out.println("");
                System.out.println("Goodbye!");
                System.out.println("");
                System.exit(0);
            default:
                System.out.println("");
                System.out.println("Wrong input, try again!");
                System.out.println("");

        }

    }

}
