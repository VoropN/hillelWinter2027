package hw_06.task;

import java.util.Scanner;

public class PusUp {
    /*
    Человек подтягивается, делает подходы лесенкой начиная с 1 раза
    - подсчитать сколько он всего подтянеться за N подходов, N задано с клавиатуры
    N = 4 -> 1 + 2 + 3 + 4 = 10

    - вывести общее время выполнения упражнения (10:45)
    каждое подтягивание занимает 5с, первый перерыв между подходами 60с,
    каждый следующий перерыв на 20% больше предыдущего.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of approaches: ");
        int n = scanner.nextInt();
        scanner.close();

        getPushUpTime1(n);
    }

    public static void getPushUpTime1(int n) {
        int totalPushUp = 0;
        int pushUpTime = 5;
        double rest = 60;
        double restCoef = 1.2;
        double totalTime = 0;

        while (n > 0) {
            totalPushUp += n;
            n--;
            if (n > 0) {
                totalTime += rest;
                rest *= restCoef;
            }
        }

        totalTime += totalPushUp * pushUpTime;
        System.out.printf("Total push-ups: %d%n", totalPushUp);
        System.out.printf("Total time: %.0f:%.0f%n", totalTime / 60, totalTime % 60);
    }

    public static void getPushUpTime(int n) {
        int totalPushUp = 0;
        for (int approach = 1; approach <= n; approach++) {
            totalPushUp += approach;
        }

        double restCoeff = 1.2;
        double pushUpTime = 5;
        double rest = 60;
        double totalTime = totalPushUp * pushUpTime;

        for (int breakIndex = 1; breakIndex < n; breakIndex++) {
            totalTime += rest;
            rest *= restCoeff;
        }

        System.out.printf("Total push-ups: %d%n", totalPushUp);
        System.out.printf("Total time: %02d:%02d%n", (int) totalTime / 60, (int) totalTime % 60);
    }
}
