/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author omiro
 */
public class DateFormatter {

    public static String dateFormatter(Date date) {
        // Converts Date objects in String Format

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formatter.format(date);
        return strDate;

    }

}
