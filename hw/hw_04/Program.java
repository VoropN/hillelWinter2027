package hw_04;

import hw_04.part_1.SameName;

public class Program {
    public static void main(String[] args) {
        Employee sam = new Employee("Sam", "Altman", null, "CEO", "sam.altman@open.ai", "1(800)123-232-23", 40);
        System.out.println(sam);
        SameName first = new SameName();
        hw_04.part_2.SameName second = new hw_04.part_2.SameName();
        Car car = new Car();
        car.start();
    }
}
