/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Scanner;
import static privateschool.PrivateSchool.con1;

/**
 *
 * @author omiro
 */
public class DbLogin {

    public static String dbUser;
    public static String dbPass;
    static Scanner dbUserInput = new Scanner(System.in);
    static Scanner dbPassInput = new Scanner(System.in);

    public static void dbLogin() {

        System.out.print("Enter database Username: ");
        dbUser = dbUserInput.next();
        System.out.print("Enter database Password: ");
        dbPass = dbPassInput.next();
        System.out.println("");

        con1.checkConnection();

    }

}
