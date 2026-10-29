package hw_08;


// 1. Створити Клас ArrayValueCalculator
public class ArrayValueCalculator {
    public static void main(String[] args) {
        String[][] withSizeEx = {{"1"}, {"2"}, {"3"}, {"4"}};
        String[][] withSizeEx1 = {{"1"}, {"2"}, {"3"}, {"4"}, {"5"}};
        String[][] withFormatEx = {{"1", "2", "3", "4"}, {"`", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr = new String[4][];
        for (int i = 0; i < 4; i++) {
            arr[i] = new String[]{"1", "2", "3", "4"};
        }
        var ar = new String[][][]{withSizeEx, withSizeEx1, withFormatEx, arr};

        // 6. У методі main() викликати отриманий метод, обробити можливі винятки ArraySizeException та ArrayDataException, та вивести результат розрахунку.
        for (String[][] a : ar) {
            try {
                System.out.println(doCalc(a));
            } catch (ArraySizeException | ArrayDataException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 2. Створити метод doCalc. На вхід до методу подається двомірний рядковий масив розміром 4х4.
    public static int doCalc(String[][] arr) throws ArraySizeException, ArrayDataException {
        final int allowedSize = 4;
        if (arr.length != allowedSize) {
            // 3. При передачі масиву іншого розміру в метод doCalc необхідно викинути виняток ArraySizeException.
            throw new ArraySizeException("Expected the first dimension to have %d elements, but got %d.".formatted(allowedSize, arr.length));
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int len = arr[i].length;
            if (len != allowedSize) {
                throw new ArraySizeException("Expected each row to have %d elements, but row %d has %d.".formatted(allowedSize, i, len));
            }
            for (int j = 0; j < len; j++) {
                String val = arr[i][j];
                // 8. * При вилові винятків у процесі перетворення, взяти до уваги винятки, що викидаються іншими (сторонніми) методами, що використовуються.
                try {
                    // 4. Метод doCalc повинен пройтися за всіма елементами масиву і перетворити в int (використовувати Integer#parseInt) і підсумувати. Після загального підсумовування метод повертає фінальний результат у вигляді int.
                    sum += Integer.parseInt(val);
                } catch (NumberFormatException ex) {
                    // 5.Якщо в якомусь елементі масиву перетворення не вдалося (наприклад, в комірці лежить символ або текст замість числа), тоді викинути виняток ArrayDataException, з деталізацією в якому саме комірці лежать невірні дані.
                    throw new ArrayDataException("Could not parse '%s' as an integer at row %d, column %d.".formatted(val, i, j), ex);
                }
            }
        }

        return sum;
    }
}
