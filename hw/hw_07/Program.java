package hw_07;

public class Program {
    public static void main(String[] args) {
        System.out.println(findSymbolOccurance("asfdf;lasasdflasias;fd", 'a'));
        System.out.println(findWordPosition("Apollo", "pollo"));
        System.out.println(findWordPosition("Apple", "Plant"));
        System.out.println(stringReverse("Hello"));
        System.out.println(isPalindrome("ERE"));
        System.out.println(isPalindrome("Allo"));
    }

    // 2. Створити метод findSymbolOccurance. Метод приймає як параметр рядок і символ. Необхідно обчислити, скільки разів символ зустрічається в переданому рядку і повернути це числове значення.
    public static int findSymbolOccurance(String line, char symb) {
        int n = 0;
        for (int i = 0; i < line.length(); i++) {
            if (symb == line.charAt(i)) {
                n++;
            }
        }
        return n;
    }

    // 3. Створити метод findWordPosition. Метод приймає як параметри два рядки (source, target). Необхідно з'ясувати, чи є target (підрядок) частиною рядка source. Якщо так, тоді повернути номер позиції (індекс) першого елемента підрядка у рядку, інакше повернути -1.
    public static int findWordPosition(String source, String target) {
        return source.indexOf(target);
    }

    // 4. Створити метод stringReverse. Метод приймає як параметр - рядок. Необхідно її розгорнути та повернути змінений варіант.
    public static String stringReverse(String line) {
        return new StringBuilder(line).reverse().toString();
    }

    // 5. Створити метод isPalindrome. Метод приймає як параметр - рядок. Необхідно Перевірити, чи є переданий рядок паліндромом. Якщо так, тоді повернуть true, інакше false.
    public static boolean isPalindrome(String line) {
        int left = 0;
        int right = line.length() - 1;
        while (left < right) {
            if (line.charAt(left++) != line.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
