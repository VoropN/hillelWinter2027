package hw_11.coffee.order;

// 2. Створити клас Order. Клас є цифровим поданням замовлення. Даний клас містить номер замовлення та ім'я людини, що його замовила.
public class Order {
    private int orderNumber;
    private String customer;

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public Order(String customer, int orderNumber) {
        this.customer = customer;
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customer='" + customer + '\'' +
                '}';
    }
}
