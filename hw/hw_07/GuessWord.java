package hw_07;

import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

/*
    6. * Створити масив зі слів String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado" , "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", " pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    При запуску програми комп'ютер загадує слово, запитує відповідь у користувача,
    порівнює його із загаданим словом та повідомляє чи правильно відповів користувач. Якщо слово не вгадано, комп'ютер показує літери, які стоять на своїх місцях.
    apple – загадане
    apricot - відповідь гравця
    ap############# (15 символів, щоб користувач не міг дізнатися довжину слова)
    Для порівняння двох слів посимвольно, можна скористатися:
    String str = "apple";
    str.charAt(0); - метод, поверне char, який стоїть у слові str на першій позиції
    Граємо до тих пір, поки гравець не відгадає слово
    Використовуємо лише маленькі літери
    ВАЖЛИВО: Під час роботи із завданням використовувати класи:
 */
class Game {
    private String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado" , "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", " pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        do {
            playRound();
        } while (askToPlayAgain());
        System.out.println("***Дякуємо за гру!***");
    }

    private void playRound() {
        System.out.println("                    ");
        System.out.println("***Гру розпочато!***");
        Random rnd = new Random();
        int idx = rnd.nextInt(words.length);
        String word = words[idx].toLowerCase().trim();
        System.out.println("Слово загадане. Спробуйте вгадати!");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String input = line.trim().toLowerCase();
            if (input.equals(word)) {
                System.out.println("Ви вгадали слово: \"" + word + "\". Поздововляємо!");
                System.out.println("***Гру закінчено!***");
                System.out.println("                    ");
                return;
            }
            int min = Math.min(input.length(), word.length());
            int guessed = 0;
            StringBuilder sb = new StringBuilder("#".repeat(15));
            for (int i = 0; i < min; i++) {
                if (word.charAt(i) == input.charAt(i)) {
                    sb.setCharAt(i, word.charAt(i));
                    guessed++;
                }
            }
            System.out.println(sb);
            if (guessed > 0) {
                System.out.printf("Ви дуже близько, вгадано %s букв. Спробуйте ще!\n", guessed);
            } else {
                System.out.println("Це щось зовсім інше, спройбуй ще!");
            }
        }
    }

    private boolean askToPlayAgain() {
        while (true) {
            System.out.print("Бажаєте зіграти ще раз? (так/ні): ");
            if (!sc.hasNextLine()) {
                return false;
            }
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("так")) {
                return true;
            }
            if (answer.equals("ні")) {
                return false;
            }
            System.out.println("Будь ласка, введіть \"так\" або \"ні\".");
        }
    }
}
