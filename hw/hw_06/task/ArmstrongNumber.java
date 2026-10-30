package hw_06.task;
// Напишите Java-программу для проверки является ли введенное число - числом Армстронга.
// Прежде всего, нам нужно понять, что такое число Армстронга. Число Армстронга это число,
// значение которого равно сумме цифр, из которых оно состоит, возведенных в степень, равную
// количеству цифр в этом числе. Как пример - число 371:
//    371 = 3*3*3 + 7*7*7 + 1*1*1 = 27 + 343 + 1 = 371
// Если у вас число четырехзначное:
//    8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8 = 4096 + 16 + 0 + 4096 = 8208


public class ArmstrongNumber {
    public static void main() {
        System.out.println(isArmstrong(371));
    }

    public static boolean isArmstrong(int originNum) {
        int num = originNum;
        int n = String.valueOf(num).length();
        int sum = 0;
        while (num > 0) {
            int val = num % 10;
            sum += (int) Math.pow(val, n);
            num /= 10;
        }
        return sum == originNum;
    }
}
