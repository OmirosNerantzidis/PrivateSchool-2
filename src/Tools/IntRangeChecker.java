/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author omiro
 */
public class IntRangeChecker {

    public static int intRangeChecker(int min, int max, Scanner input) {

        try {
            int temp = input.nextInt();
            if (temp >= min && temp <= max) {
                return temp;
            } else {
                System.out.println("");
                System.out.println("Please enter a correct value!");
                System.out.println("");
                return min - 1; // we return an out of range not accepted value
            }

        } catch (InputMismatchException e) {
            System.out.println("");
            System.out.println("Please enter a correct value!");
            System.out.println("");
            return min - 1; // we return an out of range not accepted value

        }

    }

}
