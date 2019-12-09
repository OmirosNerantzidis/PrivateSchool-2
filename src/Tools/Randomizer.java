/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author omiro
 */
public class Randomizer {

    public static String stringRandomizer(String[] listToTakeRandomEntryFrom) {
        Random randNumber = new Random();
        int randomNumber = randNumber.nextInt(listToTakeRandomEntryFrom.length);
        // Returns a random number between 0 and list length minus 1
        return listToTakeRandomEntryFrom[randomNumber];
    }

    public static Date courseStartDateRandomizer() {
// Generate a random number of up to 999, for the days to add to the current date
        Random randNumber = new Random();
        int randomNumber = randNumber.nextInt(1000);
        Date currentDate = new Date();
        Date randomStartDate = new Date();
// Add the random number of days to the current date
        randomStartDate = DateUtil.addDays(currentDate, randomNumber);
        return randomStartDate;
    }

    public static Date courseEndDateRandomizer(Date startDate) {
// Generate a random number between 90-180 days, for the days to add to the Start Date
        Random randNumber = new Random();
        int randomNumber = randNumber.nextInt(90) + 90;

        Date randomEndDate = new Date();
// Add the random number of days to the current date
        randomEndDate = DateUtil.addDays(startDate, randomNumber);
        return randomEndDate;
    }

    public static Date studentDateOfBirthRandomizer() {
// Generate a random number of up to 11680 days (32 years), for the days to subtract from the current date
// then subtract anotheer 6570 days (18 years), so the total is between 18 to 50 years

        Random randNumber = new Random();
        int randomNumber = randNumber.nextInt(11680) + 6570;
        Date currentDate = new Date();
        Date randomStartDate = new Date();
// Subtract the random number of days from the current date
        randomStartDate = DateUtil.addDays(currentDate, (-1) * randomNumber);
        return randomStartDate;
    }

    public static double tuitionFeesRandomizer() {
// Generate a random number of up to 1500 Euros, then add another 1000
// so the total is between 1000 and 2500 euros

        Random randNumber = new Random();
        double randomTuitionFees = randNumber.nextInt(1500) + 1000;
        return randomTuitionFees;
    }

    public static Date assignmentSubDateRandomizer(Date workingCourseStartDate) {
// Generate a random number up to 180 to add to the Courses's Start Date

        Random randNumber = new Random();
        int randomNumber = randNumber.nextInt(180);
        Date randomAssignmentSubDate = new Date();
        randomAssignmentSubDate = DateUtil.addDays(workingCourseStartDate, randomNumber);
        return randomAssignmentSubDate;
    }

}
