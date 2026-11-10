package hw_10;
/*
Проверка сбалансированности скобок
Описание. Используя Stack<Character>, проверить, правильно ли в строке расставлены скобки трёх типов: (), {}, [].
Пример. "{[()()]}" → true; "{[(])}" → false.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class task5 {

    public static void main(String[] args) {
        System.out.println(isValid("{{[]()[{}]}}"));
        System.out.println(isValid("{[(])}"));
        System.out.println(isValid("{[()()]}"));
    }

    public static boolean isValid(String parenthesis) {
        if (parenthesis == null || parenthesis.isEmpty()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (char ch : parenthesis.toCharArray()) {
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}

/*
{ - push
    [ - push
        ( - push
            { - push
            } - pop
        ) - pop
        [ - push
        ) - pop
        (
            [
            ]
        )
    ]
}
 */
