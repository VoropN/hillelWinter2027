package hw_06.task;
/**
 * Напишите программу, которая выводит на экран числа от 1 до 100.
 * При этом вместо чисел, кратных трем, программа должна выводить слово «Fizz»,
 * а вместо чисел, кратных пяти — слово «Buzz». Если число кратно и 3, и 5,
 * то программа должна выводить слово «FizzBuzz»
 */
public class From1to100 {
    public static void main(String[] args) {
        print(15);
    }

    public static void print(int n) {
        for (int i = 1; i <= Math.min(n, 100); i++) {
            boolean isDividedBy3 = i % 3 == 0;
            boolean isDividedBy5 = i % 5 == 0;
            if (isDividedBy3 && isDividedBy5) System.out.println(i + " FizzBuzz");
            else if (isDividedBy3) System.out.println(i +  " Fizz");
            else if (isDividedBy5) System.out.println(i +  " Buzz");
        }
    }
}

