package hw_04;

/*
    5. Створити клас Car з публічним методом start. Метод start викликає у собі методи:
        - startElectricity()
        - startCommand()
        - startFuelSystem()
    Перелічені методи, є внутрішньою (прихованою) частиною комплексного методу start.
    Вони по суті відображають внутрішню поведінку класу Car і не повинні використовуватися за межами даного класу.
 */
public class Car {
    private void startElectricity() {
        System.out.println("Electricity started!");
    }

    private void startCommand() {
        System.out.println("Command started!");
    }

    private void startFuelSystem() {
        System.out.println("Fuel system started!");
    }

    public void start() {
        startElectricity();
        startCommand();
        startFuelSystem();
    }
}
