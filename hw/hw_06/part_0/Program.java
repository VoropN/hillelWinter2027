package hw_06.part_0;

public class Program {
    public static void main(String[] args) {
        Figure[] figures = {
                new Circle(3),
                new Square(4),
                new Triangle(5, 10),
                new Triangle(1, 10)
        };
        System.out.println(calculateTotalArea(figures));
    }

    // 0. Створити масив фігур, і написати метод який виведе сумарну площу всіх фігур у цьому масиві
    public static double calculateTotalArea(Figure[] figures) {
        double areas = 0;
        for (Figure figure : figures) {
            areas += figure.area();
        }
        return areas;
    }
}
