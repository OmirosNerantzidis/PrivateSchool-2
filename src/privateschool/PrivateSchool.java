
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool;

import Tools.DatabaseConnector;

/**
 *
 * @author omiro
 */
public class PrivateSchool {

    public static DatabaseConnector con1 = new DatabaseConnector();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Tools.DbLogin.dbLogin();

        System.out.println("PRIVATE SCHOOL - THE JAVA PROGRAM");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("Written by Omiros Nerantzidis");
        System.out.println("");
        System.out.println("Tested in NetBeans IDE 8.2");
        System.out.println("");

        Menus.MainMenu.mainMenu();

    }

}
