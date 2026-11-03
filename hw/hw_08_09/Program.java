package hw_08_09;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        List<String> words = List.of("list", "one", "word", "one", "two", "dog", "tail", "dog", "dog", "tail",  "list", "dog", "around", "tree", "one", "around", "two", "two");
        List<String> strList = toList(new ArrayList<>(words));
        List<Integer> intList = toList(List.of(1,2,3));
        List<Integer> intList1 = toList(new Integer[]{1,2,3});
        List<Double> douList = toList(new ArrayList<>(List.of(.1,.2,.3)));
        System.out.printf("word \"%s\" found %d times.%n", "one", countOccurrence(words, "one"));
        System.out.printf("word \"%s\" found %d times.%n", "list", countOccurrence(new ArrayList<>(words), "list"));
        Integer[] numbers = {1, 2, 3, 4, 1, 2, 3, 1, 2, 1};
        System.out.println("unique values: " + findUnique(List.of(numbers)));
        calcOccurrence(words);
        System.out.printf(findOccurrence(words).toString());
    }

    // 1. Створити метод countOccurrence, що приймає на вхід рядковий список як параметр і довільний рядок. Список заповнений довільними словами 10-20 штук, які можуть повторюватись у необмеженій кількості. Порахувати скільки разів зустрічається переданий рядок як другий аргумент.
    public static int countOccurrence(List<String> list, String word) {
        int count = 0;
        for (String el : list) {
            if (Objects.equals(el, word)) {
                count++;
            }
        }
        return count;
    }

    // 2. Створити метод toList, що приймає на вхід цілісний масив довільної довжини. Конвертувати масив у список відповідного типу даних та повернути з методу.
    public static <T> List<T> toList(T[] list) {
        return List.of(list);
    }

    public static <T> List<T> toList(List<T> list) {
        return List.copyOf(list);
    }

    // 3. Створити метод findUnique, що приймає на вхід числовий список, що складається з довільних значень, які можуть повторюватися в необмеженій кількості. Необхідно повернути новий числовий список, що містить тільки унікальні числа.
    public static List<Integer> findUnique(List<Integer> list) {
        return List.copyOf(new LinkedHashSet<>(list));
    }

    // 4. ** Створити метод calcOccurrence, який приймає на вхід рядковий список як параметр. Список заповнений довільними словами 10-20 штук, які можуть повторюватись у необмеженій кількості. Обчислити скільки разів трапляється кожне слово. Результат вивести у консоль.
    public static void calcOccurrence(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : list) {
            int count = map.getOrDefault(word, 0) + 1;
            map.put(word, count);
        }
        for (var item : map.entrySet()) {
            System.out.printf("%s: %d%n", item.getKey(), item.getValue());
        }
    }

    // 4. *** Створити метод findOccurrence, що приймає на вхід рядковий список як параметр. Список заповнений довільними словами 10-20 штук, які можуть повторюватись у необмеженій кількості. Обчислити скільки разів трапляється кожне слово. Результат повернути у вигляді списку структур, що описують повторення кожного окремого взятого слова.
    public static List<Occurrence> findOccurrence(List<String> list) {
        Map<String, Occurrence> map = new LinkedHashMap<>();
        for (String word : list) {
            Occurrence occurrence = map.computeIfAbsent(word, Occurrence::new);
            occurrence.occurrence++;
        }
        return List.copyOf(map.values());
    }
}
