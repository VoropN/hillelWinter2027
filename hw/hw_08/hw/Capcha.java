package hw_08.hw;

public class Capcha {
    static void main(String[] args) {
        drawNumbers("sda423456074");
        drawNumbers("12123423456074");
        drawNumbers1("12123423456074");
    }

    public static void drawNumbers(String num) {
        if (!isNumber(num)) {
            System.out.printf("Passed value (%s) is not a number!%n", num);
            return;
        }
        for (int i = 0; i < 7; i++) {
            for (char ch : num.toCharArray()) {
                int val = ch - '0';
                System.out.print(Digits.VALUES[val][i]);
            }
            System.out.println();
        }
    }

    public static boolean isNumber(String num) {
        return num.matches("\\d+");
    }

    public static void drawNumbers1(String num) {
        if (!isNumber1(num)) {
            System.out.printf("Passed value (%s) is not a number!%n", num);
            return;
        }
        String[][] images = new String[num.length()][];
        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(i) - '0';
            images[i] = Digits.VALUES[val];
        }
        for (int i = 0; i < 7; i++) {
            for (String[] image : images) {
                System.out.print(image[i]);
            }
            System.out.println();
        }
    }

    public static boolean isNumber1(String num) {
        for (char ch : num.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}

class Digits {
    public static String[][] VALUES = {
            {
                    "   ###   ",
                    "  #   #  ",
                    " #     # ",
                    " #     # ",
                    " #     # ",
                    "  #   #  ",
                    "   ###   "
            },
            {
                    "   # ",
                    "  ## ",
                    " # # ",
                    "   # ",
                    "   # ",
                    "   # ",
                    "   # ",
            },
            {
                    "  #####  ",
                    " #     # ",
                    "       # ",
                    "  #####  ",
                    " #       ",
                    " #       ",
                    " ####### "
            },
            {
                    "  #####  ",
                    " #     # ",
                    "       # ",
                    "  #####  ",
                    "       # ",
                    " #     # ",
                    "  #####  "
            },
            {
                    " #       ",
                    " #    #  ",
                    " #    #  ",
                    " #    #  ",
                    " ####### ",
                    "      #  ",
                    "      #  "
            },
            {
                    " ####### ",
                    " #       ",
                    " #       ",
                    " ######  ",
                    "       # ",
                    " #     # ",
                    "  #####  "
            },
            {
                    "  #####  ",
                    " #     # ",
                    " #       ",
                    " ######  ",
                    " #     # ",
                    " #     # ",
                    "  #####  "
            },
            {
                    " ####### ",
                    " #    #  ",
                    "     #   ",
                    "    #    ",
                    "   #     ",
                    "   #     ",
                    "   #     "
            },
            {
                    "  #####  ",
                    " #     # ",
                    " #     # ",
                    "  #####  ",
                    " #     # ",
                    " #     # ",
                    "  #####  "
            },
            {
                    "  #####  ",
                    " #     # ",
                    " #     # ",
                    "  ###### ",
                    "       # ",
                    " #     # ",
                    "  #####  "
            }
    };
}