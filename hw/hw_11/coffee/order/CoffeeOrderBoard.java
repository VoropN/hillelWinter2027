package hw_11.coffee.order;

import java.util.LinkedList;
import java.util.Iterator;

// 1. Створити клас CoffeeOrderBoard. Клас є цифровим поданням черги замовлень у кав'ярні.
public class CoffeeOrderBoard {
    private int orderNumber = 1;
    private final LinkedList<Order> orders = new LinkedList<>();

    // 3. Реалізувати метод add у класі CoffeeOrderBoard. Даний метод додає нове замовлення та надає замовленню номер (натуральний порядок).
    public Order add(String customer) {
        Order order = new Order(customer, orderNumber++);
        orders.offer(order);
        return order;
    }

    // 4. Реалізувати метод deliver у класі CoffeeOrderBoard. Цей метод видає найближче у черзі замовлення. Видача супроводжується видаленням замовлення зі списку.
    public Order deliver() {
        return orders.poll();
    }

    // 5. Реалізувати метод deliver у класі CoffeeOrderBoard. Даний метод видає замовлення з певним номером. Видача супроводжується видаленням замовлення зі списку.
    public Order deliver(int orderNumber) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderNumber() == orderNumber) {
                iterator.remove();
                return order;
            }
        }
        return null;
    }

    // 6. Реалізувати метод draw у класі CoffeeOrderBoard. Цей метод виводить у консоль інформацію про поточний стан черги у порядку найближчого до видачі замовлення.
    public void draw() {
        System.out.println("=====".repeat(4));
        printLine("Num", "Name");
        for (Order order : orders) {
            printLine(String.valueOf(order.getOrderNumber()), order.getCustomer());
        }
    }

    private void printLine(String num, String customer) {
        System.out.printf("%-5s | %s%n", num, customer);
    }
}
