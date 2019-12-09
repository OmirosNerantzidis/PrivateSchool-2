/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import static Tools.DatabaseConnector.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static privateschool.PrivateSchool.con1;

/**
 *
 * @author omiro
 */
public class TablePrinter {

    public static ArrayList tablePrinter(String query, int userInput1, int userInput2) {

        ArrayList<Integer> idIndex = new ArrayList<>();

        try {

            con1.checkConnection();

            PreparedStatement pstm = null;
            ResultSet rs = null;
            String printString = null;

            pstm = con.prepareStatement(query);

            if (userInput1 != 0) {
                pstm.setInt(1, userInput1);
            }

            if (userInput2 != 0) {
                pstm.setInt(2, userInput2);
            }

            rs = pstm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int count = 0;

            while (rs.next()) {
                count++;
                idIndex.add(rs.getInt(1));
                printString = count + ") ";

                for (int i = 2; i <= columnsNumber; i++) {
                    printString += rs.getString(i);

                    if (i < columnsNumber) {
                        printString += " / ";
                    }

                }

                System.out.println(printString);
                printString = null;
            }

            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(TablePrinter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idIndex;
    }

}
