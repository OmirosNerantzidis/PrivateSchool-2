/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Queries.StudentListPrint;
import static Tools.DatabaseConnector.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static privateschool.PrivateSchool.con1;

/**
 *
 * @author omiro
 */
public class NotEmptyTableChecker {

    public static boolean notEmptyTableChecker(String tableName) {

        String notEmptyTableCheckQr = "SELECT 1 AS Output FROM " + tableName + " LIMIT 1";
        boolean result = false;
        Statement stm = null;
        ResultSet rs = null;

        try {

            con1.checkConnection();

            stm = con.createStatement();
            rs = stm.executeQuery(notEmptyTableCheckQr);

            // me to rs.next() checkaroume an to resultset exei ena value i an einai adeio
            if (rs.next() == true) {
                result = true;
            } else {
                System.out.println("");
                System.out.println("There is nothing to display at the moment");
                result = false;
            }

            rs.close();
            stm.close();
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(StudentListPrint.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

}
