package hw_11;

import hw_11.coffee.order.CoffeeOrderBoard;
import hw_11.coffee.order.Order;

public class Program {
    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();
        for (int i = 1; i <= 10; i++) {
            board.add("Customer " + i);
        }
        System.out.println("Removing 1: " + board.deliver());
        System.out.println("Removing 2: " + board.deliver());
        System.out.println("Removing 3: " + board.deliver());
        System.out.println("Removing 5: " + board.deliver(5));
        System.out.println("Removing 8: " + board.deliver(8));
        board.draw();
    }
}
