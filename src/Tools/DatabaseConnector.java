/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import static Tools.DbLogin.dbPass;
import static Tools.DbLogin.dbUser;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omiro
 */
public class DatabaseConnector {

    public static Connection con = null;
    static boolean firstConnection = true;

    public void checkConnection() {

        String dbConnection = "jdbc:mysql://localhost:3306/private_school?useSSL=false";

//            System.out.print("Checking database connection...");       
        try {
            if (con == null || !con.isValid(1)) {
                if (firstConnection == true) {
                    System.out.println("Connecting to the database...");
                    System.out.println("");
                    firstConnection = false;
                } else {
                    System.out.println("Database disconnected. Reconnecting...");
                    System.out.println("");
                }
                con = DriverManager.getConnection(dbConnection, dbUser, dbPass);
            } else {
//        System.out.println("Connection OK");
//        System.out.println("")
            }
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            System.out.println("");
            con = null;
            Tools.DbLogin.dbLogin();
        }

    }

    public void closeConnection() {

        try {
            System.out.println("");
            System.out.println("Closing database connection...");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
