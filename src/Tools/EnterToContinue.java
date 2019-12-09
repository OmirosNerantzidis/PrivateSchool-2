/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Scanner;

/**
 *
 * @author omiro
 */
public class EnterToContinue {

    public static void enterToContinue() {

        Scanner enterToContinueInput = new Scanner(System.in);

        System.out.println("");
        System.out.print("Press Enter to Continue...");
        enterToContinueInput.nextLine();
        System.out.println("");
        Menus.QueriesMenu.queriesMenu();
    }

}
