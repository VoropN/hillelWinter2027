package hw_06.task;

import java.util.ArrayList;
import java.util.List;

//Дан массив целых чисел и ещё одно целое число. Удалите все вхождения этого числа из массива
//(пропусков быть не должно). 1 2 3 4 5 6 4 5 4 - (4) -> 1 2 3 5 6 5
public class NumberRemover {
    static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>(List.of(10, 30, 20, 30, 40, 50));
        Integer valueToRemove = 30;
        nums.removeAll(List.of(valueToRemove));
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static int[] filter(int val, int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (val != arr[i]) {
                arr[index++] = arr[i];
            }
        }


        return arr;
    }
}
