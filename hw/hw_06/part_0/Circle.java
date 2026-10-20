package hw_06.part_0;

// 0. Створити 3 класи, які реалізують цей інтерфейс: коло, трикутник, квадрат.
public class Circle implements Figure {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}
