/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import static Menus.AddMenu.addMenu;
import static Menus.AddMenu.addMenuInputInt;
import java.util.Scanner;

/**
 *
 * @author omiro
 */
public class InputRepeater {

    public static void inputRepeater(int addMenuOption) {

        Scanner inputFinish = new Scanner(System.in);

        String inputFinishString;
        do {
            System.out.println("");
            System.out.println("**********");
            System.out.println("A -> Repeat Last Action");
            System.out.println("B -> Back to the Previous Menu");
            System.out.println("**********");
            System.out.print("Please Choose: ");
            inputFinishString = inputFinish.nextLine();
            inputFinishString = inputFinishString.toUpperCase();
            switch (inputFinishString) {
                case "A":
                    addMenu(); // here we don't set the value to -1, like in B
                    // so it holds the old value, and directly repeats the old addMenu selected option
                    break;
                case "B":
                    addMenuInputInt = -1;
                    addMenu();
                    break;
                default:
                    System.out.println("");
                    System.out.print("Please enter a correct value!");
                    System.out.println("");
            }
        } while (inputFinishString.length() == 0 || (!inputFinishString.equals("A") && !inputFinishString.equals("B")));

    }

}
