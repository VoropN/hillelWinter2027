package hw_06.task;

import java.util.Scanner;

/*
9. Нарисовать треугольник
    Types of Triangles
    1.  Left
    2.  Right
    3.  Center
    Enter a number (1-3):  3
    How many rows?: 6
 */
public class Triangle {
    public static void main(String[] args) {
        System.out.println(" Types of Triangles");
        System.out.println(" 1.  Left");
        System.out.println(" 2.  Center");
        System.out.println(" 3.  Right");
        System.out.println(" Enter a number (1-3):");

        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        System.out.println("How many rows?:");
        int rows = scanner.nextInt();

        if (type == 1) {
            drawLeft(rows);
        } else if (type == 2) {
            drawCenter(rows);
        } else if (type == 3) {
            drawRight(rows);
        }
    }


    /*
      #
     ##
    ###
   ####
    */
    private static void drawRight(int rows) {
        int spaces = rows - 1;
        int symb = 1;
        while (spaces >= 0) {
            System.out.println(" ".repeat(spaces--) + "#".repeat(symb++));
        }
    }
    /*
      #
     ###
    #####
   #######
    */
    private static void drawCenter(int rows) {
        int spaces = rows * 2 - 1;
        int symb = 1;
        while (spaces >= 0) {
            System.out.println(" ".repeat(spaces / 2) + "#".repeat(symb) + " ".repeat(spaces / 2));
            spaces -= 2;
            symb += 2;
        }
    }

    /*
   #
   ##
   ###
   ####
    */
    private static void drawLeft(int rows) {
        int spaces = rows - 1;
        int symb = 1;
        while (spaces >= 0) {
            System.out.println("#".repeat(symb++) + " ".repeat(spaces--));
        }
    }
}
