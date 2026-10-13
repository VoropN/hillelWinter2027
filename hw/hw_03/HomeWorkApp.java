package hw_03;

public class HomeWorkApp {
    // 1. Створити порожній проект у IntelliJ IDEA, створити клас HomeWorkApp і
    // прописати в ньому метод main().
    public static void main(String[] args) {
        printThreeWords(); // 2
        checkSumSign(); // 3
        printColor(); // 4
        compareNumbers(); // 5
        System.out.println(isInRange(11, 9)); // 6
        printNumberSign(0); // 7;
        System.out.println(isNegative(-1)); // 8
        printNTimes("Hi", 5); // 9
        System.out.println("2000: " + isLeapYear(2000)); // 10
        System.out.println("2024: " + isLeapYear(2024));
        System.out.println("2100: " + isLeapYear(2100));
    }

    // 2. Створіть метод printThreeWords(), який під час виклику повинен надрукувати
    // в стовпець у три слова: Orange, Banana, Apple.
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 3. Створіть метод checkSumSign(), у тілі якого оголосіть дві int змінні a і b,
    // та ініціалізуйте їх будь-якими значеннями, якими захочете. Далі метод повинен підсумувати ці змінні, і
    // якщо їх сума більша або дорівнює 0, то вивести в консоль повідомлення "Сума позитивна",
    // інакше - "Сума негативна";
    public static void checkSumSign() {
        int a = 1;
        int b = -2;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сума позитивна");
        } else {
            System.out.println("Сума негативна");
        }
    }

    // 4. Створіть метод printColor() у тілі якого задайте int змінну value та ініціалізуйте її будь-яким значенням.
    //  Якщо значення менше 0 (0 включно), то в консоль метод повинен вивести повідомлення "Червоний",
    //  якщо лежить в межах від 0 (0 виключно) до 100 (100 включно), то "Жовтий",
    //  якщо більше 100 (100 виключно) - "Зелений";
    public static void printColor() {
        int value = 10;
        if (value <= 0) {
            System.out.println("Червонний");
        } else if (value <= 100) {
            System.out.println("Жовтий");
        } else {
            System.out.println("Зелений");
        }
    }

    // 5. Створіть метод compareNumbers(), у тілі якого оголосіть дві int змінні a і b, та ініціалізуйте їх будь-якими значеннями, якими захочете.
    // Якщо a більше або одно b, необхідно вивести в консоль повідомлення “a >= b”, інакше “a < b”;
    public static void compareNumbers() {
        int a = 15;
        int b = 15;
        System.out.println(a >= b ? "a >= b" : "a < b");
    }

    // 6. Написати метод, який приймає на вхід два цілих числа і перевіряє, що їхня
    // сума лежить в межах від 10 до 20 (включно), якщо так – повернути true, інакше – false.
    public static boolean isInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 7. Написати метод, якому як параметр передається ціле число, метод повинен
    // надрукувати в консоль, чи додатнє число передали або від’ємне. Примітка: нуль вважаємо позитивним числом.
    public static void printNumberSign(int a) {
        if (a >= 0) {
            System.out.println("Позитивне число");
        } else {
            System.out.println("Негативне число");
        }
    }

    // 8. Написати метод, якому як параметр передається ціле число.
    // Метод повинен повернути true, якщо число є негативним, і повернути false якщо позитивне.
    public static boolean isNegative(int a) {
        return a < 0;
    }

    // 9. Написати метод, якому як аргументи передається рядок і число, метод повинен
    // надрукувати в консоль зазначений рядок, вказану кількість разів;
    public static void printNTimes(String line, int n) {
        while (n-- > 0) {
            System.out.println(line);
        }
    }

    // 10. * Написати метод, який визначає, чи є рік високосним, і повертає boolean (високосний – true, не високосний – false).
    // Кожен 4-й рік є високосним,
    // крім кожного 100-го,
    // причому кожен 400-й – високосний.
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
}
